package com.guocai.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {
	@Test
	public void ftpTest() throws SocketException, IOException {
		// 创建一个FTPClient对象
		FTPClient ftpClient = new FTPClient();
		// 创建ftp连接，默认端口21
		ftpClient.connect("192.168.254.135", 21);
		// 登录ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		// 读取本地文件
		FileInputStream inputStream = new FileInputStream(new File("D:\\idea\\images\\timg1.jpg"));
		// 设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		// 修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 第一个参数：上传的文档的名字。第二个参数：一个输入流
		ftpClient.storeFile("123.jpg", inputStream);
		// 关闭连接
		ftpClient.logout();
	}
}
