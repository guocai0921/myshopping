package com.guocai.taotao.protal;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HTTPClientGetTest {
	@Test
	public void DoGet() throws Exception {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个Get对象
		HttpGet get = new HttpGet("http://www.sogou.com");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
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
	public void DoGetWithParam() throws Exception {
		// 创建HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个URI
		URIBuilder uri = new URIBuilder("https://www.sogou.com/web");
		uri.addParameter("query", "花千骨");
		// 创建一个Get对象
		HttpGet get = new HttpGet(uri.build());
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
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
}
