package com.justeat.player.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import com.justeat.player.dto.Player;
import com.justeat.player.service.PlayerService;

@Controller
@RequestMapping(value = "/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@RequestMapping(value = "/new")
	public ModelAndView newPlayer(@RequestParam(name = "auto", defaultValue = "true") boolean auto) {

		String message = "";
		String sessionID = RequestContextHolder.currentRequestAttributes().getSessionId();
		if (this.playerService.getPlayerById(sessionID) == null) {

			Player player = this.playerService.createPlayer(sessionID, auto);

			message = "A new player has been created with id = " + player.getId();
		} else {
			message = "This player is already created!";
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("player");

		mv.addObject("message", message);

		return mv;
	}

	@RequestMapping(value = "/play")
	public ModelAndView play() {
		

    	String id = RequestContextHolder.currentRequestAttributes().getSessionId();


		String message = "";

		boolean started = this.playerService.startGameForPlayer(id);
		if (started) {
			message = "Added a player to waiting list, and he will be palying whene there is an availabe game";
		} else {
			message = "Faild: this player can not play";
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("player");

		mv.addObject("message", message);

		return mv;
	}

	@RequestMapping(value = "/all")
	public ModelAndView all() {

		this.playerService.getAllPlayers();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("player");
		mv.addObject("message", Arrays.toString((this.playerService.getAllPlayers().toArray())));
		return mv;
	}

}