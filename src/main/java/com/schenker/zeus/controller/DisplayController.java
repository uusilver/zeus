package com.schenker.zeus.controller;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.schenker.zeus.annotation.DisplayAfterValidation;

import java.util.ArrayList;
import java.util.List;

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
	
	 @RequestMapping(value = "/gridDataGenerator", method = RequestMethod.POST)
     @ResponseBody
     public String gridDataGenerator(@RequestParam String aoData) {
         JSONArray jsonarray = JSONArray.fromObject(aoData);

         String sEcho = null;
         int iDisplayStart = 0; // 起始索引
         int iDisplayLength = 0; // 每页显示的行数

         for (int i = 0; i < jsonarray.size(); i++) {
             JSONObject obj = (JSONObject) jsonarray.get(i);
             if (obj.get("name").equals("sEcho"))
                 sEcho = obj.get("value").toString();

             if (obj.get("name").equals("iDisplayStart"))
                 iDisplayStart = obj.getInt("value");

             if (obj.get("name").equals("iDisplayLength"))
                 iDisplayLength = obj.getInt("value");
         }

         // 生成20条测试数据
         List<String[]> lst = new ArrayList<String[]>();
         for (int i = 0; i < 20; i++) {
             String[] d = { "Name" + i,  String.valueOf(i) };
             lst.add(d);
         }

         JSONObject getObj = new JSONObject();
         getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
         getObj.put("iTotalRecords", lst.size());//实际的行数
         getObj.put("iTotalDisplayRecords", lst.size());//显示的行数,这个要和上面写的一样

         getObj.put("aaData", lst.subList(iDisplayStart,iDisplayStart + iDisplayLength));//要以JSON格式返回
         System.out.println(getObj.toString());
         return getObj.toString();
     }

}
