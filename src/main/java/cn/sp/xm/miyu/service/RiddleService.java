package cn.sp.xm.miyu.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;

import cn.sp.service.IBaseService;
import cn.sp.utils.JxlsUtils;
import cn.sp.xm.miyu.dao.RiddleDao;
import cn.sp.xm.miyu.entity.Riddle;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-19 下午2:17:25
* @email benjaminchen555@gmail.com
*/
@Service
@Transactional
@SuppressWarnings({"rawtypes","unchecked"})
public class RiddleService implements IBaseService<Riddle,Long>{
	private static final int ZAN = 20;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private RiddleDao dao;

	public Riddle getById(Long id) {
		return dao.getById(id);
	}

	public void save(Riddle entity) {
		dao.save(entity);		
	}

	public void delete(Riddle entityObject) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	public List<Riddle> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void zan(Long id) {
		Riddle riddle = getById(id);
		riddle.setZan(riddle.getZan()+1);
		save(riddle);
	}


	public Page<Riddle> getPage(int start, int size, Map<String, String> searchMap) {
		return dao.findPage(start, size,searchMap);
	}

	/**
	 * 推荐一个谜语
	 * 推荐赞
	 * @return
	 */
	public Riddle recomend() {
		//值得推荐的赞数
		int zan = ZAN;
		Map<String, String> searchMap = new LinkedHashMap<String, String>();
		searchMap.put("flt_r_and_geI_zan", zan+"");
		Page<Riddle> rPage = dao.findRandomPage(1, searchMap);
		return rPage.getResult().get(0);
	}

	
	
	public void importExcel(InputStream inputXLS, InputStream inputXML) {
		logger.info("importExcel.");
		Map beans = new HashMap();
		List<Riddle> r = new ArrayList<Riddle>();
		beans.put("result01", r);
		try {
			JxlsUtils.readXLS(inputXLS, inputXML, beans);
		} catch (Exception e) {
			logger.error("error", e);
		}

		for (Riddle hero : r) {
			save(hero);
		}
	}

	public Page<Riddle> getRandomPageByLen(int size, int len) {
		return dao.findRandomPageByLen( size,len);
	}


}
