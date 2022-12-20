package com.first.movie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.first.movie.service.MService;
import com.first.movie.service.TMService;

@Controller
public class TMController {
	
	// ModelAndView 객체 생성
		private ModelAndView mav = new ModelAndView();

		// MemberService 연결
		@Autowired
		private TMService tmsvc;

		@Autowired
		private HttpSession session;
	
	@RequestMapping(value = "/movList", method = RequestMethod.GET)
	public ModelAndView movList(@RequestParam("timMovName") String timMovName) {

		System.out.println("[1] jsp → controller \n timMovName : " + timMovName);

		mav = tmsvc.movList(timMovName);

//			System.out.println("[5] service → controller \n mav : " + mav);

		return mav;
	}
	
	
	
}
