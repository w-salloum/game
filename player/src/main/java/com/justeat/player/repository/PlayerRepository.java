package com.justeat.player.repository;

import java.util.List;

import com.justeat.player.dto.Player;


public interface PlayerRepository {
	
	
	
	public Player getPlayerById(String playerId);
	
	public void save(Player player) ;
	
	public List<Player> getAllPlayers();
	
	//Add a player who wants to play to the queue
	public boolean addToWaitingList(String playerId);

	public Player[] getPlayersFromWaitingList(int numbOfPlayers);
	
	

}
