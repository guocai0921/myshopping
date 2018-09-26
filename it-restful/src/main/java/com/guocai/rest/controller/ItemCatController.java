package com.guocai.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guocai.rest.pojo.CatResult;
import com.guocai.rest.service.ItemCatService;

@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/*
	@RequestMapping(value = "itemcat/list",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		CatResult list = itemCatService.getItemCatList();
		// pojo转换成json字符串
		String json = JsonUtils.objectToJson(list);
		// 拼装字符串
		String result = callback +"("+json+")";
		return result;
	}
	*/
	
	@RequestMapping("itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		CatResult list = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
}
