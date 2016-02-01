package it.dan.remotecounter.server;

import it.dan.remotecounter.REMOTE_COUNTER_OPERATIONS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.jcs.access.CacheAccess;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class UserThread extends Thread {

	private Socket connection;
	private EventBus channel;
	private BufferedReader in;
	private PrintWriter out;
	private RemoteCounterFactory<Long> commandFactory;

	public UserThread(Socket connection, EventBus channel, CacheAccess cache) {
		this.connection = connection;
		this.channel = channel;
		this.commandFactory = new RemoteCounterFactory<Long>(cache);
		try {
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			out = new PrintWriter(connection.getOutputStream(), true);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Subscribe
	public void recieveMessage(String message) {
		REMOTE_COUNTER_OPERATIONS op = REMOTE_COUNTER_OPERATIONS.valueOfCommandLine(message);
		if(op != null){
			Long value = commandFactory.executeCommand(REMOTE_COUNTER_OPERATIONS.valueOfCommandLine(message));
			
			if (out != null ) {
				out.println(value == null ? "done" : value);
			}
		}else{
			out.println("no server operation defined");
		}
		
	}

	@Override
	public void run() {
		try {
			String input;
			while ((input = in.readLine()) != null) {
				channel.post(input);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// reached eof
		channel.unregister(this);
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		in = null;
		out = null;
	}
}
