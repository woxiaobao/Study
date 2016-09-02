package com.lv.spiderGoogleMap;

import java.util.List;

import com.lv.spider.ExtractService;
import com.lv.spider.LinkTypeData;
import com.lv.spider.Rule;
import com.lv.spider.SpiderStart;

/**
 *
 * @author LvBaolin
 * @date: 2016年7月26日
 * @time: 上午11:45:58
 */

public class Test {

	public static void main(String[] arge) throws Exception {
		SpiderStart t = new SpiderStart();
		String url = "https://maps.googleapis.com/maps/api/distancematrix/json";
		t.getDatas(url);
		// String
		// url="http://www.lagou.com/jobs/list_?city=%E5%8C%97%E4%BA%AC&gj=&xl=&jd=&hy=&px=&cl=false&fromSearch=true&labelWords=&suginput=";
		// while(true){
		// t.getDatasByClass(url);
		// Thread.sleep(5000);
		// }
		// t.getDatasByCssQuery();
	}

	public void getDatas(String url) throws Exception {
		Rule rule = new Rule(url, new String[] { "origins", "destinations",
				"key" }, new String[] { "Seattle", "San+Francisco",
				"AIzaSyDMZw_ygQaU7GwGbXm6SDc4pywJ4JDqGbw" }, "", Rule.JSON,
				Rule.ASYNC);
		List<LinkTypeData> extracts = ExtractService.extract(rule);

		// printf(extracts);
	}
}
