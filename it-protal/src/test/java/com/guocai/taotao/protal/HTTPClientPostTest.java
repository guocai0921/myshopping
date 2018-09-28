package com.guocai.taotao.protal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.guocai.taotao.utils.DateUtil;

public class HTTPClientPostTest {
	@Test
	public void doPost() throws Exception {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个post请求
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.html");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(post);
		// 处理相应结果
		int statusCode = response.getStatusLine().getStatusCode(); // 获取状态码
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String str = EntityUtils.toString(entity, "utf-8");
		System.out.println(str);
		// 关闭HttpClient
		response.close();
		httpClient.close();
	}

	@Test
	public void doPostWithParam() throws Exception {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个post请求
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/postparam.html");
		
		// 创建一个Entity，模拟一个表单
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "孙煜涵"));
		kvList.add(new BasicNameValuePair("password", "123456"));
		// 包装成一个Entity对象
		StringEntity entity = new UrlEncodedFormEntity(kvList,"utf-8");
		// 设置请求的内容
		post.setEntity(entity);
		
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(post);
		// 处理相应结果
		int statusCode = response.getStatusLine().getStatusCode(); // 获取状态码
		System.out.println(statusCode);
		HttpEntity httpEntity = response.getEntity();
		String str = EntityUtils.toString(httpEntity, "utf-8");
		System.out.println("输出:"+str);
		// 关闭HttpClient
		response.close();
		httpClient.close();
	}
	
	public static void main(String[] args) {
		String str = DateUtil.formatDate(new Date(), DateUtil.DEFAULT_DATE_FORMAT);
		System.out.println(str);
	}
}
