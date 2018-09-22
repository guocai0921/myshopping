package com.guocai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.service.TbItemParamService;
import com.guocai.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/item/param")
public class TbItemParamController {

	@Autowired
	private TbItemParamService tbItemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getTbItemParamByCid(@PathVariable long itemCatId) {
		TaotaoResult result = tbItemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page,Integer rows) {
		return tbItemParamService.getItemList(page, rows);
	}
	@RequestMapping("/save/{itemCatId}")
	@ResponseBody
	public TaotaoResult insertTbItemParam(@PathVariable long itemCatId,String paramData) {
		return tbItemParamService.insertTbItemParam(itemCatId, paramData);
	}
}
