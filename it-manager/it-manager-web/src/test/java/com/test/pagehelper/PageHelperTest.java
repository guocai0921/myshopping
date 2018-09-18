package com.test.pagehelper;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guocai.mapper.TbItemMapper;
import com.guocai.pojo.TbItem;
import com.guocai.pojo.TbItemExample;

public class PageHelperTest {
	
	long startTime = 0L;
	long endTime = 0L;

	@Before
	public void setUp() throws Exception {
		startTime = System.currentTimeMillis();
		System.out.println(startTime);
	}

	@After
	public void tearDown() throws Exception {
		endTime = System.currentTimeMillis();
		System.out.println("耗时:"+(endTime - startTime));
	}

	@Test
	public void test() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext-*.xml");
		TbItemMapper tbItemMapper = ac.getBean(TbItemMapper.class);
		TbItemExample tExample = new TbItemExample();
		// 分页处理
		PageHelper.startPage(1, 10);
		List<TbItem> items = tbItemMapper.selectByExample(tExample);
		// 取商品列表
		for (TbItem tbItem : items) {
			System.out.println(tbItem);
		}
		PageInfo<TbItem> info = new PageInfo<>(items);
		long total = info.getTotal();
		System.out.println("共有商品数量:"+total);
		System.out.println(ac);
	}

}
