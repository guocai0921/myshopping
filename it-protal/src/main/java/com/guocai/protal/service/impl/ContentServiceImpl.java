package com.guocai.protal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.guocai.pojo.TbContent;
import com.guocai.protal.service.ContentService;
import com.guocai.taotao.utils.HttpClientUtil;
import com.guocai.taotao.utils.JsonUtils;
import com.guocai.taotao.utils.TaotaoResult;
@Service
public class ContentServiceImpl implements ContentService {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_INDEX_AD_URL}")
	private String REST_INDEX_AD_URL;
	
	@Override
	public String getContent() {
		// 调用服务层的服务
		System.out.println("基础路径"+REST_BASE_URL);
		System.out.println("请求数据"+REST_INDEX_AD_URL);
		String result = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
		// 将字符串转换成TaotaoResult
		try {
			TaotaoResult list = TaotaoResult.formatToList(result, TbContent.class);
			// 取内容列表
			@SuppressWarnings("unchecked")
			List<TbContent> tbContents = (List<TbContent>) list.getData();
			// 创建一个jsp页码要求的pojo列表
			List<Map<String, Object>> list2 = new ArrayList<>();
			for (TbContent tbContent : tbContents) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("src",tbContent.getPic());
				map.put("height",240);
				map.put("width",670);
				map.put("srcB",tbContent.getPic2());
				map.put("heightB",550);
				map.put("widthB",240);
				map.put("href",tbContent.getUrl());
				map.put("alt",tbContent.getSubTitle());
				list2.add(map);
			}
			return JsonUtils.objectToJson(list2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
