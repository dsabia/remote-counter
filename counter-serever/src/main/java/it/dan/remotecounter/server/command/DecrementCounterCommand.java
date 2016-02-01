package it.dan.remotecounter.server.command;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.jcs.access.CacheAccess;

public class DecrementCounterCommand extends AbstractCounterCommand {

	public DecrementCounterCommand(CacheAccess cache) {
		super(cache);
	}

	@Override
	public Long execute() {
		AtomicLong atomicValue = retrieveFromCache();
		atomicValue.decrementAndGet();
		putInCache(atomicValue);
		return null;
	}

}
