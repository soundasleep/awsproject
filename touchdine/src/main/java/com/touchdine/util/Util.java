package com.touchdine.util;

public class Util {
	public static String getKeyName(String url) {
		int index = url.lastIndexOf("/");
		String keyName = url.substring(index + 1);
		return keyName;
	}
}
