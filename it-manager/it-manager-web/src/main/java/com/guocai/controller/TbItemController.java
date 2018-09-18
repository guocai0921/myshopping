package com.guocai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.pojo.TbItem;
import com.guocai.service.TbItemService;

@Controller
@RequestMapping("/item")
public class TbItemController {
	@Autowired
	private TbItemService tbItenService;
	
	@RequestMapping("/findItemById/{id}")
	@ResponseBody
	public TbItem findItemById(@PathVariable Long id) {
		return tbItenService.selectByPrimaryKey(id);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows) {
		return tbItenService.getItemList(page, rows);
	}
	
}
