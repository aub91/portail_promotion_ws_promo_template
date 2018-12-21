package fr.afcepf.al32.wspromotemplate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
public class Pack extends PromotionType {
	
	@Column(name="number_purchased")
	@NotNull
	private Integer numberPurchased;
	
	@Column(name="number_offered")
	@NotNull
	private Integer numberOffered;
	
	
	public Integer getNumberPurchased() {
		return numberPurchased;
	}
	public void setNumberPurchased(Integer numberPurchased) {
		this.numberPurchased = numberPurchased;
	}
	public Integer getNumberOffered() {
		return numberOffered;
	}
	public void setNumberOffered(Integer numberOffered) {
		this.numberOffered = numberOffered;
	}
	
}
