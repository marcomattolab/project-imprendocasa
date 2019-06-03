package it.arancia.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import it.arancia.config.Constants;
import it.arancia.domain.User;
import it.arancia.repository.UserRepository;
import it.arancia.security.AuthoritiesConstants;
import it.arancia.service.MailService;
import it.arancia.service.UserService;
import it.arancia.service.dto.UserCriteria;
import it.arancia.service.dto.UserDTO;
import it.arancia.web.rest.errors.BadRequestAlertException;
import it.arancia.web.rest.errors.EmailAlreadyUsedException;
import it.arancia.web.rest.errors.LoginAlreadyUsedException;
import it.arancia.web.rest.util.HeaderUtil;
import it.arancia.web.rest.util.PaginationUtil;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the User entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("/api/users")
public class UserResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private final UserService userService;
    private final UserRepository userRepository;
    private final MailService mailService;

    public UserResource(UserService userService, UserRepository userRepository, MailService mailService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    /**
     * POST    : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     *
     * @param userDTO the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws BadRequestAlertException 400 (Bad Request) if the login or email is already in use
     */
    @PostMapping("")
    @Timed
    //@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    //@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_OPERATOR') OR hasRole('ROLE_SUPER_ADMIN')")
    @PreAuthorize("hasRole('"+AuthoritiesConstants.ADMIN+"') OR hasRole('"+AuthoritiesConstants.SUPER_ADMIN+"') OR hasRole('"+AuthoritiesConstants.OPERATOR+"')")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);

        if (userDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
            // Lowercase the user login before comparing with database
        } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
            throw new LoginAlreadyUsedException();
        } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            User newUser = userService.createUser(userDTO);
            mailService.sendCreationEmail(newUser);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin()))
                .headers(HeaderUtil.createAlert( "userManagement.created", newUser.getLogin()))
                .body(newUser);
        }
    }

    /**
     * PUT  : Updates an existing User.
     *
     * @param userDTO the user to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated user
     * @throws EmailAlreadyUsedException 400 (Bad Request) if the email is already in use
     * @throws LoginAlreadyUsedException 400 (Bad Request) if the login is already in use
     */
    @PutMapping("")
    @Timed
    //@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    @PreAuthorize("hasRole('"+AuthoritiesConstants.ADMIN+"') OR hasRole('"+AuthoritiesConstants.SUPER_ADMIN+"') OR hasRole('"+AuthoritiesConstants.OPERATOR+"')")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        log.debug("REST request to update User : {}", userDTO);
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new EmailAlreadyUsedException();
        }
        existingUser = userRepository.findOneByLogin(userDTO.getLogin().toLowerCase());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new LoginAlreadyUsedException();
        }
        Optional<UserDTO> updatedUser = userService.updateUser(userDTO);

        return ResponseUtil.wrapOrNotFound(updatedUser,
            HeaderUtil.createAlert("userManagement.updated", userDTO.getLogin()));
    }

    /**
     * GET  : get all users. restituisce gli Agenti selezionabili selezionabili in base al profilo (Usare nelle select Agente)
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and with body all users
     */
    @GetMapping("")
    @Timed
//    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {
//      final Page<UserDTO> page = userService.getAllManagedUsers(pageable);
    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable, UserCriteria criteria) {
        final Page<UserDTO> page = userService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
//	/**
//	 * GET /agents 
//	 *
//	 * @return restituisce gli Agenti 
//	 */
//	@GetMapping("/agents")
//	@Timed
//	public List<UserDTO> getAllUsersAgents() {
//		final List<UserDTO> users = userService.findAllUsersAgents();
//		return users;
//	}

	/**
	 * GET /agents 
	 *
	 * @return restituisce gli utenti selezionabili come Agenti con paginazione
	 */
	@GetMapping("/agents")
	@Timed
	public ResponseEntity<List<UserDTO>> getAllUsersAgents(Pageable pageable) {
		//final List<UserDTO> users = userService.findAllUsersAgents();
		Page<UserDTO> page = userService.findAllUsersAgents(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/agents");
	    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
    
    /**
     * @return a string list of the all of the roles
     */
    @GetMapping("/authorities")
    @Timed
    //@PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    @PreAuthorize("hasRole('"+AuthoritiesConstants.ADMIN+"') OR hasRole('"+AuthoritiesConstants.SUPER_ADMIN+"') OR hasRole('"+AuthoritiesConstants.OPERATOR+"')")
    public List<String> getAuthorities() {
        //return userService.getAuthorities();
    		return userService.getFilteredAuthorities();
    }

    /**
     * GET /:login : get the "login" user.
     *
     * @param login the login of the user to find
     * @return the ResponseEntity with status 200 (OK) and with body the "login" user, or with status 404 (Not Found)
     */
    @GetMapping("/{login:" + Constants.LOGIN_REGEX + "}")
    @Timed
    public ResponseEntity<UserDTO> getUser(@PathVariable String login) {
        log.debug("REST request to get User : {}", login);
        return ResponseUtil.wrapOrNotFound(
            userService.getUserWithAuthoritiesByLogin(login)
                .map(UserDTO::new));
    }

    /**
     * DELETE /:login : delete the "login" User.
     *
     * @param login the login of the user to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/{login:" + Constants.LOGIN_REGEX + "}")
    @Timed
    @PreAuthorize("hasRole(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteUser(@PathVariable String login) {
        log.debug("REST request to delete User: {}", login);
        userService.deleteUser(login);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "userManagement.deleted", login)).build();
    }
}
