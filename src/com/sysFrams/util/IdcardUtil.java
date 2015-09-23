/*
 * <p> Company: 官房电子科技有限公司</p>
 * <p> Created on 2010-12-17</p>
 * <p> @author ShenChang zou</p>
 */
package com.sysFrams.util;

public class IdcardUtil {
	/**
	 * 15位身份证转18位
	 * 
	 * @param idcard
	 * @return
	 */
	public static String Idcardchange18(String idcard) {
		/**
		 * 结果变量
		 */

		String result = idcard;
		if (result != null && result.length() == 15) {

			StringBuffer strid = new StringBuffer(18);
			int sn = 0;
			int[] w = new int[17];
			String sf1 = " 7 910 5 8 4 2 1 6 3 7 910 5 8 4 2";
			String sf2 = "10x987654321";
			String s1 = "";

			String sJy = "";
			for (int j = 0; j < 15; j++) {
				if (j == 5) {
					strid.append(idcard.substring(j, j + 1));
					strid.append("1");
					strid.append("9");
				} else {
					strid.append(idcard.substring(j, j + 1));
				}
			}
			s1 = strid.toString();
			for (int i = 0; i < 17; i++) {
				w[i] = Integer.parseInt(sf1.substring(i * 2, i * 2 + 2).trim());
				sn = sn + Integer.parseInt(s1.substring(i, i + 1)) * w[i];
			}
			sJy = sf2.substring((sn % 11), (sn % 11) + 1);
			strid.append(sJy);
			result = strid.toString();
		}
		return result;
	}

	/**
	 * 18位身份证转15位
	 * 
	 * @param idcard
	 * @return
	 */
	public static String Idcardchange15(String idcard) {
		/**
		 * 
		 */
		String result = idcard;
		/**
		 * 身份证号码 不能为空且必须为18位
		 */
		if (idcard != null && idcard.length() == 18) {
			result = idcard.substring(0, 6) + idcard.substring(8, 17);
		}
		return result;
	}
}
