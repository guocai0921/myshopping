package com.guocai.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guocai.mapper.TbItemParamItemMapper;
import com.guocai.pojo.TbItemParamItem;
import com.guocai.pojo.TbItemParamItemExample;
import com.guocai.pojo.TbItemParamItemExample.Criteria;
import com.guocai.service.TbItemParamItemService;
import com.guocai.taotao.utils.JsonUtils;

@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {

	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getItemParamByItemId(long itemId) {
		// TODO Auto-generated method stub
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list==null || list.size()==0) {
			return "";
		}
		TbItemParamItem tbItemParamItem = list.get(0);
		String paramData = tbItemParamItem.getParamData();
		List<Map> maps = JsonUtils.jsonToList(paramData, Map.class);
		// 生成html
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n" );
		sb.append("	<tbody>\n" );
		for(Map m:maps) {
			sb.append("		<tr>\n" );
			sb.append("			<th class=\"tdTitle\" colspan=\"2\">"+m.get("group")+"</th>\n" );
			sb.append("		</tr>\n" );
			List<Map> list2 = (List) m.get("params");
			for(Map m1:list2) {
				sb.append("		<tr>\n" );
				sb.append("			<td class=\"tdTitle\">"+m1.get("k")+"</td>\n" );
				sb.append("			<td>"+m1.get("v")+"</td>\n" );
				sb.append("		</tr>\n" );
				
			}
		}
		sb.append("	</tbody>\n" );
		sb.append("</table>");
		return sb.toString();
	}

}
