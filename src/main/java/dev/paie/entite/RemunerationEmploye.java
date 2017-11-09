package dev.paie.entite;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "remunerationEmploye")
public class RemunerationEmploye {
	
	/** id : Integer */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //Identity utilise l'auto increm de l'id
	private Integer id;
	
	/** matricule : String */
	private String matricule;
	
	/** entreprise : Entreprise */
	@ManyToOne
	private Entreprise entreprise;
	
	/** profilRemuneration : ProfilRemuneration */
	@ManyToOne
	private ProfilRemuneration profilRemuneration;
	
	/** grade : Grade */
	@ManyToOne
	private Grade grade;
	
	/** dateHeureCreation : LocalDateTime */
	@Column
	private LocalDateTime dateHeureCreation;
	
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public ProfilRemuneration getProfilRemuneration() {
		return profilRemuneration;
	}
	public void setProfilRemuneration(ProfilRemuneration profilRemuneration) {
		this.profilRemuneration = profilRemuneration;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/** Getter for dateHeureCreation
	 * @return the dateHeureCreation
	 */
	public LocalDateTime getDateHeureCreation() {
		return dateHeureCreation;
	}
	/** Setter for dateHeureCreation
	 * @param dateHeureCreation the dateHeureCreation to set
	 */
	public void setDateHeureCreation(LocalDateTime dateHeureCreation) {
		this.dateHeureCreation = dateHeureCreation;
	}
}
