package com.justeat.gamemanager.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.justeat.gamemanager.config.GameManager;
import com.justeat.gamemanager.dto.Game;
import com.justeat.gamemanager.dto.GameStatus;
import com.justeat.gamemanager.dto.Move;
import com.justeat.gamemanager.dto.Player;
import com.justeat.gamemanager.repository.GameRepository;
import com.justeat.gamemanager.service.GameService;

@Service
public class GameServiceImp implements GameService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private GameManager gameManager;

	@Autowired
	private GameRepository gameRepository;

	@Value("${game.player_api}")
	private String playerAPI;

	private void start(Game game, String starterId) {

		System.out.println("Game started");

		if (game.getStatus() == GameStatus.READY) {

			Player currentPlayer;

			if (game.getPlayer1().getId() == starterId) {
				currentPlayer = game.getPlayer1();

			} else if (game.getPlayer2().getId() == starterId) {
				currentPlayer = game.getPlayer2();
			} else {
				// not match with any game player
				return;
			}
			
			game.setStatus(GameStatus.INPROGRESS);
			ResponseEntity<Move> response;
			try {
				String url = playerAPI + "start?playerId=" + currentPlayer.getId();
				response = restTemplate.getForEntity(url, Move.class);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				this.terminateGame(game);
				return;
			}
			
			Move move = response.getBody();
			System.out.println(move.toString());
			game.addMove(move);
			while (game.getStatus() != GameStatus.FINISHED) {
				move = this.play(currentPlayer.getId(), move.getNumSend());
				// if there is any issue while playing, we will terminate the game and exit
				if (move == null) {
					this.terminateGame(game);
					break;
				}
				game.addMove(move);
				if (move.getNumSend() == 1) {
					// game.setWinnerId(move.getPlayerId());
					this.finishGame(game);
				} else {
					currentPlayer = game.getPlayer1().equals(currentPlayer) ? game.getPlayer2() : game.getPlayer1();
				}

			}

		}
	}

	private Move play(String playerId, int num) {
		
		try {
			String url = playerAPI + "play?playerId=" + playerId + "&num=" + num;
			ResponseEntity<Move> response = restTemplate.getForEntity(url, Move.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				Move move = response.getBody();
				System.out.println(move.toString());
				return move;
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public Player[] inviteTowPlayers(Game game) {

		Player[] players = this.invitePlayers(2);
		
		if (players != null && players[0] != null) {
			game.setPlayer1(players[0]);
			System.out.println(players[0].getId());
			if (players[1] != null) {
				System.out.println(players[1].getId());
				game.setPlayer2(players[1]);
				game.setStatus(GameStatus.READY);
				this.start(game, game.getPlayer1().getId());

			}
			return players;
		}

		return null;
	}

	public Player inviteOnePlayer(Game game) {
		Player[] players = this.invitePlayers(1);

		if (players[0] != null) {
			if (game.getPlayer1() == null) {
				game.setPlayer1(players[0]);
			} else {

				game.setPlayer2(players[0]);
				game.setStatus(GameStatus.READY);

				this.start(game, game.getPlayer1().getId());
			}

			return players[0];
		}
		return null;
	}

	public boolean askForInvitation() {
		
		if (this.gameManager.getGame() == null) {
			// create game will invite 2 players
			 return this.gameManager.createGame(); 

		}

		// incase the game is created, but there was no players, let us invite 2 players
		if (this.gameManager.getGame().getPlayer1() == null && this.gameManager.getGame().getPlayer2() == null) {
			Player[] players = this.inviteTowPlayers(this.gameManager.getGame());
			
			return players!=null&&players[0]!=null ;
		}
		if (this.gameManager.getGame().getStatus() == GameStatus.PENDING) {
			Player player = this.inviteOnePlayer(this.gameManager.getGame());
			return player!=null;
		}
		return false;
	}

	public List<Game> getAll() {
		return this.gameRepository.getAll();
	}

	/*
	 * It will change the atatus to FINISHED, archive the game, and create a new
	 * game for new players
	 */
	private void finishGame(Game game) {
		game.setStatus(GameStatus.FINISHED);
		this.gameRepository.archive(game);
		// let us create a new game for the game manager, then we can use it for other
		// players
		this.gameManager.createGame();
		System.out.println("Game has been finished and a new game has been created");
	}
	
	/*
	 * It will change the atatus to FINISHED, archive the game, and create a new
	 * game for new players
	 */
	private void terminateGame(Game game) {
		game.setStatus(GameStatus.TERMINATED);
		this.gameRepository.archive(game);
		// let us create a new game for the game manager, then we can use it for other
		// players
		this.gameManager.createGame();
		System.out.println("Game has been terminated and a new game has been created");
	}

	private Player[] invitePlayers(int numOfPlayers) {
		try {
			String url = playerAPI + "/invite?num=" + numOfPlayers;
			ResponseEntity<Player[]> response = restTemplate.getForEntity(url, Player[].class);
			if (response.getStatusCode() == HttpStatus.OK) {
				Player[] players = response.getBody();

				return players;

			}

			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
