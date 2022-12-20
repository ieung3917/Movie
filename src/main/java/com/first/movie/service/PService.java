package com.first.movie.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.first.movie.dao.MDAO;
import com.first.movie.dao.PDAO;
import com.first.movie.dao.TDAO;
import com.first.movie.dao.TMDAO;
import com.first.movie.dto.MEMBER;
import com.first.movie.dto.PAGE;
import com.first.movie.dto.PAY;
import com.first.movie.dto.TICKET;
import com.first.movie.dto.TIMEMOVIE;


@Service
public class PService {

		// ModelAndView 
		private ModelAndView mav = new ModelAndView();
		
		// DAO
		@Autowired
		private PDAO pdao;

		// DAO
		@Autowired
		private TDAO tdao;

		// DAO
		@Autowired
		private TMDAO tmdao;

		// MDAO
		@Autowired
		private MDAO mdao;
		
		// session 
		@Autowired
		private HttpSession session;
		
		//
		@Autowired
		private HttpServletRequest request;
		
		public ModelAndView payList(String payId) {
			System.out.println("(serv)payId : " + payId);

			List<PAY> pay = pdao.payList(payId);
			
			
			
			// model
			mav.addObject("p", pay);	
			
			// view
			mav.setViewName("movie-checkout");

			System.out.println("(serv)pay : " + pay);
			
			return mav;
		}

		public ModelAndView payKakao(String payId) {
			
			PAY pay = pdao.payKakao(payId);
			
			mav.addObject("pay", pay);
			mav.setViewName("kakaoPay");
			
			return mav;
		}

		public List<PAY> chunwonSale(String payId) {
			List<PAY> payList;
			
			int result = pdao.chunwonSale(payId);
			
			if(result > 0) {
				
				payList = pdao.payList(payId);
				
			} else {
				
				payList = null;
				
			}
			
			
			
			return payList;
		}
		
		public List<PAY> samchunwonSale(String payId) {
			List<PAY> payList;
			
			int result = pdao.samchunwonSale(payId);
			
			if(result > 0) {
				
				payList = pdao.payList(payId);
				
			} else {
				
				payList = null;
				
			}
			
			
			
			return payList;
		}
		
		public List<PAY> ochunwonSale(String payId) {
			List<PAY> payList;
			
			int result = pdao.ochunwonSale(payId);
			
			if(result > 0) {
				
				payList = pdao.payList(payId);
				
			} else {
				
				payList = null;
				
			}
			
			
			
			return payList;
		}

		public ModelAndView payTic(String payId) {
			System.out.println("(serv)(payTic)payId : " + payId);

			List<PAY> pay = pdao.payList(payId);
			
			System.out.println("(payTic)" + pay);
			
			int result = tdao.ticPay(pay.get(0));

			System.out.println("(serv)result : " + result);
			
			if(result>0) {
			
			mav.setViewName("redirect:/ticList?ticId=" + payId);
			int result1 = pdao.payDelete(payId);
			
			}
			
			else {
				mav.setViewName("index");
			}

			
			return mav;
		}

		public ModelAndView payDelete(String payId) {
			
			int result = pdao.payDelete(payId);
			
			if(result > 0) {
				mav.setViewName("index");
			} 
			
			return mav;
		}

		public ModelAndView payInsert(PAY pay) {

			int result = pdao.payInsert(pay);
			
			return mav;
		}

		

}
