/**
 * 
 */
package dev.paie.service;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.management.relation.Role;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRepository;

/**
 * @author ETY3
 *
 */
@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	private EntityManager em;

	@Autowired
	GradeRepository grades;

	@Autowired
	EntrepriseRepository entreprises;

	@Autowired
	ProfilRepository profils;

	@Autowired 
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void initialiser() {
		
//		String iciUnMotDePasse = "topSecret";
//		String iciMotDePasseHashe = this.passwordEncoder.encode(iciUnMotDePasse);

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"cotisations-imposables.xml",
				"cotisations-non-imposables.xml",
				"entreprises.xml",
				"grades.xml",
				"profils-remuneration.xml"
				);

		//Gère la sauvegarde de chaque classe
		Stream.of(Cotisation.class, Entreprise.class, Grade.class, ProfilRemuneration.class)
		.forEach(classe -> context.getBeansOfType(classe)
				.values()
				.stream()
				.forEach(object -> em.persist(object))
				);
		
		
		//Gestion des périodes
		List<Periode> periodes = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			LocalDate firstDayOfMonth = LocalDate.of(2017, i, 1);
			periodes.add(new Periode(firstDayOfMonth, firstDayOfMonth.with(lastDayOfMonth())));
			em.persist(periodes.get(i-1));
		}
		
		//Insérer utilisateurs de différents profils au démarrage
		em.persist(new Utilisateur("coucou", passwordEncoder.encode("coucou"), true, ROLES.ROLE_ADMINISTRATEUR));
		em.persist(new Utilisateur("hello", passwordEncoder.encode("hello"), true, ROLES.ROLE_UTILISATEUR));
		//String nomUtilisateur, String motDePasse, Boolean estActif, ROLES role
	}

}
