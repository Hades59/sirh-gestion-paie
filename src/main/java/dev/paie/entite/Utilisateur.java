/**
 * 
 */
package dev.paie.entite;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ETY3
 *
 */
@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	public enum ROLES {
		ROLE_ADMINISTRATEUR, ROLE_UTILISATEUR
	}

	/** id : Integer */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	/** nomUtilisateur : String */
	private String nomUtilisateur;
	
	/** motDePasse : String */
	private String motDePasse;
	
	/** estActif : Boolean */
	private Boolean estActif;
	
	/** role : ROLES */
	@Enumerated(EnumType.STRING)
	private ROLES role;
	
	/**
	 * @param nomUtilisateur
	 * @param motDePasse
	 * @param estActif
	 * @param role
	 */
	public Utilisateur(String nomUtilisateur, String motDePasse, Boolean estActif, ROLES role) {
		super();
		this.nomUtilisateur = nomUtilisateur;
		this.motDePasse = motDePasse;
		this.estActif = estActif;
		this.role = role;
	}

	/** Getter for id
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/** Setter for id
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/** Getter for nomUtilisateur
	 * @return the nomUtilisateur
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	/** Setter for nomUtilisateur
	 * @param nomUtilisateur the nomUtilisateur to set
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	/** Getter for motDePasse
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	/** Setter for motDePasse
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	/** Getter for estActif
	 * @return the estActif
	 */
	public Boolean getEstActif() {
		return estActif;
	}
	/** Setter for estActif
	 * @param estActif the estActif to set
	 */
	public void setEstActif(Boolean estActif) {
		this.estActif = estActif;
	}
	/** Getter for role
	 * @return the role
	 */
	public ROLES getRole() {
		return role;
	}
	/** Setter for role
	 * @param role the role to set
	 */
	public void setRole(ROLES role) {
		this.role = role;
	}
}
