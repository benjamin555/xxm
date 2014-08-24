package cn.sp.xm.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sp.xm.core.dao.NodeDao;
import cn.sp.xm.core.dao.UserDao;
import cn.sp.xm.core.entity.Node;
import cn.sp.xm.core.entity.User;
import cn.sp.xm.core.service.NodeService;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-9 上午9:24:51
* @email benjaminchen555@gmail.com
*/
@Service
@Transactional
public class NodeServiceImpl implements NodeService {

	@Autowired
	private NodeDao dao;
	@Autowired
	private UserDao userDao;

	public Node addContribution(String nodeId, String content, String operatorId) {
		Node node = new Node();
		if (StringUtils.isNotBlank(nodeId)) {
			Node pn = new Node();
			pn.setId(nodeId);
			node.setParentNode(pn);
		}
		
		node.setContent(content);
		node.setCreateTime(new Date());
		if (StringUtils.isNotBlank(operatorId)) {
			node.setCreator(operatorId);
		}
		dao.create(node);
		return node;
	}

	public List<Node> findByParent(String pnId) {
		List<Node> nodes = new ArrayList<Node>();
		if (StringUtils.isBlank(pnId)) {
			nodes.add(dao.findRoot());
		}else {
			Map<String, String> searchMap = new HashMap<String, String>();
			searchMap.put("flt_p_and_eqS_id", pnId);
			nodes=dao.find(searchMap );
		}
		return nodes;
	}

	public Node zan(String id,String updater) {
		Node node = dao.findById(id);
		node.setZan(node.getZan()+1);
		node.setSumZan(node.getSumZan()+1);
		node.setUpdateTime(new Date());
		node.setUpdater(updater);
		dao.update(node);
		return node;
	}

	public Node readNext(String id,String operatorId) {
		Map<String, String> orderMap = new LinkedHashMap<String, String>();
		orderMap.put("sort_n_zan", "desc");
		orderMap.put("sort_n_createTime", "desc");
		
		List<Node> children = dao.findChildrenBySize(id, 1,orderMap);
		if(children.isEmpty()){
			return null;
		}
		Node node =null;
		node = children.get(0);
		updateLatestNode(operatorId, node);
		
		return node;
	}

	protected void updateLatestNode(String operatorId, Node node) {
		if (StringUtils.isBlank(operatorId)) {
			return;
		}
		User u = userDao.findById(operatorId);
		u.setLatestNode(node);
	}

	public Node readNextSibling(String id, String operatorId) {
		
		
		
		Node node = dao.findNextSibling(id,true);
		if (node!=null) {
			updateLatestNode(operatorId, node);
		}
		
		return node;
	}

	public Node readPrevSibling(String id, String operatorId) {
		
		
		Node node = dao.findNextSibling(id,false);
		if (node!=null) {
			updateLatestNode(operatorId, node);
		}
		return node;
	}

	public void deleteByParent(String pnId) {
		dao.deleteByParentId(pnId);
	}
	
	public Node findLatestNode(String userId,String macAddress) {
		Node node = null;
		if (StringUtils.isNotBlank(userId)) {
			User u = userDao.findById(userId);
			node= u.getLatestNode();
		}else if (StringUtils.isNotBlank(macAddress)) {
			//TODO find node by Mac
		}
		
		if (node==null) {
			node = dao.findRoot();
		}
		
		return node;
	}

	public Node read(String id, String operatorId) {
		Node node = dao.findById(id);
		updateLatestNode(operatorId, node);
		return node;
	}

}
