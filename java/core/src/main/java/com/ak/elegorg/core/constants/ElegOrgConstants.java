package com.ak.elegorg.core.constants;

public enum ElegOrgConstants {
	
	NULL_STRING("NULL_STRING");
	private String value;
	
	ElegOrgConstants(String str) {
		this.value = str;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public static ElegOrgConstants getElegOrgConstants(String str) {
		return ElegOrgConstants.valueOf(str);
	}
	
	public static boolean isElegOrgConstants(ElegOrgConstants e,String str) {
		return str.equalsIgnoreCase(e.getValue());
	}
}
