package it.dan.remotecounter.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.jcs.access.CacheAccess;
import org.apache.log4j.Logger;

import com.google.common.eventbus.EventBus;

import it.dan.remotecounter.Constants;
import it.dan.remotecounter.cache.CacheUtil;

public class Server {

	private static Logger log = Logger.getLogger(Server.class.getName());
	
	public static void main(String[] args) throws Exception {
		
		CacheAccess cache = CacheUtil.getAndCreateCacheConnection();
		
		CacheUtil.initOrRestoreCache(cache);
		
		EventBus channel = new EventBus();
		ServerSocket socket;
		try {
			socket = new ServerSocket(Constants.SERVER_SOCKET_PORT);
			while (true) {
				Socket connection = socket.accept();
				UserThread newUser = new UserThread(connection, channel, cache);
				channel.register(newUser);
				new Thread(newUser).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
