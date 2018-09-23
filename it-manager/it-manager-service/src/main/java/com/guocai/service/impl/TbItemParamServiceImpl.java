package com.guocai.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.mapper.TbItemParamMapper;
import com.guocai.pojo.TbItemParam;
import com.guocai.pojo.TbItemParamExample;
import com.guocai.pojo.TbItemParamExample.Criteria;
import com.guocai.service.TbItemParamService;
import com.guocai.taotao.utils.IDUtils;
import com.guocai.taotao.utils.TaotaoResult;

/**
 * 商品得而规格参数模板
 * 
 * @author sungu
 *
 */
@Service
public class TbItemParamServiceImpl implements TbItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;

	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		// TODO Auto-generated method stub
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        // 判断是否查询到结果
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// TODO Auto-generated method stub
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		// List<TbItemParam> list = tbItemParamMapper.selectByExample(example);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbItemParam> info = new PageInfo<>(list);
		// PageInfo<TbItemParam> info = new PageInfo<>(paramDataList);
		long total = info.getTotal();

		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	@Override
	public TaotaoResult insertTbItemParam(long itemCatId, String paramData) {
		// TODO Auto-generated method stub
		TbItemParam entity = new TbItemParam();
		entity.setId(IDUtils.genItemId());
		entity.setItemCatId(itemCatId);
		entity.setParamData(paramData);
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		int result = tbItemParamMapper.insert(entity);
		if(result!=1) {
			return TaotaoResult.build(202, "插入数据失败!", null);
		}
		return TaotaoResult.ok();
	}

}
