package com.justeat.gamemanager.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Game {
	
	private String gameId;	
	private Player player1;
	private Player player2;

	private GameStatus status;
	private List<Move> moves;
	
	public Game(){
		this.status = GameStatus.PENDING;
		this.moves = new ArrayList<>();
		this.gameId = UUID.randomUUID().toString();
	}

	public String getGameId() {
		return gameId;
	}

	private void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public String getWinnerId() {
		if (this.moves.isEmpty()) {
			return "";
		}
		if(this.getStatus() != GameStatus.FINISHED) {
			return "";
		}
		return this.getMoves().get(this.getMoves().size()-1).getPlayerId();
	}

	

	public String getStarterId() {
		if (this.moves.isEmpty()) {
			return "";
		}
		if(this.getStatus() != GameStatus.FINISHED) {
			return "";
		}
		return this.getMoves().get(this.getMoves().size()-1).getPlayerId();
	}


	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void addMove(Move move) {
		move.setGameId(this.getGameId());
		this.moves.add(move);
	}
	
	/*
	 public String getStarterId() {
		if (this.getMoves().get(0)== null) {
			return "";
		}
		return this.getMoves().get(0).getPlayerId();
	}
	 */


}
