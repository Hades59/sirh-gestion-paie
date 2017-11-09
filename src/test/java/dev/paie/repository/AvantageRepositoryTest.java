/**
 * 
 */
package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

/**
 * @author ETY3
 *
 */
//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = {ServicesConfig.class, Avantage.class})
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {
	
	@Autowired private AvantageRepository avantageRepository;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
	// TODO sauvegarder un nouvel avantage
		Avantage avtg = new Avantage();
		avtg.setCode("A01");
		avtg.setNom("Curtis");
		avtg.setMontant(new BigDecimal("10.2"));
		avantageRepository.save(avtg);
		
	// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		Avantage a1 = avantageRepository.findOne(avtg.getId());
		assertThat(a1.getId()).isEqualTo(avtg.getId());
		assertThat(a1.getCode()).isEqualTo(avtg.getCode());
		assertThat(a1.getNom()).isEqualTo(avtg.getNom());
		assertThat(a1.getMontant().setScale(2)).isEqualTo(avtg.getMontant().setScale(2));
		
	// TODO modifier un avantage
		a1.setCode("A02");
		avantageRepository.save(a1);
		
	// TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
		Avantage a2 = avantageRepository.findOne(a1.getId());
		assertThat(a2.getCode()).isEqualTo(a1.getCode());
	}
}
