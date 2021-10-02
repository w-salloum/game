package com.justeat.player.service.imp;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.justeat.player.dto.Move;
import com.justeat.player.dto.Player;
import com.justeat.player.event.AsktoPlayEvent;
import com.justeat.player.repository.PlayerRepository;
import com.justeat.player.service.PlayerService;

@Service
public class PlayerServiceImp implements PlayerService {

	@Value("${game.max_random_number}")
	private int maxRandomNumber;
	
	@Value("${game.max_allowed_to_invite}") 
	private int maxallowedToInvite;

	@Autowired
	ApplicationEventPublisher applicationEventPublisher;

	@Value("${game.gamemanager_api}")
	private String gamemanagerAPI;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PlayerRepository playerRepository;

	public Player getPlayerById(String sessionID) {
		return this.playerRepository.getPlayerById(sessionID);
	}

	public Player createPlayer(String sessionID, boolean auto) {
		Player player = new Player(sessionID, auto);
		this.playerRepository.save(player);
		 if (player.isAutoPlayer()) {
			 this.addPlayerToWaitingListtoPlay(player);
		 }

		

		return player;
	}

	public List<Player> getAllPlayers() {

		return this.playerRepository.getAllPlayers();
	}

	public Move play(String playerId, int num) {

		Player player = this.getPlayerById(playerId);
		if (player != null) {
			int divided = 0;
			int added = 0;

			if (num % 3 == 0) {
				divided = num / 3;

			} else if ((num + 1) % 3 == 0) {
				divided = (num + 1) / 3;
				added = 1;
			} else {
				if ((num - 1) % 3 == 0)
					divided = (num - 1) / 3;
				added = -1;
			}

			Move move = new Move(num, divided, added, playerId);
			System.out.println(move);
			return move;
		}

		return null;

	}

	public Move start(String playerId) {
		Player player = this.getPlayerById(playerId);
		if (player != null) {
			Random random = new Random();
			int startNum = random.nextInt(this.maxRandomNumber) + 2;
			Move move = new Move(-1, startNum, 0, playerId); // -1 in case this player started the game;
			System.out.println(move);
			return move;
		}
		return null;
	}

	public boolean askForInvitation(String playerId) {

		String url = gamemanagerAPI + "/askforinvitation" + "?playerId=" + playerId;
		try {
			ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				return response.getBody();
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean startGameForPlayer(String playerId) {
		
		Player player = this.getPlayerById(playerId);
		
		if (player !=null && !player.isAutoPlayer()) {
			this.addPlayerToWaitingListtoPlay(player);
			
			return true;
		}
		return false;
	}
	
	private boolean addPlayerToWaitingListtoPlay(Player player) {
		
		this.playerRepository.addToWaitingList(player.getId());
		AsktoPlayEvent event = new AsktoPlayEvent(player);
		applicationEventPublisher.publishEvent(event);
		return true;
	}

	@Override
	public Player[] getPlayersFromWaitingList(int numbOfPlayers) {
		if (numbOfPlayers > this.maxallowedToInvite) {
			numbOfPlayers = this.maxallowedToInvite;
		}
		return this.playerRepository.getPlayersFromWaitingList(numbOfPlayers);
	}
	
	

}
