package com.lv.spider;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lv.log.TimeCache;

public class SpiderStart {
	
	private static Logger logger = LogManager.getLogger(SpiderStart.class);
	private static boolean loop = true;
	
	public static void main(String[] arge) throws Exception {
		SpiderStart t = new SpiderStart();
		//拉勾网
		String url="http://www.lagou.com/jobs/positionAjax.json?city=长沙";
		//百度图片
		String baiduRUL="http://www.wmpic.me/tupian/yijing/";
		//时间定时任务
//		TimeCache.start();
		//百度图片下载
		t.getImages(baiduRUL);
		//获取拉勾网的数据
//		t.getDatas(url);
//		String url="http://www.lagou.com/jobs/list_?city=%E5%8C%97%E4%BA%AC&gj=&xl=&jd=&hy=&px=&cl=false&fromSearch=true&labelWords=&suginput=";
//		while(true){
//			t.getDatasByClass(url);
//			Thread.sleep(5000);
//		}
		//t.getDatasByCssQuery();
	}
	
	public static void quit() {
		loop = false;
	}

	//获取拉勾网的数据
	public void getDatas(String url) throws Exception{
		//http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery
		int i=1;
		while(loop){
			String pn=i+"";
			Rule rule = new Rule(url,new String[] { "first","pn","kd" }, new String[] {  "false",pn,"后台开发" },
					"", Rule.JSON, Rule.ASYNC);
			List<LinkTypeData> extracts = ExtractService.extract(rule);
			i++;
			Thread.sleep(5000);
		}
		
		//printf(extracts);
	}
	
	//获取百度图片的url然后下载图片
	public void getImages(String url) throws Exception{
		//http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery
		Rule rule = new Rule(url,new String[] { "a" }, new String[] {  "1" },
				"body", Rule.IMAGE, Rule.GET);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		Thread.sleep(5000);
		
		//printf(extracts);
	}
	
	public void getDatasByClass(String url) throws Exception {
		//http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery
		Rule rule = new Rule(
				url,
				new String[] { "query.enterprisename",
						"query.registationnumber" }, new String[] { "", "" },
				"body", Rule.SELECTION, Rule.GET);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		printf(extracts);
	}

	public void getDatasByCssQuery() throws Exception {
		//http://www.11315.com/search
		
		Rule rule = new Rule("http://news.baidu.com/",
				new String[] { "query" }, new String[] { "的" },
				"div", Rule.SELECTION, Rule.GET);
		List<LinkTypeData> extracts = ExtractService.extract(rule);
		printf(extracts);
	}

	public void printf(List<LinkTypeData> datas) throws Exception {
		for (LinkTypeData data : datas) {
			logger.info(data.getLinkText()+" - "+data.getLinkHref());
			
			
			Thread.sleep(5000);
	 		  if (!data.getLinkHref().startsWith("http://")) {
					continue;
				}
	 		  //System.out.println(ld.getLinkHref());
	 		Connection con3=Jsoup.connect(data.getLinkHref());
	 		Document ru=con3.timeout(100000).get();
		 	  addlog(ru);
		 	  Thread.sleep(15000);
//			System.out.println(data.getLinkText());
//			System.out.println(data.getLinkHref());
//			System.out.println("***********************************");
		}

	}
	
	public void addlog(Document doc){
		  Elements results=doc.getElementsByTag("body");
		  for (Element result : results) {
				Elements links = result.getElementsByTag("a");
				for (Element link : links) {
					// 必要的筛选
					String linkHref = link.attr("href");
					String linkText = link.text();
					
					if (!linkHref.startsWith("http://")) {
						continue;
					}
					logger.info(linkText+" - "+linkHref);
	
				}
			}
	  }

}

	
