package it.arancia.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.StringFilter;
import it.arancia.config.Constants;

import it.arancia.domain.*;// for static metamodels
import it.arancia.repository.AppSettingsRepository;
import it.arancia.repository.AuthorityRepository;
import it.arancia.repository.PersistentTokenRepository;
import it.arancia.repository.UserRepository;
import it.arancia.security.AuthoritiesConstants;
import it.arancia.security.SecurityUtils;
import it.arancia.security.SettingsConstants;
import it.arancia.service.dto.UserCriteria;
import it.arancia.service.dto.UserDTO;
import it.arancia.service.util.RandomUtil;
import it.arancia.web.rest.errors.EmailAlreadyUsedException;
import it.arancia.web.rest.errors.InvalidPasswordException;
import it.arancia.web.rest.errors.LoginAlreadyUsedException;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class UserService extends QueryService<User> {
//    private static final String FIELD_LOGIN = "login";
	private final Logger log = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final PersistentTokenRepository persistentTokenRepository;
	private final AuthorityRepository authorityRepository;
	private final AppSettingsRepository appSettingsRepository;
	private final CacheManager cacheManager;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			PersistentTokenRepository persistentTokenRepository, AuthorityRepository authorityRepository,
			AppSettingsRepository appSettingsRepository, CacheManager cacheManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.persistentTokenRepository = persistentTokenRepository;
		this.authorityRepository = authorityRepository;
		this.appSettingsRepository = appSettingsRepository;
		this.cacheManager = cacheManager;
	}

	public Optional<User> activateRegistration(String key) {
		log.debug("Activating user for activation key {}", key);
		return userRepository.findOneByActivationKey(key).map(user -> {
			// activate given user for the registration key.
			user.setActivated(true);
			user.setActivationKey(null);
			this.clearUserCaches(user);
			log.debug("Activated user: {}", user);
			return user;
		});
	}

	public Optional<User> completePasswordReset(String newPassword, String key) {
		log.debug("Reset user password for reset key {}", key);
		return userRepository.findOneByResetKey(key)
				.filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400))).map(user -> {
					user.setPassword(passwordEncoder.encode(newPassword));
					user.setResetKey(null);
					user.setResetDate(null);
					this.clearUserCaches(user);
					return user;
				});
	}

	public Optional<User> requestPasswordReset(String mail) {
		return userRepository.findOneByEmailIgnoreCase(mail).filter(User::getActivated).map(user -> {
			user.setResetKey(RandomUtil.generateResetKey());
			user.setResetDate(Instant.now());
			this.clearUserCaches(user);
			return user;
		});
	}

	public User registerUser(UserDTO userDTO, String password) {
		userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).ifPresent(existingUser -> {
			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new LoginAlreadyUsedException();
			}
		});
		userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
			boolean removed = removeNonActivatedUser(existingUser);
			if (!removed) {
				throw new EmailAlreadyUsedException();
			}
		});
		User newUser = new User();
		String encryptedPassword = passwordEncoder.encode(password);
		newUser.setLogin(userDTO.getLogin().toLowerCase());
		// new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setFirstName(userDTO.getFirstName());
		newUser.setLastName(userDTO.getLastName());
		newUser.setEmail(userDTO.getEmail().toLowerCase());
		newUser.setImageUrl(userDTO.getImageUrl());
		newUser.setLangKey(userDTO.getLangKey());
		// new user is not active
		newUser.setActivated(false);
		// new user gets registration key
		newUser.setActivationKey(RandomUtil.generateActivationKey());
		Set<Authority> authorities = new HashSet<>();
		authorityRepository.findById(AuthoritiesConstants.ANONYMOUS).ifPresent(authorities::add);
		newUser.setAuthorities(authorities);
		userRepository.save(newUser);
		this.clearUserCaches(newUser);
		log.debug("Created Information for User: {}", newUser);
		return newUser;
	}

	private boolean removeNonActivatedUser(User existingUser) {
		if (existingUser.getActivated()) {
			return false;
		}
		userRepository.delete(existingUser);
		userRepository.flush();
		this.clearUserCaches(existingUser);
		return true;
	}

	public User createUser(UserDTO userDTO) {
		User user = new User();
		user.setLogin(userDTO.getLogin().toLowerCase());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail().toLowerCase());
		user.setImageUrl(userDTO.getImageUrl());
		if (userDTO.getLangKey() == null) {
			user.setLangKey(Constants.DEFAULT_LANGUAGE); // default language
		} else {
			user.setLangKey(userDTO.getLangKey());
		}
//        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
		String encryptedPassword = passwordEncoder.encode(getDefaultPassword());
		user.setPassword(encryptedPassword);
		user.setResetKey(RandomUtil.generateResetKey());
		user.setResetDate(Instant.now());
		user.setActivated(true);
		if (userDTO.getAuthorities() != null) {
			Set<Authority> authorities = userDTO.getAuthorities().stream().map(authorityRepository::findById)
					.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());
			user.setAuthorities(authorities);
//            //TODO CHECK Authority ROLE_USER is MANDATORY
//            List<Authority> list = new ArrayList<>(authorities);
//            List<String> listAuthritiesNames = list!=null ? list.stream().map(Authority::getName).collect(Collectors.toList()) : new ArrayList<>();
//            if(!CollectionUtils.isEmpty(listAuthritiesNames) && !listAuthritiesNames.contains("ROLE_USER")) { //FIXME
//            		Authority auth = new Authority();
//            		auth.setName("ROLE_USER"); //FIXME
//				authorities.add(auth);
//            }
		}
		userRepository.save(user);
		this.clearUserCaches(user);
		log.debug("Created Information for User: {}", user);
		return user;
	}

	private String getDefaultPassword() {
		String defaultPassword = "password";
		AppSettings appSettings = appSettingsRepository.findOneByCategoriaAndChiave(
				SettingsConstants.CATEGORIA_USER_MANAGEMENT, SettingsConstants.CHIAVE_DEFAULT_PASSWORD).get();
		if (appSettings != null && StringUtils.isNotBlank(appSettings.getValore())) {
			defaultPassword = appSettings.getValore();
		}
		return defaultPassword;
	}

	/**
	 * Update basic information (first name, last name, email, language) for the
	 * current user.
	 *
	 * @param firstName first name of user
	 * @param lastName  last name of user
	 * @param email     email id of user
	 * @param langKey   language key
	 * @param imageUrl  image URL of user
	 */
	public void updateUser(String firstName, String lastName, String email, String langKey, String imageUrl) {
		SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).ifPresent(user -> {
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email.toLowerCase());
			user.setLangKey(langKey);
			user.setImageUrl(imageUrl);
			this.clearUserCaches(user);
			log.debug("Changed Information for User: {}", user);
		});
	}

	/**
	 * Update all information for a specific user, and return the modified user.
	 *
	 * @param userDTO user to update
	 * @return updated user
	 */
	public Optional<UserDTO> updateUser(UserDTO userDTO) {
		return Optional.of(userRepository.findById(userDTO.getId())).filter(Optional::isPresent).map(Optional::get)
				.map(user -> {
					this.clearUserCaches(user);
					user.setLogin(userDTO.getLogin().toLowerCase());
					user.setFirstName(userDTO.getFirstName());
					user.setLastName(userDTO.getLastName());
					user.setEmail(userDTO.getEmail().toLowerCase());
					user.setImageUrl(userDTO.getImageUrl());
					user.setActivated(userDTO.isActivated());
					user.setLangKey(userDTO.getLangKey());
					Set<Authority> managedAuthorities = user.getAuthorities();
					managedAuthorities.clear();
					userDTO.getAuthorities().stream().map(authorityRepository::findById).filter(Optional::isPresent)
							.map(Optional::get).forEach(managedAuthorities::add);
					this.clearUserCaches(user);
					log.debug("Changed Information for User: {}", user);
					return user;
				}).map(UserDTO::new);
	}

	public void deleteUser(String login) {
		userRepository.findOneByLogin(login).ifPresent(user -> {
			userRepository.delete(user);
			this.clearUserCaches(user);
			log.debug("Deleted User: {}", user);
		});
	}

	public void changePassword(String currentClearTextPassword, String newPassword) {
		SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).ifPresent(user -> {
			String currentEncryptedPassword = user.getPassword();
			if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
				throw new InvalidPasswordException();
			}
			String encryptedPassword = passwordEncoder.encode(newPassword);
			user.setPassword(encryptedPassword);
			this.clearUserCaches(user);
			log.debug("Changed password for User: {}", user);
		});
	}

	@Transactional(readOnly = true)
	public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
		return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
	}

	@Transactional(readOnly = true)
	public Page<UserDTO> findByCriteria(UserCriteria criteria, Pageable page) {
		final Specification<User> specification = createSpecificationOnlyAgents(criteria);
		return userRepository.findAll(specification, page).map(UserDTO::new);
	}

	private Specification<User> createSpecificationOnlyAgents(UserCriteria criteria) {
		Specification<User> specification = this.createSpecification(criteria);
		specification = specification.and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.notEqual(root.join(User_.authorities).get(Authority_.name), AuthoritiesConstants.ANONYMOUS));
		specification = specification.and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.notEqual(root.join(User_.authorities).get(Authority_.name), AuthoritiesConstants.ADMIN));
		specification = specification.and((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.notEqual(root.join(User_.authorities).get(Authority_.name), AuthoritiesConstants.SUPER_ADMIN));
//
//        specification = specification.and(
//        		(root, criteriaQuery, criteriaBuilder) ->
//        			criteriaBuilder.not(	
//        									criteriaBuilder.in(root.join(User_.authorities).get(Authority_.name))
//        									.in(Arrays.asList(AuthoritiesConstants.ANONYMOUS,AuthoritiesConstants.ADMIN,AuthoritiesConstants.SUPER_ADMIN ))
//        							   )			
//        				);
//        specification = specification.and(
//        		(root, criteriaQuery, criteriaBuilder) ->
//        				criteriaBuilder.in(root.join(User_.authorities).get(Authority_.name)).in(Arrays.asList(AuthoritiesConstants.AGENT))
//        );
		return specification;
	}

	/**
	 * Function to convert UserCriteria to a {@link Specification}
	 */
	private Specification<User> createSpecification(UserCriteria criteria) {
		Specification<User> specification = Specification.where(null);
		if (criteria != null) {
			if (criteria.getId() != null) {
				specification = specification.and(buildSpecification(criteria.getId(), User_.id));
			}
			if (criteria.getLogin() != null) {
				specification = specification.and(buildStringSpecification(criteria.getLogin(), User_.login));
			}
			if (criteria.getFirstName() != null) {
				specification = specification.and(buildStringSpecification(criteria.getFirstName(), User_.firstName));
			}
			if (criteria.getLastName() != null) {
				specification = specification.and(buildStringSpecification(criteria.getLastName(), User_.lastName));
			}
			// Security for Agents
			if (criteria.getSecurityEnabled() != null && criteria.getSecurityEnabled().getEquals() != null
					&& criteria.getSecurityEnabled().getEquals()) {
				boolean isAgent = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.AGENT);
				if (isAgent) {
					Optional<String> loggedUser = SecurityUtils.getCurrentUserLogin();
					String userName = loggedUser.orElse(null);
					StringFilter currentUser = new StringFilter();
					currentUser.setEquals(userName);
					specification = specification.and(buildStringSpecification(currentUser, User_.login));
				}
			}
		}
		return specification;
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthoritiesByLogin(String login) {
		return userRepository.findOneWithAuthoritiesByLogin(login);
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthorities(Long id) {
		return userRepository.findOneWithAuthoritiesById(id);
	}

	@Transactional(readOnly = true)
	public Optional<User> getUserWithAuthorities() {
		return SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
	}

	/**
	 * Persistent Token are used for providing automatic authentication, they should
	 * be automatically deleted after 30 days.
	 * <p>
	 * This is scheduled to get fired everyday, at midnight.
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void removeOldPersistentTokens() {
		LocalDate now = LocalDate.now();
		persistentTokenRepository.findByTokenDateBefore(now.minusMonths(1)).forEach(token -> {
			log.debug("Deleting token {}", token.getSeries());
			User user = token.getUser();
			user.getPersistentTokens().remove(token);
			persistentTokenRepository.delete(token);
		});
	}

	/**
	 * Not activated users should be automatically deleted after 3 days.
	 * <p>
	 * This is scheduled to get fired everyday, at 01:00 (am).
	 */
	@Scheduled(cron = "0 0 1 * * ?")
	public void removeNotActivatedUsers() {
		userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
				.forEach(user -> {
					log.debug("Deleting not activated user {}", user.getLogin());
					userRepository.delete(user);
					this.clearUserCaches(user);
				});
	}

	/**
	 * @return a list of all the authorities
	 */
	@Transactional(readOnly = true)
	public List<String> getAuthorities() {
		return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
	}

	/**
	 * @return a list of all the authorities filtered by authority hierarchy
	 */
	@Transactional(readOnly = true)
	public List<String> getFilteredAuthorities() {
		List<String> filteredAuthorities = new ArrayList<String>();
		List<String> authorities = getAuthorities();
		if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.SUPER_ADMIN)) {
			filteredAuthorities.addAll(authorities);
		} else if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN)) {
			List<String> toExclude = new ArrayList<>();
			toExclude.add(AuthoritiesConstants.SUPER_ADMIN);
			// toExclude.add(AuthoritiesConstants.USER);
			for (String auth : authorities) {
				if (!toExclude.contains(auth)) {
					filteredAuthorities.add(auth);
				}
			}
		} else if (SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.OPERATOR)) {
			List<String> toExclude = new ArrayList<>();
			toExclude.add(AuthoritiesConstants.SUPER_ADMIN);
			toExclude.add(AuthoritiesConstants.ADMIN);
			// toExclude.add(AuthoritiesConstants.USER);
			for (String auth : authorities) {
				if (!toExclude.contains(auth)) {
					filteredAuthorities.add(auth);
				}
			}
		}
		return filteredAuthorities;
	}

	private void clearUserCaches(User user) {
		Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE)).evict(user.getLogin());
		Objects.requireNonNull(cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE)).evict(user.getEmail());
	}

	@Transactional(readOnly = true)
	public List<UserDTO> findAllUsersAgents() {
		return userRepository.findAllUsersAgents(AuthoritiesConstants.AGENT).stream()
				// .filter(user -> isAgent(user))
				.map(UserDTO::new).collect(Collectors.toList());
	}

//	private boolean isAgent(User user) {
//		boolean isAgent = false;
//		for (Authority autority : user.getAuthorities()) {
//			if (AuthoritiesConstants.AGENT.equals(autority.getName())) {
//				isAgent = true;
//			}
//		}
//		return isAgent;
//	}

	@Transactional(readOnly = true)
	public Page<UserDTO> findAllUsersAgents(Pageable pageable) {
		UserCriteria criteria = new UserCriteria();
		BooleanFilter security = new BooleanFilter();
		security.setEquals(Boolean.TRUE);
		criteria.setSecurityEnabled(security);
		final Specification<User> specification = createSpecificationOnlyAgents(criteria);
		return userRepository.findAll(specification, pageable).map(UserDTO::new);
	}

}
