package com.guocai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guocai.mapper.TbItemMapper;
import com.guocai.pojo.TbItem;
import com.guocai.service.TbItemService;

@Service
public class TbItemServiceImpl implements TbItemService{
	
	@Autowired
	private TbItemMapper tbItemMapper;

	@Override
	public TbItem selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return tbItemMapper.selectByPrimaryKey(id);
	}
	
}
