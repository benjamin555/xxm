package cn.sp.xm.fi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sp.persistent.BaseEntity;
import cn.sp.service.IBaseService;
import cn.sp.xm.fi.dao.FiItemDao;
import cn.sp.xm.fi.entity.FiItem;

/**
* @author 陈嘉镇
* @version 创建时间：2014-10-27 下午4:21:25
* @email benjaminchen555@gmail.com
*/
@Service
@Transactional
@SuppressWarnings({"rawtypes","unchecked"})
public class FiItemService implements IBaseService<FiItem,Long>{
	@Autowired
	private FiItemDao fiItemDao;
	public void save(FiItem i) {
		fiItemDao.save(i);
	}
	@Override
	public FiItem getById(Long id) {
		return fiItemDao.getById(id);
	}
	@Override
	public void delete(FiItem entityObject) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteById(Long id) {
		
		FiItem entityObject = getById(id);
		entityObject.setIsUse(BaseEntity.NOTUSING);
		fiItemDao.save(entityObject );
		
	}
	@Override
	public List<FiItem> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<FiItem> getByDate(int year, int month) {
		return fiItemDao.getByDate(year,month);
	}

}
