package com.guocai.service;

import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.pojo.TbContent;
import com.guocai.taotao.utils.TaotaoResult;

public interface TbContentService {
	EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer rows);
	TaotaoResult insertTbContentByEntity(TbContent tbContent);
	TaotaoResult updateTbContentByEntity(TbContent tbContent);
	TaotaoResult deleteTbContentById(long id);
}
