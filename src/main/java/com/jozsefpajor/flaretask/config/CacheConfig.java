package com.jozsefpajor.flaretask.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author PJ
 */
@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {

    private static final Logger LOG = LoggerFactory.getLogger(CacheConfig.class);
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public static final String USERS = "users";

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(USERS);

        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {USERS})
    @Scheduled(fixedDelay = 30 * 60 * 1000, initialDelay = 500)
    public void reportCacheEvict() {
        LOG.info("Flush '" + USERS + "' Cache " + DATE_FORMAT.format(new Date()));
    }
}
