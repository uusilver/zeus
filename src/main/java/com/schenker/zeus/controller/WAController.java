package com.schenker.zeus.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class WAController {
	
	   private static Logger logger = Logger.getLogger(WAController.class);

	
	@RequestMapping(value = "/rest/{test}", method = RequestMethod.GET)
	public  @ResponseBody String testRest(@PathVariable String test){
		return test;
	}
	
	   //纪录访问路径并且访问次数加1
		@RequestMapping(value = "/rest/visitPath/{pathValue}", method = RequestMethod.GET)
		public @ResponseBody String visitPath(@PathVariable String pathValue){
			logger.info("Visit Path:"+pathValue);
			return "Visit Path:"+pathValue;
		}
		
		@RequestMapping(value = "/rest/broswer/{pathValue}/{browserVersion}", method = RequestMethod.GET)
		public @ResponseBody String recordBrowserVersion(@PathVariable String pathValue, @PathVariable String browserVersion){
			logger.info("Browser Version:"+pathValue+" "+browserVersion);
			return "Browser Version:"+pathValue+" "+browserVersion;
		}
		
		@RequestMapping(value = "/rest/clickEle/{pathValue}/{clickElement}", method = RequestMethod.GET)
		public @ResponseBody String recordClickElement(@PathVariable String pathValue, @PathVariable String clickElement){
			logger.info("Click element:"+pathValue+" "+clickElement);
			return "Click element:"+pathValue+" "+clickElement;
		}
		
		
		@RequestMapping(value = "/rest/visitTime/{pathValue}/{visitTime}", method = RequestMethod.GET)
		public @ResponseBody String recordVisitTime(@PathVariable String pathValue, @PathVariable int visitTime){
			logger.info("Visit Time:"+pathValue+" "+visitTime);
			return"Visit Time:"+pathValue+" "+visitTime;
		}
}
