package com.guocai.search.service;

import com.guocai.search.pojo.SearchResult;

public interface ItemSearchService {
	public SearchResult searchItem(String queryString, Integer page) throws Exception;
}
