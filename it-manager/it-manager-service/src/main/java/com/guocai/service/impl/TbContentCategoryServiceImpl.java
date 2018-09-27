package com.guocai.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guocai.common.pojo.EasyTreeNode;
import com.guocai.mapper.TbContentCategoryMapper;
import com.guocai.pojo.TbContentCategory;
import com.guocai.pojo.TbContentCategoryExample;
import com.guocai.pojo.TbContentCategoryExample.Criteria;
import com.guocai.service.TbContentCategoryService;
import com.guocai.taotao.utils.TaotaoResult;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<EasyTreeNode> getCategoryList(long parentId) {
		// TODO Auto-generated method stub
		// 根据parentId查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		List<EasyTreeNode> resultList = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			// 创建一个节点
			EasyTreeNode node = new EasyTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TaotaoResult insertDataByEntity(TbContentCategory entity) {
		// TODO Auto-generated method stub
		int result = tbContentCategoryMapper.insert(entity);
		if (result == -1) {
			return TaotaoResult.build(202, "数据保存失败!");
		}
		return TaotaoResult.ok(entity);
	}

	@Override
	public TbContentCategory selectByPrimaryKey(long id) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKey(TbContentCategory entity) {
		// TODO Auto-generated method stub
		tbContentCategoryMapper.updateByPrimaryKey(entity);
	}

	/**
	 * 删除商品分类管理
	 */
	@Override
	public void deleteContentCategoryById(long id) {
		// TODO Auto-generated method stub
		TbContentCategory entity = tbContentCategoryMapper.selectByPrimaryKey(id);
		if(entity.getIsParent()==false) {
			tbContentCategoryMapper.deleteByPrimaryKey(entity.getId());
		} else {
			deleteEntityById(id);
		}
		TbContentCategory parentEntity = tbContentCategoryMapper.selectByPrimaryKey(entity.getParentId());
		parentEntity.setIsParent(false);
		tbContentCategoryMapper.updateByPrimaryKey(parentEntity);
	}

	/**
	 * 递归删除商品分类管理的树形结构
	 * @param id
	 */
	public void deleteEntityById(long id) {
		// 根据parentId查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		// 执行查询
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		if (list!=null && list.size()>0) {
			for (TbContentCategory tbContentCategory : list) {
				if (tbContentCategory.getIsParent()==true) {
					deleteEntityById(tbContentCategory.getId());
				} else {
					tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
				}
			}
		} 
		tbContentCategoryMapper.deleteByPrimaryKey(id);
	}

}
