package com.guocai.search.service.impl;

import java.util.List;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guocai.search.mapper.ItemMapper;
import com.guocai.search.pojo.Item;
import com.guocai.search.service.ItemService;
import com.guocai.taotao.utils.ExceptionUtil;
import com.guocai.taotao.utils.TaotaoResult;

@Service
public class ItemServiceImpl implements ItemService {
	
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrServer solrServer;

	@Override
	public TaotaoResult importAllItems()  {
		// TODO Auto-generated method stub
		List<Item> list = itemMapper.findAll();
		try {
			for (Item item : list) {
				// 创建一个SolrInputDocument对象
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSellPoint());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategoryName());
				document.setField("item_desc", item.getItemDesc());
				// 写入索引库
				solrServer.add(document);
			}
			// 提交数据
			solrServer.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		} 
		return null;
	}

}
