package com.guocai.rest.service;

import com.guocai.taotao.utils.TaotaoResult;

public interface RedisService {
	TaotaoResult syncContent(long contentCid);
}
