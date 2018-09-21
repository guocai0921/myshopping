package com.guocai.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.mapper.TbItemDescMapper;
import com.guocai.mapper.TbItemMapper;
import com.guocai.pojo.TbItem;
import com.guocai.pojo.TbItemDesc;
import com.guocai.pojo.TbItemExample;
import com.guocai.service.TbItemService;
import com.guocai.taotao.utils.IDUtils;
import com.guocai.taotao.utils.TaotaoResult;

@Service
public class TbItemServiceImpl implements TbItemService{
	
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;

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

	@Override
	public TaotaoResult insertItem(TbItem tbItem,String desc) throws Exception {
		// TODO Auto-generated method stub
		// 生成商品ID
		long itemId = IDUtils.genItemId();
		// 设置前台没有传过来的数据
		tbItem.setId(itemId);
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		tbItemMapper.insert(tbItem);
		// 添加商品描述信息
		TaotaoResult result = insertItemDesc(itemId,desc);
		if (result.getStatus() != 200) {
			throw new Exception("添加商品描述信息失败!");
		}
		return TaotaoResult.ok();
	}
	
	public TaotaoResult insertItemDesc(Long itemId,String desc) {
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		tbItemDescMapper.insert(tbItemDesc);
		return TaotaoResult.ok();
		
	}
	
}
