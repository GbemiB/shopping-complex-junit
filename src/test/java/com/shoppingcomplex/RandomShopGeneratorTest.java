package com.shoppingcomplex;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

class RandomShopGeneratorTest {

	private static final double FIX_CHARGES = 4.0;
	
	@Nested
	class GeneratorDefaultParamsTests {
		
		private RandomShopGenerator generator;
		
		@BeforeEach
		void setup() {
			this.generator = new RandomShopGenerator();
		}
		
		@RepeatedTest(10)
		void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
			
			// given
			double shopArea = 30.0;
			double maxShopArea = shopArea * FIX_CHARGES;
			BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
			BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(FIX_CHARGES));
			
			// when
			ShopSpace apartment = generator.generate();
			
			// then
			BigDecimal maxApartmentPrice = new BigDecimal(apartment.getShopArea()).multiply(maxPricePerSquareMeter);
			BigDecimal minApartmentPrice = new BigDecimal(apartment.getShopArea()).multiply(minPricePerSquareMeter);
			assertAll(
				() -> assertTrue(apartment.getShopArea() >= shopArea),
				() -> assertTrue(apartment.getShopArea() <= maxShopArea),
				() -> assertTrue(apartment.getShopFee().compareTo(minApartmentPrice) >= 0),
				() -> assertTrue(apartment.getShopFee().compareTo(maxApartmentPrice) <= 0)
			);
		}
	}
	
	@Nested
	class GeneratorCustomParamsTests {
		
		private RandomShopGenerator generator;
		private double shopArea = 15.0;
		private BigDecimal minPricePerSquareMeter = new BigDecimal(5000.0);
		
		@BeforeEach
		void setup() {
			this.generator = new RandomShopGenerator(shopArea, minPricePerSquareMeter);
		}
		
		@RepeatedTest(10)
		void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
			
			// given
			double minArea = this.shopArea;
			double maxArea = minArea * FIX_CHARGES;
			BigDecimal minPricePerSquareMeter = this.minPricePerSquareMeter;
			BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(FIX_CHARGES));
			
			// when
			ShopSpace apartment = generator.generate();
			
			// then
			BigDecimal maxApartmentPrice = new BigDecimal(apartment.getShopArea()).multiply(maxPricePerSquareMeter);
			BigDecimal minApartmentPrice = new BigDecimal(apartment.getShopArea()).multiply(minPricePerSquareMeter);
			assertAll(
				() -> assertTrue(apartment.getShopArea() >= minArea),
				() -> assertTrue(apartment.getShopArea() <= maxArea),
				() -> assertTrue(apartment.getShopFee().compareTo(minApartmentPrice) >= 0),
				() -> assertTrue(apartment.getShopFee().compareTo(maxApartmentPrice) <= 0)
			);
		}
	}
}
