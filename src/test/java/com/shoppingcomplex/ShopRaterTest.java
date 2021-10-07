package com.shoppingcomplex;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ShopRaterTest {

	@ParameterizedTest
	@CsvSource(value = {"72.0, 250000.0, 0", "48.0, 350000.0, 1", "30.0, 600000.0, 2"})
	void should_ReturnCorrectRating_When_CorrectApartment(Double area, Double price, int rating) {
	
		// given
		ShopSpace apartment = new ShopSpace(area, new BigDecimal(price));
		int expected = rating;
		
		// when
		int actual = ShopRater.rateShop(apartment);
		
		// then
		assertEquals(expected, actual);		
	}
	
	@Test
	void should_ReturnErrorValue_When_IncorrectApartment() {
	
		// given
		ShopSpace apartment = new ShopSpace(0.0, new BigDecimal(350000.0));
		int expected = -1;
		
		// when
		int actual = ShopRater.rateShop(apartment);
		
		// then
		assertEquals(expected, actual);		
	}
	
	@Test
	void should_CalculateAverageRating_When_CorrectApartmentList() {
	
		// given
		List<ShopSpace> apartments = new ArrayList<>();
		apartments.add(new ShopSpace(72.0, new BigDecimal(250000.0)));
		apartments.add(new ShopSpace(48.0, new BigDecimal(350000.0)));
		apartments.add(new ShopSpace(30.0, new BigDecimal(600000.0)));

		double expected = 1.0;
		
		// when
		double actual = ShopRater.calculateAverageRating(apartments);
		
		// then
		assertEquals(expected, actual);		
	}
	
	@Test
	void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
	
		// given
		List<ShopSpace> apartments = new ArrayList<>();
		
		// when
		Executable executable = () -> ShopRater.calculateAverageRating(apartments);
		
		// then
		assertThrows(RuntimeException.class, executable);
	}
}
