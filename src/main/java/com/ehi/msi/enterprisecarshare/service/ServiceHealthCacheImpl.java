package com.ehi.msi.enterprisecarshare.service;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Implementation class for ServiceHealthCache interface.
 * 
 */
@Component
public class ServiceHealthCacheImpl implements ServiceHealthCache {

	/*
	 * @see
	 * com.ehi.msi.enterprisecarshare.service.ServiceHealthCache#find(java.lang
	 * .String)
	 */
	@Override
	@Cacheable(value = "serviceHealthStatusCache")
	public <T> T find(final String systemName) {
		return null;
	}

	/*
	 * @see
	 * com.ehi.msi.enterprisecarshare.service.ServiceHealthCache#save(java.lang
	 * .String, java.lang.Object)
	 */
	@Override
	@CachePut(value = "serviceHealthStatusCache", key = "#systemName")
	public <T> T save(final String systemName, final T serviceHealthResponse) {
		return serviceHealthResponse;
	}

}
