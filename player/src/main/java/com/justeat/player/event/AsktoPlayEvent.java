package com.justeat.player.event;

import com.justeat.player.dto.Player;

public class AsktoPlayEvent {
	
	private Player player;		
	
	public AsktoPlayEvent(Player player){
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

}
