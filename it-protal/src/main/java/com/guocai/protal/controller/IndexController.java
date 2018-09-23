package com.guocai.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
