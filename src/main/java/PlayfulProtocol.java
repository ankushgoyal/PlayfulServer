package main.java;

import java.util.*;

/**
 * 
 * @author agoyal
 *
 */
public class PlayfulProtocol {
	
	private enum State {
		KNOCKKNOCK, HINT, JOKE, BYE
	}
	
	private Map<String, String> hintJokeMap = new HashMap<String, String>();
	private int currentJoke = 0;
	
	private State state;
	
	public PlayfulProtocol() {
		this.state = State.KNOCKKNOCK;
		hintJokeMap.put("Turnip", "Turnip the heat!");
		hintJokeMap.put("Little Old Lady", "I didn't know you could yodel!");
		hintJokeMap.put("Atch", "Bless you!");
		hintJokeMap.put("Who", "Is there an echo in here?");
	}
	
	public String processInput(String input) {
		String output = null;
		if(input == null) {
			output = "Knock Knock!";
			state = State.HINT;
		} else if(input.equalsIgnoreCase("exit")) {
			output = "Bye!, see you next time.";
		} else if(state == State.HINT) {
			if(input.equalsIgnoreCase("Who's there?")) {
				currentJoke = new Random().nextInt(4);
				output = (String) hintJokeMap.keySet().toArray()[currentJoke];
				state = State.JOKE;
			} else {
				output = "You are supposed to say: Who's there?, lets try again: Knock Knock!";
			}
		} else if(state == State.JOKE) {
			if(input.equalsIgnoreCase((String) hintJokeMap.keySet().toArray()[currentJoke] + " who?")) {
				output = (String) hintJokeMap.values().toArray()[currentJoke] + "Want Another? (y,n)";
				state = State.BYE;
			} else {
				output = "You are supposed to say:" + (String) hintJokeMap.keySet().toArray()[currentJoke] + " who?" + ", lets try again: Knock Knock!";
				state = State.HINT;
			}
		} else if(state == State.BYE) {
			if(input.equalsIgnoreCase("y")) {
				output = "Knock Knock!";
				state = State.HINT;
			} else {
				output = "Bye!, see you next time.";
			}
		}
		
		return output;
	}
	
	
}
