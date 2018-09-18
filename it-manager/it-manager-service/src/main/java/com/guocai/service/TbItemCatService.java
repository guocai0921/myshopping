package com.guocai.service;

import java.util.List;

import com.guocai.common.pojo.EasyTreeNode;

public interface TbItemCatService {
	List<EasyTreeNode> getCatList(long parentId);
}
