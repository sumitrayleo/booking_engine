package com.ehi.msi.enterprisecarshare.service;

/**
 * Contains methods to store the service health status of every individual
 * system. The key of the cache will be the system name like DQ, Wecar_US,
 * Appsec etc.
 * 
 */
public interface ServiceHealthCache {

	/**
	 * Retrieves the service health status of a particular system from cache
	 * based on the system name provided in the request as key
	 * 
	 * @param systemName
	 * @return
	 */
	<T> T find(final String systemName);

	/**
	 * Stores the health status in cache. The key is the system name which
	 * contains values like LRD, DQ, Wecar_US, ECS etc.
	 * 
	 * @param systemName
	 * @param serviceHealthResponse
	 * @return
	 */
	<T> T save(final String systemName, final T serviceHealthResponse);

}
