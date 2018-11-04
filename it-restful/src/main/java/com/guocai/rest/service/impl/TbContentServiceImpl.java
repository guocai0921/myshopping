package com.guocai.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.guocai.mapper.TbContentMapper;
import com.guocai.pojo.TbContent;
import com.guocai.pojo.TbContentExample;
import com.guocai.pojo.TbContentExample.Criteria;
import com.guocai.rest.dao.JedisClient;
import com.guocai.rest.service.TbContentService;
import com.guocai.taotao.utils.JsonUtils;
import com.guocai.taotao.utils.StringUtil;

/**
 * 内容管理
 * @author sungu
 *
 */
@Service
public class TbContentServiceImpl implements TbContentService {
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public List<TbContent> getContentList(long contentId) {
		// 从缓从中取数据
		try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentId));
			if (StringUtil.isNotEmpty(result)) {
				// 把字符串转换为List
				List<TbContent> list = JsonUtils.jsonToList(result, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentId);
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		
		// 往缓存中添加数据
		try {
			// 把list转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentId), cacheString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}
