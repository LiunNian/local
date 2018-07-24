package com.paper.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 过滤辅助类
 */
public class TextUtils {
	
	/**
	 * 过滤
	 * @param key
	 * @return
	 */
	public static String delSqlSymbol(String key) {
		String regEx="(?i)(select|insert|update|delete)"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(key);
		return m.replaceAll("").trim();
	}
	
	
	/**
	 * 删除所有符号  包括中文和英文
	 * @param key
	 * @return
	 */
	public static String delAllSymbol(String key){
		String regEx="[~！@#￥%……&*（）——+|{}：。”“？》《~!@#$%^&*()_+|{}:\"<>?.]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(key);
		return m.replaceAll("").trim();
	}

	/**
	 * 过滤中文所有符号
	 * @param key
	 * @return
	 */
	public static String delChineseSymbol(String key){
		String regEx="[~！@#￥%……&*（）——+|{}：。”“？》《]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(key);
		return m.replaceAll("").trim();
	}
	
	/**
	 * 过滤英文符号
	 * @param key
	 * @return
	 */
	public static String delEnglishSymbol(String key){
		String regEx="[~!@#$%^&*()_+|{}:\\\"<>?.]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(key);
		return m.replaceAll("").trim();
	}
	
	/**
	 * 过滤字母
	 * @param key
	 * @return
	 */
	public static String delWordChar(String key){
		String regEx="[A-Za-z]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(key);
		return m.replaceAll("").trim();
	}
	
	
	/**
	 * 过滤中文汉字
	 * @param key
	 * @return
	 */
	public static String delChineseChar(String key){
		String regEx="[\\u4e00-\\u9fa5]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(key);
		return m.replaceAll("").trim();
	}
	
	public static String delNumberChar(String key) {
		String regEx="[0-9]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(key);
		return m.replaceAll("").trim();
	}
	
	public static String delSpaceAndLineTag(String key) {
		String regEx="(\\r\\n|\\n|\\t|\\s*)"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(key);
		return m.replaceAll("").trim();
	}
	
	
	/**
	 * 过滤所有标签
	 * @param htmlStr
	 * @return
	 */
	public static String delHtmlTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
        String regEx_line = "(\\r\\n|\\n|\\t|\\s*)";
         
        Pattern p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script = p_script.matcher(htmlStr); 
        htmlStr = m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style = p_style.matcher(htmlStr); 
        htmlStr = m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html = p_html.matcher(htmlStr); 
        htmlStr = m_html.replaceAll(""); //过滤html标签 

        
        Pattern p_line = Pattern.compile(regEx_line,Pattern.CASE_INSENSITIVE); 
        Matcher m_line = p_line.matcher(htmlStr); 
        htmlStr = m_line.replaceAll(""); //过滤html标签 
        return htmlStr.trim(); //返回文本字符串
    }

	
	/**
	 * 过滤Js标签
	 * @param htmlStr
	 * @return
	 */
	public static String delJsTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
         
        Pattern p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script = p_script.matcher(htmlStr); 
        htmlStr = m_script.replaceAll(""); //过滤script标签 
        return htmlStr.trim(); //返回文本字符串 
    }

	/**
	 * 只保留中文
	 * @param str
	 * @return
	 */
	public static String delStrAll(String str){
		String repl = "";
		if (str!=null) {
			Pattern p = Pattern.compile("[^\\u4E00-\\u9FA5]");
			Matcher m = p.matcher(str);
			repl = m.replaceAll("");
		}
		return repl;
	}

	/**
	 * 只保留中文和英文
	 * @param str
	 * @return
	 */
	public static String delStrToOR(String str){
		String repl = "";
		if (str!=null) {
			Pattern p = Pattern.compile("[^\\u4E00-\\u9FA5^.^a-z^A-Z^0-9]");
			Matcher m = p.matcher(str);
			repl = m.replaceAll("");
		}
		return repl;
	}

}
