package dev.paie.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeMapper implements RowMapper <Grade> {

	@Override
	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Grade g = new Grade();
		g.setCode(rs.getString("code"));
		g.setNbHeuresBase(rs.getBigDecimal("nbHeuresBase"));
		g.setTauxBase(rs.getBigDecimal("tauxBase"));
		
		return g;		
	}

}
