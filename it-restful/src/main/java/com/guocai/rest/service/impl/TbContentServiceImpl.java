package com.guocai.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guocai.mapper.TbContentMapper;
import com.guocai.pojo.TbContent;
import com.guocai.pojo.TbContentExample;
import com.guocai.pojo.TbContentExample.Criteria;
import com.guocai.rest.service.TbContentService;
/**
 * 内容管理
 * @author sungu
 *
 */
@Service
public class TbContentServiceImpl implements TbContentService {
	
	@Autowired
	private TbContentMapper tbContentMapper;

	@Override
	public List<TbContent> getContentList(long contentId) {
		// TODO Auto-generated method stub
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentId);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		return list;
	}

}
