package com.first.movie.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.first.movie.dto.COMMENT;
import com.first.movie.dto.MOVIE;

@Repository
public class MVDAO {

	@Autowired
	SqlSessionTemplate sql;
	
	public int movieData(MOVIE movie) {
		
		
		return sql.insert("TimeMovie.movData",movie);
	}

	public void movDel() {
		
		sql.delete("TimeMovie.movDel");
		
	}
	
	}
