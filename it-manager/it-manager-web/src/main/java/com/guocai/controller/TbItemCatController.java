package com.guocai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guocai.common.pojo.EasyTreeNode;
import com.guocai.service.TbItemCatService;

@Controller
@RequestMapping("/item/cat")
public class TbItemCatController {
	
	@Autowired
	private TbItemCatService tbItemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyTreeNode> getCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		return tbItemCatService.getCatList(parentId);
	}
	
}
