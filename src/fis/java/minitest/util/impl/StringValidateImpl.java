package fis.java.minitest.util.impl;

import fis.java.minitest.util.ValidateUtil;

public class StringValidateImpl implements ValidateUtil{

	@Override
	public boolean isValidate(int min, int max, String data) {
		int len = data.trim().length();
		if(len >= min && len <= max) {
			return true;
		}
		return false;
	}

}
