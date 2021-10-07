package com.shoppingcomplex;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RandomShopGenerator {

	private static final double FIX_CHARGES = 4.0;

	private double shopArea;
	private BigDecimal minPricePerShop;

	public RandomShopGenerator() {
		super();
		this.shopArea = 30.0;
		this.minPricePerShop = new BigDecimal(3000.0);
	}

	public RandomShopGenerator(double minArea, BigDecimal minPricePerSquareMeter) {
		super();
		this.shopArea = minArea;
		this.minPricePerShop = minPricePerSquareMeter;
	}

	public ShopSpace generate() {
		double maxArea = shopArea * FIX_CHARGES;
		BigDecimal maxPricePerSquareMeter = minPricePerShop.multiply(new BigDecimal(FIX_CHARGES));

		double area = Math.round((shopArea + Math.random() * (maxArea - shopArea)) * 10) / 10;
		BigDecimal pricePerSquareMeter = minPricePerShop
				.add((new BigDecimal(Math.random()).multiply(maxPricePerSquareMeter.subtract(minPricePerShop))));
		BigDecimal price = pricePerSquareMeter.multiply(new BigDecimal(area)).setScale(1, RoundingMode.FLOOR);
		return new ShopSpace(area, price);
	}
}
