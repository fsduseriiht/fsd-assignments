package com.cts.fsd.util;

import java.io.File;


public class FSDAppUtil {
	
	private static String realContextPath;
	
	public static String getRealContextPath() {
		return realContextPath;
	}

	public static void setRealContextPath(String realContextPath) {
		FSDAppUtil.realContextPath = realContextPath;
	}
	
	final static String RESOURCE_LOCATION = /*File.separator + */"WEB-INF" + File.separator + "classes" + File.separator;

	
}
