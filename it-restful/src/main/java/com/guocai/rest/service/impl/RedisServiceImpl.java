package com.guocai.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.guocai.rest.dao.JedisClient;
import com.guocai.rest.service.RedisService;
import com.guocai.taotao.utils.ExceptionUtil;
import com.guocai.taotao.utils.TaotaoResult;

public class RedisServiceImpl implements RedisService {

	@Autowired
	private JedisClient jedisClient;

	@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;

	@Override
	public TaotaoResult syncContent(long contentCid) {
		// TODO Auto-generated method stub
		try {
			jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, String.valueOf(contentCid));
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
