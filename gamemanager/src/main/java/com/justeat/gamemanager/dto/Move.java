package com.justeat.gamemanager.dto;

public class Move {
	
	private int numReceived;
	private int numSend;
	private int added;
	private String playerId;
	private String gameId;
	
	public Move(String gameId,int numReceived, int numSend, int added, String playerId) {
		super();
		this.numReceived = numReceived;
		this.numSend = numSend;
		this.added = added;
		this.playerId = playerId;
		this.gameId = gameId;
	}
	
	public Move() {
		
	}
	
	public int getNumReceived() {
		return numReceived;
	}
	public void setNumReceived(int numReceived) {
		this.numReceived = numReceived;
	}
	public int getNumSend() {
		return numSend;
	}
	public void setNumSend(int numSend) {
		this.numSend = numSend;
	}
	public int getAdded() {
		return added;
	}
	public void setAdded(int added) {
		this.added = added;
	}
	public String getPlayerId() {
		return playerId;
	}
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Move : "+playerId+" / "+numReceived+" / "+numSend+" / "+added;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	
}
