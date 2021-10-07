package com.shoppingcomplex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ShopRater {

	private static final BigDecimal FIRST_CLASS = new BigDecimal(6000.0);
	private static final BigDecimal MIDDLE_CLASS = new BigDecimal(8000.0);
	
	public static int rateShop(ShopSpace shop) {
		if (shop.getShopArea() == 0.0) {
			return -1;
		}
		BigDecimal ratio = shop.getShopFee().divide(new BigDecimal(shop.getShopArea()), RoundingMode.HALF_UP);
		
		if (ratio.compareTo(FIRST_CLASS) < 0) {
			return 0;
		} else if (ratio.compareTo(FIRST_CLASS) >= 0 && ratio.compareTo(MIDDLE_CLASS) < 0) {
			return 1;
		} else {
			return 2;
		}
	}
	
	public static double calculateAverageRating(List<ShopSpace> shopSpace) {
		if (shopSpace.isEmpty()) {
			throw new RuntimeException("Cannot calculate average rating for empty list");
		}
		int sumRatings = 0;
		for (ShopSpace apartment : shopSpace) {
			sumRatings += rateShop(apartment);
		}
		return sumRatings * 1.0 / shopSpace.size();
	}
}
