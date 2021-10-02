package com.justeat.gamemanager.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.justeat.gamemanager.service.GameService;

@Component
public class GameEventListner {

	@Autowired
	private GameService gameService;

	// we will invite 2 players to play while the game is created and ready to
	// receive players
	@EventListener
	private void handleNewGameEvent(NewGameEvent event) {

		this.gameService.inviteTowPlayers(event.getGame());

		System.out.println("Event to invite 2 players has been created for game  " + event.getGame().getGameId());
	}

}
