package it.dan.remotecounter.server;

import java.util.HashMap;

import org.apache.jcs.access.CacheAccess;
import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

import it.dan.remotecounter.REMOTE_COUNTER_OPERATIONS;
import it.dan.remotecounter.server.command.AbstractCounterCommand;
import it.dan.remotecounter.server.command.DecrementCounterCommand;
import it.dan.remotecounter.server.command.GetCounterCommand;
import it.dan.remotecounter.server.command.IncrementCounterCommand;

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
