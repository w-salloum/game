package com.justeat.gamemanager.event;

import com.justeat.gamemanager.dto.Game;

public class NewGameEvent {
	
	private Game game;	
	
	
	public NewGameEvent(Game game){
		this.game = game;
	}



	public Game getGame() {
		return game;
	}

}
