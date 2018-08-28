package com.ecar.epark.eotherpushlib;

public class OtherMessageUtil {

	public static boolean isEmpty(String content) {
		if (content == null || content.trim().length() == 0
				|| "null".equals(content)) {
			return true;
		}
		return false;
	}
}
