package cn.sp.xm.core.service;

import java.util.List;

import cn.sp.xm.core.entity.Node;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-9 上午9:23:22
* @email benjaminchen555@gmail.com
*/
public interface NodeService {

	Node addContribution(String nodeId, String content, String operator);

	List<Node> findByParent(String pnId);

	Node zan(String id,String operator);

	/**
	 * 阅读下个节点，为子节点中票数最多的
	 * @param id
	 * @param operatorId 
	 * @return
	 */
	Node readNext(String id, String operatorId);

	/**
	 * 阅读下一个兄弟节点
	 * @param id
	 * @param userId
	 * @return
	 */
	Node readNextSibling(String id, String userId);

	/**
	 * 阅读上一个兄弟节点
	 * @param id
	 * @param userId
	 * @return
	 */
	Node readPrevSibling(String id, String userId);

	void deleteByParent(String pnId);


	/**
	 * 返回当前用户最近一次阅读的节点
	 * 当前用户为空，则根据mac地址获取最近阅读节点；mac地址也不存在则，返回根节点
	 * @param userId
	 * @return
	 */
	Node findLatestNode(String userId,String macAddress);

	/**
	 * 阅读某节点
	 * @param id
	 * @param operatorId
	 * @return
	 */
	Node read(String id, String operatorId);
	
}
