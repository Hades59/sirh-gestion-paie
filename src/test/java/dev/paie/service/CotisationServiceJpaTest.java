/**
 * 
 */
package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.config.JpaConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;

/**
 * @author ETY3
 *
 */
//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = {ServicesConfig.class})
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	
	@Autowired private CotisationService cotisationService;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder une nouvelle cotisation
		Cotisation cot = new Cotisation();
		cot.setCode("M01");
		cot.setLibelle("Meeko");
		cot.setTauxPatronal(new BigDecimal("13.50"));
		cot.setTauxSalarial(new BigDecimal("1.20"));
		cotisationService.sauvegarder(cot);
		
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		cotisationService.lister().stream().forEach(c1 -> System.out.println(c1.getId()+" "+c1.getCode()+" "+c1.getLibelle()));
		assertThat(cotisationService.lister().get(0).getCode()).isEqualTo("M01");
		
		// TODO modifier une cotisation
		Cotisation c2 = cotisationService.lister().get(0);
		c2.setCode("M02");
		c2.setLibelle("Flit");
		cotisationService.mettreAJour(c2);
		
		// TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		cotisationService.lister().stream().forEach(c1 -> System.out.println(c1.getId()+" "+c1.getCode()+" "+c1.getLibelle()));
		assertThat(c2.getCode()).isEqualTo("M02");
	}
}
