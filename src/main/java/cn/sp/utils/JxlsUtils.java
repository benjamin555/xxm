package cn.sp.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadMessage;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用JXLs组件操作excel
 * 
 * @author yong.gao
 * 
 */
/*
 * Revision Date    Revised By  Description
 * ---------------------------------------------------
 * 2013/06/25       ajiang      #4449 无法合并打包采办申请 ChangeMethod(checkAndSuitApply)
 * ---------------------------------------------------
 */
@SuppressWarnings("unchecked")
public class JxlsUtils {

	private static Logger logger = LoggerFactory.getLogger(JxlsUtils.class);

	/**
	 * 根据excel文件路径，xml路径获取excel中的对象列表
	 * 
	 * @param xlsPath
	 *            excel文件路径
	 * @param xmlPath
	 *            xml路径
	 * @param beans
	 *            Map对象，内容包含对象名称、对象的键值对
	 * @return excel中的对象列表
	 */
	@SuppressWarnings("rawtypes")
	public static void readXLS(InputStream inputXLS, InputStream inputXML, Map beans) throws Exception {
		InputStream inputXLS1 = null;
		try {
			// 转换成XLSReaderd对象
			XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
			// 根据excel文件路径获取输入流
			inputXLS1 = new BufferedInputStream(inputXLS);

			// 读取xls文件，获取的对象列表放入result列表中
			XLSReadStatus readStatus = mainReader.read(inputXLS1, beans);

			// 获取读取信息
			List<XLSReadMessage> msgs = readStatus.getReadMessages();
			if (CollectionUtils.isNotEmpty(msgs)) {
				// 记录读取信息
				for (XLSReadMessage msg : msgs) {
					logger.info("readMessages is {}", msg.getMessage());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error is {}", e);
			throw e;
		} finally {
			if (inputXLS1 != null) {
				inputXLS1.close();
			}

		}
	}

}