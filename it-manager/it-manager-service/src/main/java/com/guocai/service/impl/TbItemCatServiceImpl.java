package com.guocai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guocai.common.pojo.EasyTreeNode;
import com.guocai.mapper.TbItemCatMapper;
import com.guocai.pojo.TbItemCat;
import com.guocai.pojo.TbItemCatExample;
import com.guocai.pojo.TbItemCatExample.Criteria;
import com.guocai.service.TbItemCatService;

@Service
public class TbItemCatServiceImpl implements TbItemCatService{

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	/**
	 * 查询商品类别
	 */
	@Override
	public List<EasyTreeNode> getCatList(long parentId) {
		// 创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		// 根据条件查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List<EasyTreeNode> result = new ArrayList<EasyTreeNode>();
		
		// 将列表转换成EasyTreeNode
		for (TbItemCat tbItemCat : list) {
			EasyTreeNode entity = new EasyTreeNode();
			entity.setId(tbItemCat.getId());
			entity.setText(tbItemCat.getName());
			entity.setState(tbItemCat.getIsParent()?"closed":"open");
			result.add(entity);
		}
		return result;
	}

}
