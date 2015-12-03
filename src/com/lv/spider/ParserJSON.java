package com.lv.spider;

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
	public static Map<String,Object> getDATAP(String results){
		Map<String, Object> data = JSON.parseObject(results, new TypeReference<Map<String, Object>>() {});
		
		
		//效率最高的map遍历
		for(Iterator<Entry<String, Object>> it=data.entrySet().iterator();it.hasNext();){
			it.next();
			Entry<String, Object> e = it.next(); 
			String key = e.getKey().toString();
			
			if(key=="content"){
				Map<String, Object> content = JSON.parseObject(e.getValue().toString(), new TypeReference<Map<String, Object>>() {});
				for(Iterator<Entry<String, Object>> ite=content.entrySet().iterator();it.hasNext();){
					ite.next();
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
							LGResult re=new LGResult();
							Iterator<String> resultIterator = v.keySet().iterator();
							while(resultIterator.hasNext()){
								
								String resultkey = resultIterator.next();
								if(resultkey=="salary"){
									String value = v.get(resultkey).toString();
									re.setSalary(value);
								}
								if(resultkey=="education"){
									String value = v.get(resultkey).toString();
									re.setEducation(value);
								}
								if(resultkey=="createTime"){
									String value = v.get(resultkey).toString();
									re.setCreateTime(value);
								}
								if(resultkey=="workYear"){
									String value = v.get(resultkey).toString();
									re.setWorkYear(value);
								}
								if(resultkey=="positionType"){
									String value = v.get(resultkey).toString();
									re.setPositionType(value);
								}
								if(resultkey=="positionName"){
									String value = v.get(resultkey).toString();
									re.setPositionName(value);
								}
								if(resultkey=="financeStage"){
									String value = v.get(resultkey).toString();
									re.setFinanceStage(value);
								}
								if(resultkey=="industryField"){
									String value = v.get(resultkey).toString();
									re.setIndustryField(value);
								}
								if(resultkey=="companyName"){
									String value = v.get(resultkey).toString();
									re.setCompanyName(value);
								}
							}
//							System.out.println(re.toString());
							LogStart.WLog(re.toString());
							
						}
					}
				}
			}
		}
		return null;
	}
	
	
	//解析map
	public static Map<String,Object> getDATA(String results){
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
//							System.out.println("v="+v);
							LGResult re=new LGResult();
							Iterator<String> resultIterator = v.keySet().iterator();
							while(resultIterator.hasNext()){
								
								String resultkey = resultIterator.next();
								if(resultkey=="salary"){
									String value = v.get(resultkey).toString();
									re.setSalary(value);
								}
								if(resultkey=="education"){
									String value = v.get(resultkey).toString();
									re.setEducation(value);
								}
								if(resultkey=="createTime"){
									String value = v.get(resultkey).toString();
									re.setCreateTime(value);
								}
								if(resultkey=="workYear"){
									String value = v.get(resultkey).toString();
									re.setWorkYear(value);
								}
								if(resultkey=="positionType"){
									String value = v.get(resultkey).toString();
									re.setPositionType(value);
								}
								if(resultkey=="positionName"){
									String value = v.get(resultkey).toString();
									re.setPositionName(value);
								}
								if(resultkey=="financeStage"){
									String value = v.get(resultkey).toString();
									re.setFinanceStage(value);
								}
								if(resultkey=="industryField"){
									String value = v.get(resultkey).toString();
									re.setIndustryField(value);
								}
								if(resultkey=="companyName"){
									String value = v.get(resultkey).toString();
									re.setCompanyName(value);
								}
							}
//							System.out.println(re.toString());
							LogStart.WLog(re.toString());
							
						}
					}
				}
			}
		}
		
		return data;
	}

}
