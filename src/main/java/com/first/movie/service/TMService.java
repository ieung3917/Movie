package com.first.movie.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.first.movie.dao.MDAO;
import com.first.movie.dao.TMDAO;
import com.first.movie.dto.MEMBER;
import com.first.movie.dto.TICKET;
import com.first.movie.dto.TIMEMOVIE;

@Service
public class TMService {

			// ModelAndView 
			private ModelAndView mav = new ModelAndView();
			
			// DAO
			@Autowired
			private TMDAO tmdao;
			
			// session 
			@Autowired
			private HttpSession session;
			
			//
			@Autowired
			private HttpServletRequest request;
	
	public ModelAndView movList(String timMovName) {
		System.out.println("(serv)timMovName : " + timMovName);

		List<TIMEMOVIE> timeMovie = tmdao.movList(timMovName);
		
		
		// model
		mav.addObject("tm", timeMovie);
		
		TIMEMOVIE one = timeMovie.get(0);	// 첫번째 튜플 전체를 가져옴
		
		mav.addObject("timMovName", one.getTimMovName());
		mav.addObject("timBackPic", one.getTimBackPic());
		
		
		// view
		mav.setViewName("movie-ticket-plan");

		System.out.println("(serv)timeMovie : " + timeMovie);
		
		return mav;
	}

	
}
