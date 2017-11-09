package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "code", length = 10, nullable = false)
	private String code;
	@Column(name = "nbHeuresBase", precision = 5, scale = 2, nullable = true )
	private BigDecimal nbHeuresBase;
	@Column(name = "tauxBase", precision = 5, scale = 2, nullable = true)
	private BigDecimal tauxBase;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getNbHeuresBase() {
		return nbHeuresBase;
	}
	public void setNbHeuresBase(BigDecimal nbHeuresBase) {
		this.nbHeuresBase = nbHeuresBase;
	}
	public BigDecimal getTauxBase() {
		return tauxBase;
	}
	public void setTauxBase(BigDecimal tauxBase) {
		this.tauxBase = tauxBase;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getSalaireAnnuel(){
		return tauxBase.multiply(nbHeuresBase).multiply(new BigDecimal(12)).intValue() + " €";
	}

}
