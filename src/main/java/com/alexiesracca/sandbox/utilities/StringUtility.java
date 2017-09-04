package com.alexiesracca.sandbox.utilities;

public class StringUtility {
	
	public static Double parseDoubleDigit(String s) {
		Double d = null;
		try {
			d = Double.parseDouble(s);
		}catch (Exception e) {
			return null;
		}
		return d;
	}

}
