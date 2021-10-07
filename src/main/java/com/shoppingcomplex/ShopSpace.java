package com.shoppingcomplex;

import java.math.BigDecimal;

public class ShopSpace {
	
	private double shopArea;
	private BigDecimal shopFee;
	
	public ShopSpace(double shopArea, BigDecimal shopFee) {
		super();
		this.shopArea = shopArea;
		this.shopFee = shopFee;
	}

	public double getShopArea() {
		return shopArea;
	}
	public void setShopArea(float shopArea) {
		this.shopArea = shopArea;
	}
	public BigDecimal getShopFee() {
		return shopFee;
	}
	public void setShopFee(BigDecimal shopFee) {
		this.shopFee = shopFee;
	}

	@Override
	public String toString() {
		return "ShopSpace [shopArea=" + shopArea + ", shopFee=" + shopFee + "]";
	}
	
}
