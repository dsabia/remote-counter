package it.dan.remotecounter.server.command;

import it.dan.remotecounter.Constants;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.jcs.access.CacheAccess;
import org.apache.jcs.access.exception.CacheException;

public abstract class AbstractCounterCommand implements Command<Long> {

	private CacheAccess cache;
	
	public AbstractCounterCommand(CacheAccess cache) {
		this.cache = cache;
	}

	protected void putInCache(AtomicLong counter) {
		try {
			cache.put(Constants.ATOMIC_LONG_COUNTER_KEY_CACHE, counter);
		} catch (CacheException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	protected AtomicLong retrieveFromCache() {
		return (AtomicLong) cache.get(Constants.ATOMIC_LONG_COUNTER_KEY_CACHE);
	}
}
