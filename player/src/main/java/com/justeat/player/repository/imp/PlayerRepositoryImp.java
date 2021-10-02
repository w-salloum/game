package com.justeat.player.repository.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Repository;

import com.justeat.player.dto.Player;
import com.justeat.player.repository.PlayerRepository;

@Repository
public class PlayerRepositoryImp implements PlayerRepository {
	
	private final HashMap<String, Player> players = new HashMap<>();
	// We will put all the automatic players who are waiting to play in this Queue
	private final Queue<String> waitingPlayers = new LinkedList<>(); 

	
	public Player getPlayerById(String sessionID) {		
		return players.get(sessionID);
	}
	
	public void save(Player player) {
		
		 if(players.get(player.getId()) == null) { // let us double check
			 Integer seq = players.size()+1;
			 player.setSequance(seq);
			 player.setShowName("Player"+seq);
			 players.put(player.getId(),player);			
			 
			 System.out.println("player created");
		 }else {
			 System.out.println("player not created");
		 }
		 
	}
	
	public List<Player> getAllPlayers(){
		
		List<Player> list = new ArrayList<>();
		players.forEach((k,v)->{
			list.add(v);
		});
		return list;
	}
	
	
	public boolean addToWaitingList(String playerId) {
		this.waitingPlayers.add(playerId);
		return true;
		
	}

	@Override
	public Player[] getPlayersFromWaitingList(int numbOfPlayers) {
		
		if (this.waitingPlayers.isEmpty()) {
			return null;
		}
		Player[] players = new Player[numbOfPlayers];
		int i = 0;
		while(!this.waitingPlayers.isEmpty() && i<numbOfPlayers) {
			String  playerId = this.waitingPlayers.poll();
			
			players[i]= this.players.get(playerId);	
			i++;
		}
		
		return players;
		
	}
	
	

}
