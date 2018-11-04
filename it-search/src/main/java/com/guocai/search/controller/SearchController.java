package com.guocai.search.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guocai.search.pojo.SearchResult;
import com.guocai.search.service.ItemSearchService;
import com.guocai.taotao.utils.ExceptionUtil;
import com.guocai.taotao.utils.TaotaoResult;

@Controller
public class SearchController {

	@Autowired
	private ItemSearchService itemSearchService;

	@RequestMapping(value="/query", method = RequestMethod.GET)
	@ResponseBody
	public TaotaoResult search(@RequestParam(value = "kw") String queryString,
			@RequestParam(value = "page", defaultValue = "1") Integer page) {

		if (StringUtils.isBlank(queryString)) {
			return TaotaoResult.build(400, "查询条件是必须的参数");
		}
		SearchResult result = null;
		try {
			// 解绝get请求乱码问题
			queryString = new String(queryString.getBytes("ISO8859-1"), "utf-8");
			result = itemSearchService.searchItem(queryString, page);

		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}

		return TaotaoResult.ok(result);
	}

}
