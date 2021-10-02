package com.justeat.gamemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.justeat.gamemanager.config.GameManager;
import com.justeat.gamemanager.dto.Game;
import com.justeat.gamemanager.service.GameService;

@Controller
@RequestMapping(value = "/game")
public class GameController {


	@Autowired
	private GameManager gameManager;

	@Autowired
	private GameService gameService;

	@RequestMapping(value = "/start")
	public ModelAndView start() {

		if (gameManager.getGame() == null) {
			this.gameManager.createGame();
		}

		ModelAndView mv = new ModelAndView();
		mv.setViewName("game");
		Game game = this.gameManager.getGame();
		mv.addObject("message", "Game status: " + game.getStatus() );

		return mv;
	}

	@RequestMapping(value = "/moves")
	public ModelAndView play() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("moves");

		mv.addObject("games", this.gameService.getAll());

		return mv;
	}

}
