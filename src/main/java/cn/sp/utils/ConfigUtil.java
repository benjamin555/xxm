package cn.sp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置工具类，用于取配置文件中的键值信息
 * 
 */
public class ConfigUtil {
	private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

	private static String configFilePath = "/socreComment.properties";

	public static Properties properties = null; // 属性集

	/**
	 * 根据配置文件构建配置工具类
	 * 
	 * @param configFilePath
	 *            配置文件路径
	 * @throws Exception
	 *             通用异常
	 */
	static {
		try {
			init(configFilePath);
		} catch (Exception e) {
			logger.error("error.",e);
		}
	}

	/**
	 * 被始化本工具类
	 * 
	 * @param configFilePath
	 *            配置文件路径
	 * @throws Exception
	 *             通用异常
	 */
	private static void init(String configFilePath) throws Exception {
		File f0 = new File(ConfigUtil.class.getResource(configFilePath).toURI());
		if (!f0.exists()) {
			logger.error("configuration does not exist");
			return;
		}
		properties = new Properties();
		FileInputStream fis = new FileInputStream(f0);
		try {
			properties.load(fis);
		} catch (IOException e) {
			logger.error("error.",e);
			return;
		} finally {
			fis.close();
		}
	}

	/**
	 * 得到默认类实例对象的属性集
	 * 
	 * @return 属性集
	 */
	public static Properties getProperties() {
		return properties;
	}

	/**
	 * 得到字符串值属性
	 * 
	 * @param key
	 *            键的名称
	 * @return 字符串值
	 */
	public static String getString(String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * 得到字符串值属性
	 * 
	 * @param key
	 *            键的名称
	 * @return 字符串值
	 */
	public static int getInt(String key) {
		return Integer.parseInt(properties.getProperty((key)));
	}


}
