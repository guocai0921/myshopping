package com.guocai.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guocai.common.pojo.EasyTreeNode;
import com.guocai.pojo.TbContentCategory;
import com.guocai.service.TbContentCategoryService;
import com.guocai.taotao.utils.TaotaoResult;

@Controller
@RequestMapping("/content/category")
public class TbContentCategoryController {
	
	@Autowired
	private TbContentCategoryService tbContentCategoryService;
	
	// 展示商品分类管理
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyTreeNode> getCategoryList(@RequestParam(value="id",defaultValue="0")Long parentId) {
		List<EasyTreeNode> result = tbContentCategoryService.getCategoryList(parentId);
		return result;
	}
	
	// 添加商品分类管理
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertDataByEntity(Long parentId,String name) {
		TbContentCategory entity = new TbContentCategory();
		TbContentCategory contentCategory = tbContentCategoryService.selectByPrimaryKey(parentId);
		if(contentCategory!=null&&contentCategory.getIsParent()==false) {
			contentCategory.setIsParent(true);
			tbContentCategoryService.updateByPrimaryKey(contentCategory);
		}
		entity.setIsParent(false);
		entity.setName(name);
		entity.setParentId(parentId);
		entity.setUpdated(new Date());
		entity.setCreated(new Date());
		entity.setSortOrder(1);
		entity.setStatus(1);
		TaotaoResult result = tbContentCategoryService.insertDataByEntity(entity);
		return result;
	}
	
	/**
	 * 修改商品分类管理
	 */
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult editContentCategoryById(long id,String name) {
		TbContentCategory contentCategory = tbContentCategoryService.selectByPrimaryKey(id);
		if(contentCategory!=null&&contentCategory.getIsParent()==false) {
			contentCategory.setName(name);
			tbContentCategoryService.updateByPrimaryKey(contentCategory);
		}
		return TaotaoResult.ok();
	}
	
	/**
	 * 删除商品分类管理
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategoryById(long id) {
		tbContentCategoryService.deleteContentCategoryById(id);
		return TaotaoResult.ok();
	}
}
