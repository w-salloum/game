package com.justeat.player.controller.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.justeat.player.dto.Move;
import com.justeat.player.dto.Player;
import com.justeat.player.service.PlayerService;

@RestController
@RequestMapping("/player/api")
public class PlayerAPIController {
	
	
	RestTemplate restTemplate= new RestTemplate();
	
	@Autowired
	private PlayerService playerService; 
	
	//input: playerId, num
	//output: move ( int numReceived;	private int ;int added; String playerId)
	@RequestMapping("/play")
	public ResponseEntity<Move> play(@RequestParam(name = "playerId") String playerId,@RequestParam(name = "num", defaultValue = "0") int num) {				
		Move move = this.playerService.play(playerId, num);
		return new ResponseEntity<Move>(move, HttpStatus.OK);
	}
	
	//input: playerId
	//output: move ( int numReceived;	private int ;int added; String playerId)
	@RequestMapping("/start")
	public ResponseEntity<Move> start(@RequestParam(name = "playerId") String playerId) {				
		Move move = this.playerService.start(playerId);
		return new ResponseEntity<Move>(move, HttpStatus.OK);
	}
	
	
	//Invite 2 players to join the game
	//output: 2 players
	@RequestMapping("/invite2")
	public ResponseEntity<Player[]> inviteTowPlayers() {
		
		
		Player[] players = this.playerService.getPlayersFromWaitingList(2);
		
		if(players !=null) {
			
			return new ResponseEntity<Player[]>(players, HttpStatus.OK);
		}		
				
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
	}
	
	//Invite 1 players to join the game
	//output: 1 player
	@RequestMapping("/invite1")
	public ResponseEntity<Player> inviteOnePlayer() {		
		
		Player[] players = this.playerService.getPlayersFromWaitingList(1);
		
		if(players[0] !=null) {
			
			return new ResponseEntity<Player>(players[0], HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
	}
	
	//Invite 1 players to join the game
		//output: 1 player
		@RequestMapping("/invite")
		public ResponseEntity<Player[]> invitePlayers(@RequestParam(name = "num", defaultValue = "1") int num) {		
			
			Player[] players = this.playerService.getPlayersFromWaitingList(num);
			if(players!=null) {
				
				return new ResponseEntity<Player[]>(players, HttpStatus.OK);
			}
			
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		}
	
	
	

}
