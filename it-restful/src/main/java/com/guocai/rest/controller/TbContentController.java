package com.guocai.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guocai.pojo.TbContent;
import com.guocai.rest.service.TbContentService;
import com.guocai.taotao.utils.ExceptionUtil;
import com.guocai.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/content")
public class TbContentController {
	
	@Autowired
	private TbContentService tbContentService;
	
	@RequestMapping("/list/{contentCategoryId}")
	@ResponseBody
	public TaotaoResult getTbContentList(@PathVariable long contentCategoryId) {
		List<TbContent> contentList;
		try {
			contentList = tbContentService.getContentList(contentCategoryId);
			return TaotaoResult.ok(contentList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 用异常工具类返回异常信息
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
	}
	
}
