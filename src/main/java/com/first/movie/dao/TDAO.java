package com.first.movie.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.movie.dto.MEMBER;
import com.first.movie.dto.PAY;
import com.first.movie.dto.TICKET;



@Repository
public class TDAO {

	// sql(mapper)로 연결
	@Autowired
	private SqlSessionTemplate sql;
	
	public List<TICKET> ticList(String ticId) {
		System.out.println("(dao)ticName : " + ticId);
		return sql.selectList("Ticket.ticList", ticId);
	}

	public int ticPay(PAY pay) {
		System.out.println("(payTic) (dao) pay : " + pay);
		return sql.insert("Ticket.ticPay", pay);
	}
}
