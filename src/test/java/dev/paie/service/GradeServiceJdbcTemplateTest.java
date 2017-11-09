/**
 * 
 */
package dev.paie.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.config.JpaConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

/**
 * @author ETY3
 *
 */
//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = {ServicesConfig.class, DataSourceMySQLConfig.class, JpaConfig.class})
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired private GradeService gradeService;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouveau grade
		Grade g = new Grade();
		g.setCode("M01");
		g.setNbHeuresBase(new BigDecimal("9.3"));
		g.setTauxBase(new BigDecimal("12"));
		gradeService.sauvegarder(g);
		// TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		gradeService.lister();
		// TODO modifier un grade
		g.setCode("M01");
		g.setNbHeuresBase(new BigDecimal("6.3"));
		g.setTauxBase(new BigDecimal("9"));
		gradeService.mettreAJour(g);
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		gradeService.lister();
	}
}

