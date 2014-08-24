package cn.sp.service;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 所有Service的父接口
 * 
 * @author yong.gao
 *
 * @param <T> 泛型对象
 * @param <PK> 泛型对象主键
 */
public interface IBaseService<T, PK extends Serializable> {
	/**
	 * 根据主键获取对象
	 * @param id 对象主键
	 * @return 对象
	 */
	public T getById(PK id);
	
	/**
	 * 保存对象
	 * @param entityObject 待保存对象
	 */
	public void save(T entityObject);
	
	/**
	 * 删除对象
	 * @param entityObject 待删除对象
	 */
	public void delete(T entityObject);

	/**
	 * 根据主键删除对象
	 * @param id 主键
	 */
	public void deleteById(PK id);
	
	/**
	 * 获取所有对象
	 * @return 所有对象
	 */
	public List<T> getAll();
}
