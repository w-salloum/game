package com.justeat.gamemanager.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.justeat.gamemanager.dto.Game;
import com.justeat.gamemanager.dto.GameStatus;
import com.justeat.gamemanager.event.NewGameEvent;

@Component
@Scope("singleton")
public class GameManager {
	
	private Game game;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	 
	public boolean createGame() {		
		
		// we will not create a new game untill the game will be finished
		if(game == null || game.getStatus() == GameStatus.FINISHED) {
			this.game = new Game();
			NewGameEvent event = new NewGameEvent(game);
			applicationEventPublisher.publishEvent(event);
			System.out.println("A new game has been created");
			return true;
		}
		
		return false;
		
		
	}
	
	
	

}
