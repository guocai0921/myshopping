package com.guocai.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 首页展示
 * @author sungu
 *
 */
@Controller
public class IndexController {
	@RequestMapping("/index")
	public String showIndex() {
		System.out.println("Hello SGC");
		return "index";
	}
	@RequestMapping(value="/httpclient/post",method=RequestMethod.POST)
	@ResponseBody
	public String post() {
		return "OK";
	}
	
	@RequestMapping(value="/httpclient/postparam",method=RequestMethod.POST)
	@ResponseBody
	public String postParam(String username,String password) {
		return "username:"+username+"<-->password:"+password;
	}
}
