package com.guocai.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guocai.rest.service.RedisService;
import com.guocai.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/cache/sync")
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@RequestMapping("/content/{contentCid}")
	@ResponseBody
	private TaotaoResult contentCacheSync (@PathVariable long contentCid) {
		TaotaoResult result = redisService.syncContent(contentCid);
		return result;
	}
	
}
