package com.guocai.service;

import java.util.List;

import com.guocai.common.pojo.EasyTreeNode;
import com.guocai.pojo.TbContentCategory;
import com.guocai.taotao.utils.TaotaoResult;

public interface TbContentCategoryService {
	List<EasyTreeNode> getCategoryList(long parentId);
	TaotaoResult insertDataByEntity(TbContentCategory entity);
	TbContentCategory selectByPrimaryKey(long id);
	void updateByPrimaryKey(TbContentCategory entity);
	void deleteContentCategoryById(long id);
}
