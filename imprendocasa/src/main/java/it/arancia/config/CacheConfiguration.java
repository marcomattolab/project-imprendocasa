package it.arancia.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
        	cm.createCache(it.arancia.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(it.arancia.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(it.arancia.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(it.arancia.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Cliente.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.Cliente.class.getName() + ".listaContattis", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Cliente.class.getName() + ".tags", jcacheConfiguration);
//            cm.createCache(it.arancia.domain.Cliente.class.getName() + ".incaricos", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Cliente.class.getName() + ".incaricoCommittentes", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Cliente.class.getName() + ".incaricoProponentes", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Cliente.class.getName() + ".incaricoAcquirenteLocatarios", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Cliente.class.getName() + ".incaricoSegnalatores", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Immobile.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.Immobile.class.getName() + ".files", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Immobile.class.getName() + ".incaricos", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Files.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.Partner.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.Partner.class.getName() + ".incaricos", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Incarico.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.Incarico.class.getName() + ".listaContattis", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Incarico.class.getName() + ".partners", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Incarico.class.getName() + ".committentes", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Incarico.class.getName() + ".proponentes", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Incarico.class.getName() + ".segnalatores", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Incarico.class.getName() + ".acquirenteLocatarios", jcacheConfiguration);
            cm.createCache(it.arancia.domain.Notifiche.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.ListaContatti.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.Tag.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.Geolocalizzazione.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.MailTemplate.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.AppSettings.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.Professione.class.getName(), jcacheConfiguration);
            cm.createCache(it.arancia.domain.EntityAuditEvent.class.getName(), jcacheConfiguration);
        };
    }
}
