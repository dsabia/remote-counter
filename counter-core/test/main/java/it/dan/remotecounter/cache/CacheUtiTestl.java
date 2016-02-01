package it.dan.remotecounter.cache;

import org.apache.jcs.access.CacheAccess;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class CacheUtiTestl {

	private static Logger log = Logger.getLogger(CacheUtiTestl.class.getName());
	@Test
	public void test_getAndCreateCacheConnection_create(){
		CacheAccess cache = CacheUtil.getAndCreateCacheConnection();
		Assert.assertNotNull(cache);
	}
	
	@Test
	public void test_getAndCreateCacheConnection_create_and_reconnect(){
		//create
		CacheAccess cache = CacheUtil.getAndCreateCacheConnection();
		Assert.assertNotNull(cache);
		//reconnect
		cache = CacheUtil.getAndCreateCacheConnection();
		Assert.assertNotNull(cache);
	}
	
	@Test
	public void test_initOrRestoreCache_invocation_not_throws_exception(){
		CacheAccess cache = CacheUtil.getAndCreateCacheConnection();
		Assert.assertNotNull(cache);
		CacheUtil.initOrRestoreCache(cache);
	}
}
