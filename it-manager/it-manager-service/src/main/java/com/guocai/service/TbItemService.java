package com.guocai.service;

import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.pojo.TbItem;

public interface TbItemService {
	TbItem selectByPrimaryKey(Long id);
	EasyUIDataGridResult getItemList(int page,int rows);
}
