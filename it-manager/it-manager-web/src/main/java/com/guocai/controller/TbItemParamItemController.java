package com.guocai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.guocai.service.TbItemParamItemService;

@Controller
public class TbItemParamItemController {

	@Autowired
	private TbItemParamItemService tbItemParamItemService;
	
	@RequestMapping("/items/{itemId}")
	public String showItemParam(@PathVariable long itemId,Model model) {
		String str = tbItemParamItemService.getItemParamByItemId(itemId);
		model.addAttribute("itemParam",str);
		return "item";
	}
	
}
