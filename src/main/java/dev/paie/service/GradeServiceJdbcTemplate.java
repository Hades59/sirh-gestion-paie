/**
 * 
 */
package dev.paie.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;
import dev.paie.util.GradeMapper;

/**
 * @author ETY3
 *
 */
@Service
public class GradeServiceJdbcTemplate implements GradeService {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Grade (code, nbHeuresBase, tauxBase) VALUES(?,?,?)";
		this.jdbcTemplate.update(sql,  nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(), nouveauGrade.getTauxBase());
	}

	@Override
	public void mettreAJour(Grade grade) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Grade set code = ? where nbHeuresBase = ? AND tauxBase = ?";
		this.jdbcTemplate.update(sql, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase());
	}

	@Override
	public List<Grade> lister() {
		String sql ="SELECT * FROM GRADE";
		return this.jdbcTemplate.query(sql, new GradeMapper());
	}
}
