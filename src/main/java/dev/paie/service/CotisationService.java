/**
 * 
 */
package dev.paie.service;

import java.util.List;

import dev.paie.entite.Cotisation;

/**
 * @author ETY3
 *
 */
public interface CotisationService {
	void sauvegarder(Cotisation nouvelleCotisation);
	void mettreAJour(Cotisation cotisation);
	List<Cotisation> lister();
}
