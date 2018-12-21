package fr.afcepf.al32.wspromotemplate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
public class PercentType extends PromotionType {

	@Column(name = "percent_value")
	@NotNull
	private Double percentValue;

	@Column(name = "min_purchase_amount")
	private Double minPurchaseAmount;

	public PercentType(Long promotionTypeId, @NotNull Double percentValue, Double minPurchaseAmount) {
		super(promotionTypeId);
		this.percentValue = percentValue;
		this.minPurchaseAmount = minPurchaseAmount;
	}

	public Double getPercentValue() {
		return percentValue;
	}

	public void setPercentValue(Double percentValue) {
		this.percentValue = percentValue;
	}

	public Double getMinPurchaseAmount() {
		return minPurchaseAmount;
	}

	public void setMinPurchaseAmount(Double minPurchaseAmount) {
		this.minPurchaseAmount = minPurchaseAmount;
	}

}
