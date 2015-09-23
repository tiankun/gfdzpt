/*
* <p> Company: 官房电子科技有限公司</p>
* <p> Created on 2014-1-15上午11:52:46</p>
* <p> @author 李存永</p>
*/
package com.web.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.map.Fi_billcategory;
import com.map.Fi_billtype;
import com.sysFrams.db.DataBaseControl;

/**
 * @类功能说明：获取发票类型
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作	    者：李存永
 * @创建时间：2014-1-15 上午11:52:46
 */
public class BillTypeUtil {

	private DataBaseControl dataBaseControl=DataBaseControl.getInstance();	
	private static BillTypeUtil billTypeUtil;
	private static Map<String,Object> dataMap = new HashMap<String,Object>();
	private BillTypeUtil(){}
	
	public static BillTypeUtil getInstance(){
		if(billTypeUtil == null){
			synchronized (BillTypeUtil.class) {
				if(billTypeUtil == null){
					billTypeUtil = new BillTypeUtil();
				}
			}
		}
		return billTypeUtil;
	}
	
	public List<Fi_billcategory> getBillType(){
		@SuppressWarnings("unchecked")
		List<Fi_billcategory> billCat = (List<Fi_billcategory>) dataMap.get("billCat");
		if(billCat == null){
			try {
				billCat = initBillCat();
				dataMap.put("billCat", billCat);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return billCat;
	}

	/**
	 * 初始化 发票类型
	 */
	private List<Fi_billcategory> initBillCat() throws Exception{
		List<Fi_billcategory> billCatList = null;
		String sql = "select fb.id,fb.categoryname from FI_BILLCATEGORY fb where 1=1 order by fb.id ";
		List<Map<String,Object>> billCatData = dataBaseControl.getOutResultSet(sql, null);
		String jsonStr = JSON.toJSONString(billCatData);
		billCatList = JSON.parseArray(jsonStr, Fi_billcategory.class);
		
		sql = "select bt.id,bt.billname,bt.billcatetory from fi_billType bt where 1=1 order by bt.id  ";
		billCatData = dataBaseControl.getOutResultSet(sql, null);
		jsonStr = JSON.toJSONString(billCatData);
		List<Fi_billtype> billTypeList = JSON.parseArray(jsonStr, Fi_billtype.class);
		
		List<Fi_billtype> billTypeBak = null;
		for (Fi_billcategory billcategory : billCatList) {
			Long id = billcategory.getId();
			billTypeBak = new ArrayList<Fi_billtype>();
			for (Fi_billtype billtype : billTypeList) {
				if(id.equals(billtype.getBillcatetory())){
					billTypeBak.add(billtype);
				}
			}
			billcategory.setBillTypeList(billTypeBak);
		}
		
		return billCatList;
	}
	
}
