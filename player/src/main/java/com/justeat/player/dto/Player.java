package com.justeat.player.dto;

public class Player {
	
	private Integer sequance;
	private String id;
	private String showName;
	private boolean autoPlayer;
	
	
	public Player(String id, boolean auto) {	
		this.id = id;		
		this.autoPlayer = auto;
	}
	
	public String getId() {
		return id;
	}
	
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	
	public boolean isAutoPlayer() {
		return autoPlayer;
	}

	public void setAutoPlayer(boolean autoPlayer) {
		this.autoPlayer = autoPlayer;
	}

	public Integer getSequance() {
		return sequance;
	}

	public void setSequance(Integer sequance) {
		this.sequance = sequance;
	}
	
	

}
