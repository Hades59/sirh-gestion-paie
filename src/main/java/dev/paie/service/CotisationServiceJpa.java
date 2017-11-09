/**
 * 
 */
package dev.paie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

/**
 * @author ETY3
 *
 */
@Repository
public class CotisationServiceJpa implements CotisationService {

	@PersistenceContext private EntityManager em;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, timeout = 60)
	public void sauvegarder(Cotisation nouvelleCotisation) {
		em.persist(nouvelleCotisation);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, timeout = 60)
	public void mettreAJour(Cotisation cotisation) {
		Cotisation c =em.find(Cotisation.class, cotisation.getId());
		c.setCode(cotisation.getCode());
		c.setLibelle(cotisation.getLibelle());
		c.setTauxPatronal(cotisation.getTauxPatronal());
		c.setTauxSalarial(cotisation.getTauxSalarial());
	}

	@Override
	public List<Cotisation> lister() {
		TypedQuery<Cotisation> q = em.createQuery("From Cotisation c", Cotisation.class);
		List<Cotisation> l = q.getResultList();
		return l;
	}
}
