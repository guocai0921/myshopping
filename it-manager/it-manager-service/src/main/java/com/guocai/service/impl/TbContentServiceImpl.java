package com.guocai.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guocai.common.pojo.EasyUIDataGridResult;
import com.guocai.mapper.TbContentMapper;
import com.guocai.pojo.TbContent;
import com.guocai.pojo.TbContentExample;
import com.guocai.pojo.TbContentExample.Criteria;
import com.guocai.service.TbContentService;
import com.guocai.taotao.utils.FtpUtil;
import com.guocai.taotao.utils.TaotaoResult;
@Service
public class TbContentServiceImpl implements TbContentService {
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	
	@Value("${FTP_BASE_BATH}")
	private String FTP_BASE_BATH;

	@Autowired
	private TbContentMapper tbContentMapper;
	
	/**
	 * 内容管理页面内容展示
	 */
	@Override
	public EasyUIDataGridResult getContentList(Long categoryId,Integer page,Integer rows) {
		// TODO Auto-generated method stub
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbContent> info = new PageInfo<>(list);
		long total = info.getTotal();
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(total);
		result.setRows(list);
		return result;
	}

	/**
	 * 内容管理添加数据
	 */
	@Override
	public TaotaoResult insertTbContentByEntity(TbContent tbContent) {
		// TODO Auto-generated method stub
	    // 补全POJO内容
		tbContent.setUpdated(new Date());
		tbContent.setCreated(new Date());
		tbContentMapper.insert(tbContent);
		return TaotaoResult.ok();
	}
	/**
	 * 内容管理修改数据
	 */
	@Override
	public TaotaoResult updateTbContentByEntity(TbContent tbContent) {
		// TODO Auto-generated method stub
		TbContent oldTbContent = tbContentMapper.selectByPrimaryKey(tbContent.getId());
		if(tbContent.getPic()!=null&&oldTbContent.getPic()!=null&&!(tbContent.getPic().equals(oldTbContent.getPic()))) {
			boolean flag1 = FtpUtil.deleteFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_BATH, oldTbContent.getPic());
			if (flag1) {
				System.out.println("删除图片1成功");
			}
		}
		if(tbContent.getPic2()!=null&&oldTbContent.getPic2()!=null&&!(tbContent.getPic2().equals(oldTbContent.getPic2()))) {
			boolean flag2 = FtpUtil.deleteFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_BATH, oldTbContent.getPic2());
			if (flag2) {
				System.out.println("删除图片2成功");
			}
		}
		tbContent.setUpdated(new Date());
		tbContentMapper.updateByPrimaryKeyWithBLOBs(tbContent);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteTbContentById(long id) {
		// TODO Auto-generated method stub
		tbContentMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}

}
