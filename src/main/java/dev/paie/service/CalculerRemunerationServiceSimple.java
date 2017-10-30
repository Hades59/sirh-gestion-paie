package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;
import java.math.BigDecimal;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	PaieUtils paieUtils;
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		ResultatCalculRemuneration res = new ResultatCalculRemuneration();
		
		String salaireBase = paieUtils.formaterBigDecimal(bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase().multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase()));
		res.setSalaireDeBase(salaireBase);
		
		String salaireBrut = paieUtils.formaterBigDecimal((new BigDecimal(salaireBase)).add(bulletin.getPrimeExceptionnelle()));
		res.setSalaireBrut(salaireBrut);
		
		BigDecimal totalRetenueSalariale = formaterBigDecimal((new BigDecimal(bulletin)).getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().stream()
				.filter(cotNonImp -> cotNonImp.getTauxSalarial() != null)
				.map(cotNonImp-> cotNonImp.getTauxSalarial().multiply(salaireBrut))
				.reduce((cot, cotbis) -> cot.add(cotbis).get()));
		res.setTotalRetenueSalarial(paieUtils.totalRetenueSalariale);
		
		BigDecimal totalCotisationsPatronales = formaterBigDecimal((new BigDecimal(bulletin)).getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables().stream()
				.filter(cotNonImp -> cotNonImp.getTauxPatronal() != null)
				.map(cotNonImp-> cotNonImp.getTauxPatronal().multiply(salaireBrut))
				.reduce((cot, cotbis) -> cot.add(cotbis).get()));
		res.setTotalCotisationsPatronales(totalRetenueSalariale);
		
		String netImposable = formaterBigDecimal((new BigDecimal(salaireBrut)).subtract(totalRetenueSalariale));
		res.setNetImposable(netImposable);		
		
		String netAPayer = formaterBigDecimal((new BigDecimal(netImposable)).subtract(totalRetenueSalariale));
		res.setNetAPayer(netAPayer);
		
		return res;
	}
}
