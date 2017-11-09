package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;
import java.math.BigDecimal;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	PaieUtils paieUtils;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {

		ResultatCalculRemuneration res = new ResultatCalculRemuneration();

		String salaireBase = paieUtils.formaterBigDecimal(bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase()
				.multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase()));
		res.setSalaireDeBase(salaireBase);

		String salaireBrut = paieUtils
				.formaterBigDecimal((new BigDecimal(salaireBase)).add(bulletin.getPrimeExceptionnelle()));
		res.setSalaireBrut(salaireBrut);

		String totalRetenueSalariale = paieUtils
				.formaterBigDecimal(bulletin.getRemunerationEmploye().getProfilRemuneration()
						.getCotisationsNonImposables().stream().filter(cotNonImp -> cotNonImp.getTauxSalarial() != null)
						.map(cotNonImp -> cotNonImp.getTauxSalarial().multiply(new BigDecimal(salaireBrut)))
						.reduce((cot, cotbis) -> cot.add(cotbis)).get());
		res.setTotalRetenueSalarial(totalRetenueSalariale);

		String totalCotisationsPatronales = paieUtils
				.formaterBigDecimal(bulletin.getRemunerationEmploye().getProfilRemuneration()
						.getCotisationsNonImposables().stream().filter(cotNonImp -> cotNonImp.getTauxPatronal() != null)
						.map(cotNonImp -> cotNonImp.getTauxPatronal().multiply(new BigDecimal(salaireBrut)))
						.reduce((cot, cotbis) -> cot.add(cotbis)).get());
		res.setTotalCotisationsPatronales(totalCotisationsPatronales);

		String netImposable = paieUtils
				.formaterBigDecimal((new BigDecimal(salaireBrut)).subtract(new BigDecimal(totalRetenueSalariale)));
		res.setNetImposable(netImposable);

		String netAPayer = paieUtils.formaterBigDecimal(
				new BigDecimal(netImposable).subtract(bulletin.getRemunerationEmploye()
						.getProfilRemuneration()
						.getCotisationsImposables()
						.stream()
						.filter(cotisation -> cotisation.getTauxSalarial() != null)
						.map(cotisation -> cotisation
								.getTauxSalarial().multiply(new BigDecimal(salaireBrut)))
						.reduce(BigDecimal::add)
						.orElse(BigDecimal.ZERO)));
		res.setNetAPayer(netAPayer);

		return res;
	}
}
