package com.schenker.zeus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schenker.zeus.annotation.DisplayAfterValidation;
import com.schenker.zeus.model.Person;

/**
 *@author vani.li@dbschenker.com
 *@version 1.0
 *@date
 *@desc
 */
@Controller
public class DisplayController {

	@DisplayAfterValidation(required=true)
	@RequestMapping(value = "/displayData", method = RequestMethod.GET)
	public  @ResponseBody String queryData(){
		return "success";
	}
	
	 @RequestMapping(value = "/testPost", method = RequestMethod.POST,headers={"content-type=application/json"})
     @ResponseBody
     public String testPost(@RequestBody Person person) {
             System.out.println(person.getName());
             return "success";
     }

}
