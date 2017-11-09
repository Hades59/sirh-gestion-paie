/**
 * 
 */
package dev.paie.web.controller;

/**
 * @author ETY3
 *
 */
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationRepository;
import dev.paie.service.CalculerRemunerationServiceSimple;

/**
 * @author ETY9
 *
 */
@Controller
@RequestMapping("/bulletin")
public class BulletinController {

	@Autowired private PeriodeRepository repoPer;
	@Autowired private RemunerationRepository repoEmp;
	@Autowired private BulletinRepository repoBull;

	@Autowired private CalculerRemunerationServiceSimple calcRem;
	
	@RequestMapping(value="/creer", method = RequestMethod.GET)
	public ModelAndView creer(){
		List<Periode> listPer = repoPer.findAll();
		List<RemunerationEmploye> listRem = repoEmp.findAll();
		
		BulletinSalaire bull = new BulletinSalaire();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/creerBulletin");
		mv.addObject("listPer", listPer);
		mv.addObject("listRem", listRem);
		mv.addObject("formBull", bull);
	
		return mv;
	}
	
	@RequestMapping(value="/creer", method = RequestMethod.POST)
	public String formSubmit(@RequestParam("remunerationEmploye") String idRemEmp, 
			@RequestParam("periode") String idPer,
			@RequestParam("primeExceptionnelle") String prime){
		
		BulletinSalaire bull = new BulletinSalaire();
		bull.setPeriode(repoPer.findOne(Integer.parseInt(idPer)));
		bull.setRemunerationEmploye(repoEmp.findOne(Integer.parseInt(idRemEmp)));
		bull.setPrimeExceptionnelle(new BigDecimal(prime));
		bull.setDateHeureCreation(LocalDateTime.now());
		
		repoBull.save(bull);
		
		return "redirect:/mvc/bulletin/lister";
	}
	
	@RequestMapping(value="/lister", method = RequestMethod.GET)
	@Transactional
	public ModelAndView lister(){
		
		ModelAndView mav = new ModelAndView();
		List<BulletinSalaire> listBull = repoBull.findAll();
		Map<BulletinSalaire, ResultatCalculRemuneration> mapBull = new TreeMap<>();
		listBull.stream().forEach(b->mapBull.put(b, calcRem.calculer(b)));
		mav.setViewName("bulletin/listerBulletins");
		mav.addObject("mapBull", mapBull);
		
		return mav;
	}
	
	@RequestMapping(value="/visualiser/{id}", method = RequestMethod.GET)
	@Transactional
	public ModelAndView visualiser(@PathVariable String id){
		
		ModelAndView mav = new ModelAndView();
		BulletinSalaire bull = repoBull.findOne(Integer.parseInt(id));
	
		mav.setViewName("bulletin/visualiserBulletin");
		mav.addObject("bulletin", bull);
		mav.addObject("calcRem", calcRem.calculer(bull));
		
		return mav;
	}
}