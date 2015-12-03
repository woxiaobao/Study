package com.lv.spider;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class ParserJSON {
	
	public static Map<String,Object> getDATA(String results){
		Map<String, Object> data = JSON.parseObject(results, new TypeReference<Map<String, Object>>() {});
		
		//效率最高的map遍历
//		for(Iterator<Entry<String, Object>> it=data.entrySet().iterator();it.hasNext();){
//			it.next();
//			Entry<String, Object> e = it.next(); 
//			Object key = e.getKey();
//			Object val = e.getValue();
//		}
		
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
						
						for(Map<String, Object> v : result_value){
							System.out.println("v="+v);
							Iterator<String> resultIterator = v.keySet().iterator();
							while(resultIterator.hasNext()){
								String resultkey = resultIterator.next();
								String value = v.get(resultkey).toString();
								System.out.println(resultkey+"="+value);
							}
						}
					}
				}
			}
		}
		
		return data;
	}

}
