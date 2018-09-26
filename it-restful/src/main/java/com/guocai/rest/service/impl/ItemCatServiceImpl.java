package com.guocai.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guocai.mapper.TbItemCatMapper;
import com.guocai.pojo.TbItemCat;
import com.guocai.pojo.TbItemCatExample;
import com.guocai.pojo.TbItemCatExample.Criteria;
import com.guocai.rest.pojo.CatNode;
import com.guocai.rest.pojo.CatResult;
import com.guocai.rest.service.ItemCatService;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public CatResult getItemCatList() {
		// TODO Auto-generated method stub
		CatResult catResult = new CatResult();
		catResult.setData(getCatList(0));
		return catResult;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<?> getCatList(long parentId){
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		// 返回值list
		List resultList = new ArrayList<>();
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				
				CatNode catNode = new CatNode();
				if (parentId ==0) {
					catNode.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/"+tbItemCat.getId()+".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				resultList.add(catNode);
				count++;
				// 第一层只取14条记录
				if (parentId ==0 && count >=14) {
					break;
				}
			// 如果为叶子节点	
			} else {
				resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
			}
		}
		return resultList;
	}

}
