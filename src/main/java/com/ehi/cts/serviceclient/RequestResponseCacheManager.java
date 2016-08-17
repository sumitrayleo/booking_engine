package com.ehi.cts.serviceclient;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class RequestResponseCacheManager {

    public static final String LAST_REQUEST_CACHE_NAME = "LastRequestCache";
    public static final String LAST_RESPONSE_CACHE_NAME = "LastResponseCache";

    @Autowired
    private CacheManager cacheManager;

    public RequestResponseCacheManager() {
    }

    @CacheEvict(value = { LAST_REQUEST_CACHE_NAME, LAST_RESPONSE_CACHE_NAME }, allEntries = true)
    public void clearAllCache() {
        return;
    }

    public void clearCache(String cacheName) {
        Cache cache = getCache(cacheName);
        if (cache != null) {
            cache.clear();
        }
    }

    public Cache getCache(String cacheName) {

        if (StringUtils.isBlank(cacheName) || cacheManager == null) {
            return null;
        }
        return cacheManager.getCache(cacheName);
    }
}
