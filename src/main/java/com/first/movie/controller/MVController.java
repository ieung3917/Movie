package com.first.movie.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.first.movie.dto.COMMENT;
import com.first.movie.service.MService;
import com.first.movie.service.MVService;

@Controller
public class MVController {

	ModelAndView mav = new ModelAndView();

	@Autowired
	MVService mvsvc;

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/sports-ticket", method = RequestMethod.GET)
	public ModelAndView sportsticket() {
		
		mvsvc.crl();
		
		mav.setViewName("redirect:/index");
		
		return mav;
	}

}