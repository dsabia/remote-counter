package it.dan.remotecounter.client;

import it.dan.remotecounter.Constants;
import it.dan.remotecounter.cache.CacheUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.jcs.access.CacheAccess;
import org.apache.log4j.Logger;

/** 
 * Client simulation
 * 1. connect to cache
 * 2. socket connection to server with operation of (g)GET, (i)INCREMENT, (d)DECREMENT or (q)QUIT
 * @author Daniel
 *
 */
public class Client {
	
	private static Logger log = Logger.getLogger(Client.class.getName());

	public static void main(String[] args)  {
		
		@SuppressWarnings("unused")
		CacheAccess cache = CacheUtil.getAndCreateCacheConnection();
		
		while(true){
			try {
				socketConnection();
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}

	/**
	 * Simple socket connection with cmd operation of<br/>
	 * (g)GET, (i)INCREMENT, (d)DECREMENT or (q)QUIT
	 */
	private static void socketConnection() throws IOException{
		Socket clientCounterSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		try {
			clientCounterSocket = new Socket(Constants.SERVER_HOST_NAME, Constants.SERVER_SOCKET_PORT);
			out = new PrintWriter(clientCounterSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientCounterSocket.getInputStream()));
		} catch (UnknownHostException e) {
			log.error("Don't know about host: " + Constants.SERVER_HOST_NAME);
			System.exit(1);
		} catch (IOException e) {
			log.error("Couldn't get I/O for " + "the connection to: " + Constants.SERVER_HOST_NAME);
			System.exit(1);
		}
		
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;

		log.info("Send Message (g)GET, (i)INCREMENT, (d)DECREMENT or (q)QUIT)");
		while ((userInput = stdIn.readLine()) != null) {
			// end loop
			if (userInput.equals("q")){
				log.info("Ok, bye now.");
				System.exit(1);
			}

			out.println(userInput);
			

			log.info("response: " + in.readLine());
			log.info("Send Message again (g)GET, (i)INCREMENT, (d)DECREMENT or (q)QUIT)");
		}

		out.close();
		in.close();
		stdIn.close();
		clientCounterSocket.close();
	}
}
