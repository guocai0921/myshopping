package com.guocai.date;

import java.util.Date;

import com.guocai.taotao.utils.DateUtil;

public class DateTest {
	public static void main(String[] args) {
		String date = DateUtil.formatDate(new Date(), DateUtil.DEFAULT_DATE_FORMAT);
		System.out.println(date);
	}
}
