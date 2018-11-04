package com.guocai.search.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.guocai.search.dao.ItemSearchDao;
import com.guocai.search.pojo.SearchResult;
import com.guocai.search.service.ItemSearchService;

public class ItemSearchServiceImpl implements ItemSearchService {
	
	@Value("${SEARCH_RESULT_PAGE_SIZE}")
	private Integer PAGE_SIZE;
	@Autowired
	private ItemSearchDao itemSearchDao;

	@Override
	public SearchResult searchItem(String queryString, Integer page) throws Exception {
		// 创建一个查询对象
		SolrQuery solrQuery = new SolrQuery();
		// 查询条件
		if (StringUtils.isBlank(queryString)) {
			solrQuery.setQuery("*:*");
		} else {
			solrQuery.setQuery(queryString);
		}
		// 分页条件
		if (page == null) {
			page = 1;
		}
		solrQuery.setStart((page - 1) * PAGE_SIZE);
		solrQuery.setRows(PAGE_SIZE);
		// 高亮显示
		solrQuery.setHighlight(true);
		// 设置高亮显示的域
		solrQuery.addHighlightField("item_title");
		// 高亮显示前缀
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		// 后缀
		solrQuery.setHighlightSimplePost("</em>");
		// 设置默认搜索域
		solrQuery.set("df", "item_keywords");

		// 执行查询
		SearchResult result = itemSearchDao.searchItem(solrQuery);
		// 计算分页
		Long recordCount = result.getRecordCount();
		int pageCount = (int) (recordCount / PAGE_SIZE);
		if (recordCount % PAGE_SIZE > 0) {
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurPage(page);

		return result;
	}

}
