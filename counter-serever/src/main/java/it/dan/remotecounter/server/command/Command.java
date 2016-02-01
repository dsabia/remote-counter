package it.dan.remotecounter.server.command;

public interface Command<A> {
	
	A execute(); 
}
