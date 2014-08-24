package cn.sp.xm.core.dao;

import java.util.List;
import java.util.Map;

import cn.sp.xm.core.entity.Node;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-9 上午9:55:04
* @email benjaminchen555@gmail.com
*/
public interface NodeDao {

	void create(Node node);
	
	List<Node> find(Map<String, String> searchMap);

	Node findRoot();

	Node findById(String id);

	void update(Node node);
	
	List<Node> findChildrenBySize(String pId,int size,Map<String, String> orderMap);

	/**
	 * 寻找下个兄弟节点
	 * @param id
	 * @param b true:正序;false:反序
	 * @return
	 */
	Node findNextSibling(String id, boolean b);

	void deleteByParentId(String pnId);

}
