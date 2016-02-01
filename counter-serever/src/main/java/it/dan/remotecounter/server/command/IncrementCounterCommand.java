package it.dan.remotecounter.server.command;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.jcs.access.CacheAccess;

public class IncrementCounterCommand extends AbstractCounterCommand {

	public IncrementCounterCommand(CacheAccess cache) {
		super(cache);
	}

	@Override
	public Long execute() {
		AtomicLong atomicValue = retrieveFromCache();
		atomicValue.incrementAndGet();
		putInCache(atomicValue);
		return null;
	}
}
