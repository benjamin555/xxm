package cn.sp.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReadMessage;
import net.sf.jxls.reader.XLSReadStatus;
import net.sf.jxls.reader.XLSReader;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
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
	
	
	/**
	 * 导出Excel
	 * 
	 * @param response
	 * @param srcFilePath
	 *            模板文件的绝对路径
	 * @param dataList
	 *            数据集合
	 * @throws Exception
	 */
	public static void exportXLS(HttpServletResponse response,
			String srcFilePath, List dataList) throws Exception {
		response.setHeader("Content-disposition", "attachment; filename=export"
				+ System.currentTimeMillis() + ".xls");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		Map beanParams = new HashMap();
		beanParams.put("dataList", dataList);
		Workbook workbook = makeExcel(srcFilePath, beanParams);
		OutputStream os = new BufferedOutputStream(response.getOutputStream());
		workbook.write(os);
		
		os.flush();
		os.close();
	}

	public static Workbook makeExcel(String transformFilePath,  Map<String, Object> beanParams)
			 {
		InputStream is = null;
		org.apache.poi.ss.usermodel.Workbook workbook = null;
		try {
			
			XLSTransformer transformer = new XLSTransformer();
			 is = new BufferedInputStream(new FileInputStream(
					 transformFilePath));
			workbook = transformer
					.transformXLS(is, beanParams);
		} catch (ParsePropertyException e) {
			logger.error("error.",e);
		} catch (FileNotFoundException e) {
			logger.error("error.",e);
		} catch (InvalidFormatException e) {
			logger.error("error.",e);
		}finally{
			try {
				if (is!=null) {
					is.close();
				}
			} catch (IOException e) {
				logger.error("error.",e);
			}
		}
		return workbook;
	}


	/**
	 * 
	 * @param destFilePath
	 * @param transformFilePath
	 * @param dateMap
	 */
	public static Workbook export(String destFilePath, String transformFilePath, Map<String, Object> dateMap) {
		Workbook workbook = makeExcel(transformFilePath, dateMap);
		OutputStream os =null;
		 try {
			os = new BufferedOutputStream(new FileOutputStream(new File(destFilePath)));
			workbook.write(os );
			os.flush();
		} catch (FileNotFoundException e) {
			logger.error("error.",e);
		} catch (IOException e) {
			logger.error("error.",e);
		}finally{
			if (os!=null) {
				try {
					os.close();
				} catch (IOException e) {
					logger.error("error.",e);
				}
			}
		}
		return workbook;
		
	}

}