package fr.afcepf.al32.wspromotemplate.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class Discount extends PromotionType {

	@Column(name = "discount_value")
	@NotNull
	@Min(0)
	private Double discountValue;

	@Column(name = "min_purchase_amount")
	private Double minPurchaseAmount;

	public Double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Double discountValue) {
		this.discountValue = discountValue;
	}

	public Double getMinPurchaseAmount() {
		return minPurchaseAmount;
	}

	public void setMinPurchaseAmount(Double minPurchaseAmount) {
		this.minPurchaseAmount = minPurchaseAmount;
	}

}
