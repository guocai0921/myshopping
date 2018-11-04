package com.guocai.restful.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrjTest {

	/**
	 * 添加和修改solr索引
	 * 
	 * @throws Exception
	 */
	@Test
	public void addDocumnt() throws Exception {
		// 创建一个连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.254.135:8983/solr/collection1");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test002");
		document.addField("item_title", "测试商品2");
		document.addField("item_price", "67890");
		// 把文档对象写进索引库
		solrServer.add(document);
		// 提交
		solrServer.commit();
	}

	@Test
	public void deleteDocument() throws Exception {
		// 创建一个连接
		SolrServer solrServer = new HttpSolrServer("http://192.168.254.135:8983/solr/collection1");
		solrServer.deleteById("test002");
		// solrServer.deleteByQuery("*:*");   // 删除所有
		solrServer.commit();
	}

}
