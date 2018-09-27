package com.guocai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.pojo.TbContent;
import com.guocai.service.TbContentService;
import com.guocai.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/content/query")
public class TbContentController {
	
	@Autowired
	private TbContentService tbContentService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer rows) {
		return tbContentService.getContentList(categoryId,page,rows);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertTbContentByEntity(TbContent tbContent) {
		return tbContentService.insertTbContentByEntity(tbContent);
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult updateTbContentByEntity(TbContent tbContent) {
		return tbContentService.updateTbContentByEntity(tbContent);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteTbContentById(String ids) {
		TaotaoResult result = new TaotaoResult();
		int index = ids.indexOf(",");
		if(index==-1) {
			result = tbContentService.deleteTbContentById(Long.valueOf(ids));
		} else {
			String[] array = ids.split(",");
			for (int i = 0; i < array.length; i++) {
				result = tbContentService.deleteTbContentById(Long.valueOf(array[i]));
			}
		}
		return result;
	}
	
}
