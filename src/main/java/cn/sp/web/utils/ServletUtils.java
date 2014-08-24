package cn.sp.web.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-13 上午11:16:30
* @email benjaminchen555@gmail.com
*/
public class ServletUtils {
	private static Logger logger = LoggerFactory.getLogger(ServletUtils.class);
	
	public static String getRequestUrl(HttpServletRequest request) {
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append(request.getRequestURI());
		if (org.apache.commons.lang.StringUtils.isNotBlank(request.getQueryString())) {
			urlBuffer.append("?");
			urlBuffer.append(request.getQueryString());
		}
		
		return urlBuffer.toString();
	}
	
	public static String getRemoteAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public static String getMACAddress(HttpServletRequest request) {
		
		return getMACAddress(getRemoteAddress(request));
	}

	public static String getMACAddress(String ip) {
		String str = "";
		String macAddress = "";
		try {
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException e) {
			logger.error("error.",e);
		}
		return macAddress;
	}
	
	
	public static void writeXmlToPage(ServletResponse response, String xml) throws IOException {
		response.setContentType("application/xml;charset=UTF-8");
		response.getWriter().write(xml);
	}

	public static void writeJsonToPage(ServletResponse response, String json) throws IOException {
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(json);
	}

	public static void writeJsonToPage(ServletResponse response, Object object) throws IOException {
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(JSONObject.fromObject(object).toString());
		
	}
	
	public static void writeJsonToPage(ServletResponse response, Collection<?> c) throws IOException {
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().write(JSONArray.fromObject(c).toString());
		
	}

	public static void WriteErrorJson(ServletResponse response,String msg) throws IOException {
		response.setContentType("text/json;charset=UTF-8");
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("result", "fail");
		ret.put("msg", msg);
		response.getWriter().write(JSONObject.fromObject(ret).toString());
		
	}
}
