package com.justeat.player.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.justeat.player.dto.Move;
import com.justeat.player.dto.Player;

public interface PlayerService {

	public Player getPlayerById(String sessionID);

	public Player createPlayer(String sessionID, boolean auto);

	public List<Player> getAllPlayers();

	public Move play(String playerId, int num);

	public Move start(String playerId);
	
	//Get players from the Queue of the players who are waiting to play
	public Player[] getPlayersFromWaitingList(int numbOfPlayers);

	/*
	 * it will ask the game manager to invite players, and if the invitation is
	 * accepted we will delete the player from the waiting list, while he will be
	 * playing when the game is ready
	 */
	public boolean askForInvitation(String playerId);

	//Manual player will not be in the waiting list to play,
	// so we start the game for the manual player
	public boolean startGameForPlayer(String playerId);

}
