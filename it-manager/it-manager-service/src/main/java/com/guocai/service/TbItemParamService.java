package com.guocai.service;

import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.taotao.utils.TaotaoResult;

public interface TbItemParamService {
	TaotaoResult getItemParamByCid(long cid);
	EasyUIDataGridResult getItemList(int page,int rows);
	TaotaoResult insertTbItemParam(long itemCatId,String paramData);
}
