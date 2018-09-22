package com.guocai.service;

import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.pojo.TbItem;
import com.guocai.taotao.utils.TaotaoResult;

public interface TbItemService {
	TbItem selectByPrimaryKey(Long id);
	EasyUIDataGridResult getItemList(int page,int rows);
	TaotaoResult insertItem(TbItem tbItem,String desc,String itemParams) throws Exception;
}
