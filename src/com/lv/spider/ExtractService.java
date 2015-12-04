package com.lv.spider;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtractService {
	/**
	 * @param rule
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	public static List<LinkTypeData> extract(Rule rule) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		// 进行对rule的必要校验
		validateRule(rule);

		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		try {
			/**
			 * 解析rule
			 */
			String url = rule.getUrl();
			String[] params = rule.getParams();
			String[] values = rule.getValues();
			String resultTagName = rule.getResultTagName();
			int type = rule.getType();
			int requestType = rule.getRequestMoethod();

			Connection conn = Jsoup.connect(url);
			// 设置查询参数
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					conn.data(params[i], values[i]);
				}
			}

			// 设置请求类型
			Document doc = null;
			Response resp=null;
			switch (requestType) {
			case Rule.GET:
				doc = conn.timeout(100000).get();
				break;
			case Rule.POST:
				doc = conn.timeout(100000).post();
				break;
			case Rule.ASYNC:
				resp=conn.timeout(100000).ignoreContentType(true).execute();
				break;
			}
			
			// 处理返回数据
			Elements results = new Elements();
			switch (type) {
				case Rule.CLASS:
					results = doc.getElementsByClass(resultTagName);
					break;
				case Rule.ID:
					Element result = doc.getElementById(resultTagName);
					results.add(result);
					break;
				case Rule.SELECTION:
					results = doc.select(resultTagName);
					break;
				case Rule.IMAGE:
					results = doc.select(resultTagName);
					break;
				case Rule.JSON:
//					System.out.println(resp.body());
					break;
//				default:
//					// 当resultTagName为空时默认去body标签
//					if (resultTagName.equals("")) {
//						results = doc.getElementsByTag("body");
//					}
			}
//			System.out.println(results);
			if(type==Rule.JSON){
				//System.out.println(resp.body());
				ParserJSON.getDATAP(resp.body());
			}else if(type==Rule.IMAGE){
				ParserHTML.getIMAGES(results);
			}else{
				//解析html 存放在datas中
				datas=ParserHTML.getDATA(results);
			}
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}

		return datas;
	}

	/**
	 * 对传入的参数进行必要的校验
	 */
	private static void validateRule(Rule rule) {
		String url = rule.getUrl();
		if (url.equals("")) {
			throw new RuleException("url不能为空！");
		}
		if (!url.startsWith("http://")) {
			throw new RuleException("url的格式不正确！");
		}

		if (rule.getParams() != null && rule.getValues() != null) {
			if (rule.getParams().length != rule.getValues().length) {
				throw new RuleException("参数的键值对个数不匹配！");
			}
		}

	}

}
