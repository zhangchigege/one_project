package sy.test;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;

public class test implements PageProcessor{

	
	private Site site = Site.me()
			.setSleepTime(1)
			.setTimeOut(1000*10)
			.setRetrySleepTime(3000)
			.setRetryTimes(3);
	
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		System.out.println(page.getHtml());
		List<String> list = page.getHtml().links().css("ul#clearfix cur").all();
		System.out.println(list);
	}
	
	
	public static void main(String[] args) {
		Spider spider = Spider.create(new test()).addUrl("http://www.bjrbj.gov.cn/csibiz/indinfo/index.jsp")
		.thread(5)
		.pipeline(new ConsolePipeline());
		Scheduler scheduler = spider.getScheduler();
		spider.run();
		
		
	}
	
	

}
