package com.guocai.rest.service;

import java.util.List;

import com.guocai.pojo.TbContent;

public interface TbContentService {
	List<TbContent> getContentList(long contentId);
}
