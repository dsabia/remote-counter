package it.dan.remotecounter.server;

import it.dan.remotecounter.Constants;
import it.dan.remotecounter.REMOTE_COUNTER_OPERATIONS;
import it.dan.remotecounter.server.command.AbstractCounterCommand;
import it.dan.remotecounter.server.command.DecrementCounterCommand;
import it.dan.remotecounter.server.command.GetCounterCommand;
import it.dan.remotecounter.server.command.IncrementCounterCommand;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.jcs.access.CacheAccess;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

public class RemoteCounterFactory<T>{
	
	private static Logger log = Logger.getLogger(RemoteCounterFactory.class.getName());
	
	private final HashMap<REMOTE_COUNTER_OPERATIONS, AbstractCounterCommand>	commands = Maps.newLinkedHashMap();
	
	public RemoteCounterFactory(CacheAccess cacheAccess) {
		initCommands(cacheAccess);
	}

	private void initCommands(CacheAccess cacheAccess) {
		commands.put(REMOTE_COUNTER_OPERATIONS.GET, new GetCounterCommand(cacheAccess));
		commands.put(REMOTE_COUNTER_OPERATIONS.INCREMENT, new IncrementCounterCommand(cacheAccess));
		commands.put(REMOTE_COUNTER_OPERATIONS.DECREMENT, new DecrementCounterCommand(cacheAccess));
	}

	public Long executeCommand(REMOTE_COUNTER_OPERATIONS command){
		return commands.get(command).execute();
	}
}
