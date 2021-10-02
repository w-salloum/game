package com.justeat.player.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.justeat.player.service.PlayerService;

@Component
public class PlayerEventListner {

	@Autowired
	private PlayerService playerService;

	// this event to ask the game server to send an invitation to play
	@EventListener
	public void handlAskToPlayEvent(AsktoPlayEvent event) {

		
		Boolean invitation = this.playerService.askForInvitation(event.getPlayer().getId());

		System.out.println("Event to ask for invitation and the response  : " + invitation);

	}

}
