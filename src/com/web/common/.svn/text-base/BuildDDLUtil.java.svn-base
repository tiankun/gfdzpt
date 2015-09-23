/*
* <p> Company: 官房电子科技有限公司</p>
* <p> Created on 2013-12-10上午10:35:57</p>
* <p> @author 李存永</p>
*/
package com.web.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sysFrams.db.DataBaseControl;

/**
 * @类功能说明：渲染页面常用数据获取工具
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作	    者：李存永
 * @创建时间：2013-12-10 上午10:35:57
 */
public class BuildDDLUtil {
	

	private static DataBaseControl dataBaseControl=DataBaseControl.getInstance();
	private volatile static BuildDDLUtil buildDDLUtil = null;
	
	private BuildDDLUtil(){}
	
	public static BuildDDLUtil getInstance(){
		if(buildDDLUtil == null){
			synchronized (BuildDDLUtil.class) {
				if(buildDDLUtil == null){
					buildDDLUtil = new BuildDDLUtil();
				}
			}
		}
		return buildDDLUtil;
	}
	
	public static Map<Object, Object> getDataMap(Object[] queryValue,String...params)throws Exception{
		if(params.length == 0)
			return null;
		Map<Object,Object> mapData = new HashMap<Object,Object>();
		String sql = params[0];		
		List<Map<String,Object>> mapDataList = dataBaseControl.getOutResultSet(sql, queryValue);
		
		JSONArray jsonArr = JSONArray.parseArray(JSON.toJSONString(mapDataList));
		for (Object object : jsonArr) {
			String id =JSON.parseObject(object.toString()).getString(params[1]);
			String value = jsonArr.parseObject(object.toString()).getString(params[2]);
			mapData.put(id, value);
		}
		return mapData;
	}

}
