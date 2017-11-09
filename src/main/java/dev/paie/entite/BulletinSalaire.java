package dev.paie.entite;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bulletinSalaire")
public class BulletinSalaire implements Comparable<BulletinSalaire> {
	
	/** id : Integer */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/** remunerationEmploye : RemunerationEmploye */
	@ManyToOne
	private RemunerationEmploye remunerationEmploye;
	
	/** periode : Periode */
	@ManyToOne
	private Periode periode;
	
	/** primeExceptionnelle : BigDecimal */
	@Column(name = "primeExceptionnelle", precision = 5, scale = 2, nullable = false)
	private BigDecimal primeExceptionnelle;
	
	/** dateHeureCreation : LocalDateTime */
	@Column(name = "date_heure_creation")
	private LocalDateTime dateHeureCreation;
	
	public RemunerationEmploye getRemunerationEmploye() {
		return remunerationEmploye;
	}
	public void setRemunerationEmploye(RemunerationEmploye remunerationEmploye) {
		this.remunerationEmploye = remunerationEmploye;
	}
	public Periode getPeriode() {
		return periode;
	}
	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
	public BigDecimal getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}
	public void setPrimeExceptionnelle(BigDecimal primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
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
	
	@Override
    public int compareTo(BulletinSalaire bulletinSalaire) {
        if(this.dateHeureCreation.compareTo(bulletinSalaire.dateHeureCreation) == 0){
            if (this.id == bulletinSalaire.id){
                return 0;
            }
            else if (this.id > bulletinSalaire.id){
                return 1;
            }
            else {
                return -1;
            }
        }
         return this.dateHeureCreation.compareTo(bulletinSalaire.dateHeureCreation);
    }   
	
}
