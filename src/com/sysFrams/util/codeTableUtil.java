package com.sysFrams.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sysFrams.db.DataBaseControl;;

public class codeTableUtil {
	static Map<String,Map<String,String>> codeMap = new HashMap<String,Map<String,String>>(); 
	static Collection list;
	
	
	public static Map<String,Map<String,String>> getCodeMap(String dmlb) throws Exception{
		if(codeMap==null || codeMap.size()==0){
			readDMLB();
		}
		
		return (Map)codeMap.get(dmlb);
	}
	
	public static String getCodeValue(String dmlb, String dmz) throws Exception
    {
        if (codeMap == null || codeMap.size() == 0)
        {
            readDMLB();
        }
        Map table = (Map)codeMap.get(dmlb);
        if (table == null) return "";
        if (StrUtils.isBlank(dmz)) return "";
        return table.get(dmz)==null ? "" : table.get(dmz).toString();
    }
	
	public static void cleanCodeMap(){
		codeMap = new HashMap<String,Map<String,String>>();
	}
	
	static void readDMLB() throws Exception{
		DataBaseControl dbControl=DataBaseControl.getInstance();
		ArrayList list = (ArrayList)dbControl.getOutResultSet("select dmlb from code where yxbz='1' group by dmlb", new Object[]{});
		for(Object item : list){
			Map m = (Map)item;			
			codeMap.put(m.get("dmlb").toString(),readDMZ(m.get("dmlb").toString()));
		}
	}
	static Map<String,String> readDMZ(String dmlb) throws Exception{
		DataBaseControl dbControl=DataBaseControl.getInstance();
		ArrayList list = (ArrayList)dbControl.getOutResultSet("select dmz,dmzmc from code where yxbz='1' and dmlb=? order by plsx", new Object[]{dmlb});
		Map<String, String> valueMap = (Map<String, String>)new ListOrderedMap(); 
		for(Object item:list){
			Map m = (Map)item;
			valueMap.put(m.get("dmz").toString(), m.get("dmzmc").toString());
		}
		return valueMap;
	}

}
