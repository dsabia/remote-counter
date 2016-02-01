package it.dan.remotecounter;

public enum REMOTE_COUNTER_OPERATIONS {

	INCREMENT("i"), DECREMENT("d"), GET("g");

	private final String command_line_operator;
	
	REMOTE_COUNTER_OPERATIONS(String value){
		this.command_line_operator = value;
	}
	
	public static REMOTE_COUNTER_OPERATIONS valueOfCommandLine(String commandLineValue){
		for (REMOTE_COUNTER_OPERATIONS current : REMOTE_COUNTER_OPERATIONS.values()) {
			if(current.command_line_operator.equals(commandLineValue)){
				return current;
			}
		}
		return null;
	}
}
