package com.ruoyi.project.upload;

import java.io.File;
import java.util.Calendar;
/**
 * 任金涛
 * 系统上传文件路径工具类
 * @author victory
 *
 */
public class FolderUtil {
	/***
	 * 获取上传路径 服务器
	 * 虚拟目录使用
	 * @param dir
	 * @return
	 */
 	public static String[] getDir(String dir) {
 		String str[] =new String[2];
 		Calendar cal = Calendar.getInstance();
 		String year = String.valueOf(cal.get(Calendar.YEAR));
 		String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
 		// File filemonth1=new File("c:\\"+year+"\\"+month+"");
 		File fileLocation = new File(dir + "/" + year + "/" + month + "");
 		if (!fileLocation.exists())
 			fileLocation.mkdirs();
 		str[0]=dir + "/" + year + "/" + month + "/";//绝对地址
 		str[1]="/" + year + "/" + month + "/";//上传服务器地址
 		return str;
 	}
}
