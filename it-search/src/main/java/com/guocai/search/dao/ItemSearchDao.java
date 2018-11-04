package com.guocai.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.guocai.search.pojo.SearchResult;

public interface ItemSearchDao {
	public SearchResult searchItem(SolrQuery solrQuery) throws Exception;
}
