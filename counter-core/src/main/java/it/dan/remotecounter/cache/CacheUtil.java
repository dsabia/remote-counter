package it.dan.remotecounter.cache;

import it.dan.remotecounter.Constants;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.jcs.JCS;
import org.apache.jcs.access.CacheAccess;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;

public class CacheUtil {

	private static Logger log = Logger.getLogger(CacheUtil.class.getName());
	
	/**
	 * Execute connection to the cluster cache.
	 */
	public static CacheAccess getAndCreateCacheConnection() {
		CacheAccess cache = null;
		try {
			cache = JCS.getInstance(Constants.JCS_CLUSTER_CACHE_NAME);
		} catch (CacheException e) {
			log.error(String.format("Problem initializing cache: %s", e.getMessage()));
		}
		return cache;
	}

	public static void initOrRestoreCache(CacheAccess cacheAccess) {
		AtomicLong atomicCachedValue = (AtomicLong) cacheAccess.get(Constants.ATOMIC_LONG_COUNTER_KEY_CACHE);
		
		if(atomicCachedValue != null){
			log.info("Value coirrectly recovered");
		}else{
			log.info("Initialization remote counter value in progress...");
			// first time initialization
			atomicCachedValue = new AtomicLong(Constants.ATOMIC_LONG_COUNTER_START_VALUE);
			try {
				cacheAccess.put(Constants.ATOMIC_LONG_COUNTER_KEY_CACHE, atomicCachedValue);
				
			} catch (CacheException e) {
				log.error(e);
				throw new RuntimeException(e.getMessage());
			}
		}
		log.info("Remote counter actual value in cache: " + atomicCachedValue.get());
	}
}
