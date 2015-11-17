package com.lab2.transit;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FareCalculator {
	
	private static final double DELTA = 1e-15;
	
	private double expected;
	private int age;
	private String time;
	private boolean isHoliday;
	
	
	public FareCalculatorTest(double expected, int age, String time, boolean isHoliday) 
	{
		this.expected = expected;
		this.age = age;
		this.time = time;
		this.isHoliday = isHoliday;
	}
	
	@Parameters
	public static Collection<Object[]> testParams() {
		return Arrays.asList(new Object[][] {
				{0.0, 5, "6:59", false},
				{2.5, 5, "7:00", false},
				{0.0, 5, "9:01", false},
				
				{2.5, 6, "6:59", false},
				{2.5, 6, "7:00", false},
				{2.5, 6, "9:01", false},
				
				{0.0, 5, "6:59", true},
				{0.0, 5, "7:00", true},
				{0.0, 5, "9:01", true},
				
				{2.5, 6, "6:59", true},
				{2.5, 6, "7:00", true},
				{2.5, 6, "9:01", true},
				
				{2.5, 64, "6:59", false},
				{2.5, 64, "7:00", false},
				{2.5, 64, "9:01", false},
				
				{0.0, 65, "6:59", false},
				{2.5, 65, "7:00", false},
				{0.0, 65, "9:01", false},
				
				{2.5, 64, "6:59", true},
				{2.5, 64, "7:00", true},
				{2.5, 64, "9:01", true},
				
				{0.0, 65, "6:59", true},
				{0.0, 65, "7:00", true},
				{0.0, 65, "9:01", true},
				
				
		});
	}
	/**
	 * 
	 * @param age - The age of the person
	 * @param time - The time in military (24-hour) format e.g. 4:00 PM would be 16:00 
	 * @param isHoliday - If the day is a holiday or not
	 * @return - 0.0 if discounted fare, 2.5 if regular fare
	 */
	public static double calculateFare(int age, String time, boolean isHoliday) {
		
		String[] parsedTime = time.split(":");
		
		int hour = Integer.parseInt(parsedTime[0]);
		int minutes = Integer.parseInt(parsedTime[1]);
		
		if(age <= 5 || age >= 65) {	
			if(hour < 7 || (hour == 9 && minutes > 0) || hour > 9 || isHoliday) {
				return 0.0;
			}
		}
		
		return 2.5;
	}

}
