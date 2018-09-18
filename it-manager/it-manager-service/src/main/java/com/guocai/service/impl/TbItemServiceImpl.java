package com.guocai.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.mapper.TbItemMapper;
import com.guocai.pojo.TbItem;
import com.guocai.pojo.TbItemExample;
import com.guocai.service.TbItemService;

@Service
public class TbItemServiceImpl implements TbItemService{
	
	@Autowired
	private TbItemMapper tbItemMapper;

	/**
	 * 根据id查询商品信息
	 */
	@Override
	public TbItem selectByPrimaryKey(Long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询商品
	 */
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo<TbItem> info = new PageInfo<>(list);
		long total = info.getTotal();
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}
	
}
