package com.zph.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author adminZPH 文件管理工具类
 * */
public class FileUtil {
	private static final String Home = "D:/Manager/";
	private static final String OK = "OK";
	private static final String ERROR = "ERROR";

	/**
	 * 创建本地文件夹
	 * 
	 * @param destFileName
	 * */
	public static boolean createFile(String destFileName) {
		destFileName = Home + destFileName;
		File file = new File(destFileName);
		if (file.exists()) {
			System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				System.out.println("创建目标文件所在目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				System.out.println("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				System.out.println("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out
					.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
			return false;
		}
	}

	public static boolean createDir(String destDirName) {
		destDirName = Home + destDirName;
		File dir = new File(destDirName);
		if (dir.exists()) {
			System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			System.out.println("创建目录" + destDirName + "成功！");
			return true;
		} else {
			System.out.println("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		File tempFile = null;
		if (dirName == null) {
			try {
				// 在默认文件夹下创建临时文件
				tempFile = File.createTempFile(prefix, suffix);
				// 返回临时文件的路径
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("创建临时文件失败！" + e.getMessage());
				return null;
			}
		} else {
			File dir = new File(dirName);
			// 如果临时文件所在目录不存在，首先创建
			if (!dir.exists()) {
				if (!createDir(dirName)) {
					System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");
					return null;
				}
			}
			try {
				// 在指定目录下创建临时文件
				tempFile = File.createTempFile(prefix, suffix, dir);
				return tempFile.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("创建临时文件失败！" + e.getMessage());
				return null;
			}
		}
	}

	/**
	 * @author adminZPH
	 * 
	 *         读写,直接覆盖
	 * */
	public static void writeByFileOutputStream(String url, String content) {

		FileOutputStream fop = null;
		File file;
		try {
			file = new File(Home + url);
			fop = new FileOutputStream(file);
			if (!file.exists()) {

				createFile(url);
			}
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();
			System.out.println("写入成功！");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 追加写入
	 * 
	 * @throws Exception
	 * */

	public static String writeByFileReader(String url, String content) {
		try {

			File file = new File(Home + url);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				createFile(url);
			}
			
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			// 加密
			String contentt = QEncodeUtil.aesEncrypt(content);
			bw.write(contentt);
			// 换行
			bw.write("\r\n");
			bw.flush();
			bw.close();
			return OK;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	public static List<String> readByBufferedReader(String fileName) {
		fileName = Home + fileName;
		List<String> list = new ArrayList();
		StringBuffer sb = new StringBuffer();
		try {
			File file = new File(fileName);
			// 读取文件，并且以utf-8的形式写出去
			BufferedReader bufread;
			String read;
			bufread = new BufferedReader(new FileReader(file));
			while ((read = bufread.readLine()) != null) {
				// 先进行解密 解密需要把之前的添加的\r\n去掉，因为在AES加密中，字符长度是16的整数倍
				list.add(QEncodeUtil.aesDecrypt(read.toString().replace("\r\n", "")));
			}
			bufread.close();
			return list;
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;

	}

}
