package com.lv.spider;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserHTML {

	public static List<LinkTypeData> getDATA(Elements results){
		List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
		LinkTypeData data = null;
		for (Element result : results) {
			Elements links = result.getElementsByTag("a");
			for (Element link : links) {
				// 必要的筛选
				String linkHref = link.attr("href");
				String linkText = link.text();

				if (!linkHref.startsWith("http://")) {
					continue;
				}
				data = new LinkTypeData();
				data.setLinkHref(linkHref);
				data.setLinkText(linkText);

				datas.add(data);

			}
		}
		return datas;
	}
}
