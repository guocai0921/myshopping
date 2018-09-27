package com.guocai.taotao.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp上传下载工具类
 * <p>
 * Title: FtpUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 鬼谷神
 * @date 2019年9月21日下午8:11:51
 * @version 1.0
 */
public class FtpUtil {

	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param host     FTP服务器hostname
	 * @param port     FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param basePath FTP服务器基础目录
	 * @param filePath FTP服务器文件存放路径。例如分日期存放：/2019/09/04。文件的路径为basePath+filePath
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input    输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String host, int port, String username, String password, String basePath,
			String filePath, String filename, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.setControlEncoding("utf-8");
			ftp.connect(host, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			// 切换到上传目录
			if (!ftp.changeWorkingDirectory(basePath + filePath)) {
				// 如果目录不存在创建目录
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir))
						continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			// 设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode(); // 图片为空
			// 上传文件
			if (!ftp.storeFile(filename, input)) {
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param host       FTP服务器hostname
	 * @param port       FTP服务器端口
	 * @param username   FTP登录账号
	 * @param password   FTP登录密码
	 * @param remotePath FTP服务器上的相对路径
	 * @param fileName   要下载的文件名
	 * @param localPath  下载后保存到本地的路径
	 * @return
	 */
	public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());

					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	/**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param host       FTP服务器hostname
	 * @param port       FTP服务器端口
	 * @param username   FTP登录账号
	 * @param password   FTP登录密码
	 * @param remotePath FTP服务器上的相对路径
	 * @param filePath   数据库保存的文件路径
	 * @return
	 */
	public static boolean deleteFile(String host, int port, String username, String password, String remotePath,
			String filePath) {
		boolean result = false;
		FTPClient ftp = new FTPClient();

		try {
			int reply;
			ftp.connect(host, port);
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			String str = "images";
			String tempPath = filePath.substring(filePath.indexOf(str) + str.length(), filePath.lastIndexOf("/"));
			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
			ftp.changeWorkingDirectory(remotePath+tempPath);// 转移到FTP服务器目录
			result = ftp.deleteFile(fileName);
			ftp.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		
		try {  
			FileInputStream in = new FileInputStream(new File("D:\\idea\\images\\meinv.jpg"));
			boolean flag = uploadFile("192.168.254.135", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images",
					"/2018/09/04", "qqq.jpg", in);
			System.out.println(flag); 
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }

		boolean result = deleteFile("192.168.254.135", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images",
				"http://192.168.254.135/images/2018/09/20/1537434479738374.jpg");
		System.out.println(result);
		

	}
}
