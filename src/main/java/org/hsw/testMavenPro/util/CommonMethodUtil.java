package org.hsw.testMavenPro.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class CommonMethodUtil {
	private static final Logger logger = Logger.getLogger(CommonMethodUtil.class);
	/**
	 * 判断是否为邮箱
	 * @param email 要验证的邮箱地址
	 * @return
	 */
	public static boolean ifEmail(String email){
		if(StringUtils.isEmpty(email)){
			return false;
		}
		Pattern p = Pattern.compile(ConstantUtil.EMAIL_REG);     
        Matcher m = p.matcher(email);
        return m.matches();
	}
	
	/**
	 * md5加密
	 * @param str 要加密的字符串
	 * @return
	 */
	public static String encodeByMd5(String str){
		MessageDigest messagedigest;
		try {
			messagedigest = MessageDigest.getInstance("MD5");
			messagedigest.update(str.getBytes());
			return new BigInteger(1, messagedigest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			return "";
		}
	}
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	public static FileCommonBean uploadFile(MultipartFile file){
		if(file!=null){
			String fileName = file.getOriginalFilename();
			if(StringUtils.isEmpty(fileName)){
				return null;
			}
			String[] types = fileName.split("\\.");
			String fileType = types[1];
			//long fileSize = file.getSize();
	        Calendar now = Calendar.getInstance();  
			//重命名文件名
			String newFileName = now.getTimeInMillis()+"."+fileType;
			String year = now.get(Calendar.YEAR)+"";
			String month = (now.get(Calendar.MONTH)+1)+"";
			String date = now.get(Calendar.DAY_OF_MONTH)+"";
			String dirName = year+month+date;
			
			//新的路径
			String path = "C:/softWare/apacheFileServer/images/testMavenPro/"+dirName+"/"+newFileName;
			
			File newFile = new File(path);
			if(!newFile.exists()){
				newFile.mkdirs();
			}
			try {
				file.transferTo(newFile);
				FileCommonBean fileCommonBean = new FileCommonBean();
				fileCommonBean.setFileName(fileName);
				fileCommonBean.setFileUrl("http://127.0.0.1:88/images/testMavenPro/"+dirName+"/"+newFileName);
				return fileCommonBean;
			} catch (IOException e) {
				logger.error(e);
				System.out.println("上传失败！");
				return null;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 物理删除一个文件
	 * @param fileUrl 文件路径
	 * @return
	 */
	public static boolean removeFile(String fileUrl){
		if(StringUtils.isNotEmpty(fileUrl)){
			String[] urls = fileUrl.split("127.0.0.1:88");
			String url = urls[1];
			String path = "C:/softWare/apacheFileServer/"+url;
			File file = new File(path);
			if(file.exists()){
				file.delete();
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
}
