/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Copyright: Copyright ? 2006 官房电子科技有限公司</p>
 * <p> Created on2007-2-8</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.util;

import java.security.MessageDigest;

/**
 * 实现了不可逆加密(MD5/SHA)
 */
public class Encrypt { 
	/**
	 * 对输入字符串进行不可逆加密 
	 * @param str 要求加密的字符串
	 * @return 返回加密后的密文，如果加密不成功，则返回空，请在返回处检查
	 */
	public static String crypt(String str) {
		if (str == null) {
			throw new IllegalArgumentException(
					"String to encript cannot be null !");
		}
		byte[] unencodedPassword = str.getBytes();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (Exception e) {
			e.printStackTrace();
			return str;
		}
		md.reset();
		md.update(unencodedPassword);
		byte[] encodedPassword = md.digest();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}
		return buf.toString();
	}
//	public static void main(String[] arg){
//		System.out.println(Encrypt.crypt("123456"));//7c4a8d09ca3762af61e59520943dc26494f8941b
//	}
}