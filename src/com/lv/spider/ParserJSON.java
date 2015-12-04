package com.lv.spider;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lv.log.LogStart;

public class ParserJSON {
	private static Logger LOG = LogManager.getLogger(ParserJSON.class);
	
	//map以最快的方式解析
	public static Map<String,Object> getDATAP(String results) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Map<String, Object> data = JSON.parseObject(results, new TypeReference<Map<String, Object>>() {});
		
		//效率最高的map遍历
		for(Iterator<Entry<String, Object>> it=data.entrySet().iterator();it.hasNext();){
			//it.next();
			Entry<String, Object> e = it.next(); 
			String key = e.getKey().toString();
			
			if(key=="content"){
				Map<String, Object> content = JSON.parseObject(e.getValue().toString(), new TypeReference<Map<String, Object>>() {});
				for(Iterator<Entry<String, Object>> ite=content.entrySet().iterator();ite.hasNext();){
					//ite.next();
					Entry<String, Object> ec = ite.next(); 
					String keye = ec.getKey().toString();
					
					if(keye=="result"){
						@SuppressWarnings("unchecked")
						List<Map<String,Object>> result_value = (List<Map<String,Object>>)ec.getValue();
						if(result_value.size()==0){
							LOG.info("no data！");
							SpiderStart.quit();
						}
						for(Map<String, Object> v : result_value){
							System.out.println("v="+v);
							LGResult.result(v);
						}
					}
				}
			}
		}
		return null;
	}
	
	
	//解析map
	public static Map<String,Object> getDATA(String results) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String, Object> data = JSON.parseObject(results, new TypeReference<Map<String, Object>>() {});
		Iterator<String> iterator = data.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			if(key=="content"){
				
				String content_value = data.get(key).toString();
				Map<String, Object> content = JSON.parseObject(content_value, new TypeReference<Map<String, Object>>() {});
				Iterator<String> contentIterator = content.keySet().iterator();
				
				
				while(contentIterator.hasNext()){
					String contentkey = contentIterator.next();
					if(contentkey=="result"){
						
						@SuppressWarnings("unchecked")
						List<Map<String,Object>> result_value = (List<Map<String,Object>>)content.get(contentkey);
						if(result_value.size()==0){
							LOG.info("no data！");
							SpiderStart.quit();
						}
						for(Map<String, Object> v : result_value){
							System.out.println("v="+v);
							LGResult.result(v);
						}
					}
				}
			}
		}
		
		return data;
	}

}
