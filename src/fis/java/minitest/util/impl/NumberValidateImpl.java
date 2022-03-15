package fis.java.minitest.util.impl;

import fis.java.minitest.util.ValidateUtil;

public class NumberValidateImpl implements ValidateUtil{

	@Override
	public boolean isValidate(int min, int max, String data) {
		try {
			int input_number = Integer.parseInt(data.trim());
			if(input_number >= min && input_number <= max) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
}
