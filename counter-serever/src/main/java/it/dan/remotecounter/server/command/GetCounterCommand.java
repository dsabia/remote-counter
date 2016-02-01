package it.dan.remotecounter.server.command;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.jcs.access.CacheAccess;

public class GetCounterCommand extends AbstractCounterCommand {

	public GetCounterCommand(CacheAccess cache) {
		super(cache);
	}

	@Override
	public Long execute() {
		AtomicLong atomicValue = retrieveFromCache();
		return atomicValue.get();
	}
}
