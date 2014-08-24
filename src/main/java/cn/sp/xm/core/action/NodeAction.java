package cn.sp.xm.core.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.sp.web.utils.ServletUtils;
import cn.sp.xm.core.entity.Node;
import cn.sp.xm.core.service.NodeService;

import com.opensymphony.xwork2.ActionSupport;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-12 下午5:28:48
* @email benjaminchen555@gmail.com
*/
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.Exception", result = "exception") })
@Results({ @Result(name = "exception", location = "/common/error.jsp") })
public class NodeAction extends ActionSupport {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private NodeService nodeService;

	private Node node;

	public String read() {

		String userId = "";
		if (StringUtils.isBlank(userId)) {
			//			HttpServletRequest request = ServletActionContext.getRequest();
			//			String macAddress = ServletUtils.getMACAddress(request);
			node = nodeService.findLatestNode(null, null);
		}

		return "read";
	}

	public String readNext() {

		Node n = nodeService.readNext(node.getId(), null);
		ServletResponse response = ServletActionContext.getResponse();
		try {
			if (n == null) {
				String msg = "不存在下一段";
				ServletUtils.WriteErrorJson(response, msg);
				return null;
			}

			ServletUtils.writeJsonToPage(response, n.toJsonString());
		} catch (IOException e) {
			logger.error("error.", e);
		}

		return null;
	}

	public String readPrev() {
		logger.info("node:{]",node.toJsonString());
		String ret = null;
		String id = node.getParentNode().getId();
		ServletResponse response = ServletActionContext.getResponse();
		try {
			if (StringUtils.isBlank(id)) {
				String msg = "不存在上一段";
				ServletUtils.WriteErrorJson(response, msg);
			} else {
				Node n = nodeService.read(id, null);
				ret = n.toJsonString();
				ServletUtils.writeJsonToPage(response, ret);
			}
		} catch (IOException e) {
			logger.error("error.", e);
		}

		return null;
	}

	public String readNextSibling() {
		logger.info("node:{}",node.toJsonString());
		Node n = nodeService.readNextSibling(node.getId(), null);
		ServletResponse response = ServletActionContext.getResponse();
		try {
			if (n == null) {
				String msg = "不存在下一段";
				ServletUtils.WriteErrorJson(response, msg);
				return null;
			}
			ServletUtils.writeJsonToPage(response, n.toJsonString());
		} catch (IOException e) {
			logger.error("error.", e);
		}

		return null;
	}

	public String readPrevSibling() {
		logger.info("node:{}",node.toJsonString());
		Node n = nodeService.readPrevSibling(node.getId(), null);
		ServletResponse response = ServletActionContext.getResponse();
		try {
			if (n == null) {
				String msg = "不存在上一段";
				ServletUtils.WriteErrorJson(response, msg);
				return null;
			}
			ServletUtils.writeJsonToPage(response, n.toJsonString());
		} catch (IOException e) {
			logger.error("error.", e);
		}

		return null;
	}

	public String zan() {
		nodeService.zan(node.getId(), null);
		ServletResponse response = ServletActionContext.getResponse();
		try {
			Map<String, String> ret = new HashMap<String, String>();
			ret.put("result", "pass");
			ServletUtils.writeJsonToPage(response, ret);
		} catch (IOException e) {
			logger.error("error.", e);
		}

		return null;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

}
