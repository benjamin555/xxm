package cn.sp.xm.miyu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.Page;

import cn.sp.action.CrudActionSupport;
import cn.sp.utils.ConfigUtil;
import cn.sp.web.utils.ServletUtils;
import cn.sp.xm.miyu.entity.Riddle;
import cn.sp.xm.miyu.entity.Tip;
import cn.sp.xm.miyu.service.RiddleService;

/**
* @author 陈嘉镇
* @version 创建时间：2014-8-20 上午10:24:36
* @email benjaminchen555@gmail.com
*/
@SuppressWarnings({"serial","unchecked"})
@Controller
@Scope("prototype")
@Namespace("/miyu")
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.Exception", result = "exception") })
@Results({ @Result(name = "exception", location = "/common/error.jsp") 
			,@Result(name="show",type="redirect",location="riddle!show.action",params={"id","${riddle.id}"})
			,@Result(name="showRight",type="redirect",location="riddle!showRight.action",params={"id","${riddle.id}"})
			,@Result(name="challenge-score",type="redirect",location="riddle!score.action",params={"score","${score}"})
			,@Result(name="challenge",type="redirect",location="riddle!chaDetail.action",params={"id","${riddle.id}"})
})
public class RiddleAction extends CrudActionSupport<Riddle> {
	
	private int len;
	/**
	 * 最大挑战题目数
	 */
	private static final int MAX_QUESTION_NO = ConfigUtil.getInt("MAX_QUESTION_NO");
	/**
	 * 满分
	 */
	private static final int FULL_SCORE=100;
	/**
	 * 每一题的分数
	 */
	private static final int PER_SCORE = FULL_SCORE/MAX_QUESTION_NO;
	
	private Riddle riddle;
	@Autowired
	private RiddleService riddleService;
	
	private File dataExcel;
	
	private String dataExcelContentType;
	private String dataExcelFileName;
	
	private boolean pass;
	
	private String comment;
	
	private int score;
	
	private int deduction = 0;
	
	public Riddle getModel() {
		if (riddle==null) {
			riddle = new Riddle();
		}
		return riddle;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//过滤空的tip
		List<Tip> tips = riddle.getTips();
		if (tips!=null) {
			CollectionUtils.filter(tips, new Predicate() {
				@Override
				public boolean evaluate(Object object) {
					Tip tip = (Tip)object;
					return StringUtils.isNotBlank(tip.getContent());
				}
			});
			//排序
			for (int i = 1; i <=tips.size(); i++) {
				Tip	t = tips.get(i-1);
				t.setOrd(i);
				
				t.setRiddle(riddle);
				
			}
		}
		
		riddleService.save(riddle);
		return "show";
	}
	
	public String show() throws Exception {
		if (riddle==null||riddle.getId()==null) {
			riddle = riddleService.recomend();
		}else {
			riddle = riddleService.getById(riddle.getId());
		}
		
		return "detail";
		
	}
	public String score()throws Exception{
		comment = parseComment(score);
		return "score";
	}
	
	public String challenge() throws Exception {
		//初始化分数和当前题数
		ServletActionContext.getRequest().getSession().setAttribute("score", 0);
		ServletActionContext.getRequest().getSession().setAttribute("questionNo", 1);
		
		riddle = riddleService.recomend();
		return "challenge";
	}
	
	public String chaDetail() {
		riddle = riddleService.getById(riddle.getId());
		return "chaDetail";
	}
	
	public String challengeNext() throws Exception {
		if (!pass) {
			return challenge();
		}
		Integer score =(Integer) ServletActionContext.getRequest().getSession().getAttribute("score");
		Integer questionNo =(Integer) ServletActionContext.getRequest().getSession().getAttribute("questionNo");
		if(score==null){
			logger.info("session is expired.");
			return challenge();
		}
		
		score+=PER_SCORE-deduction;
		logger.info("deduction:{}",deduction);
		questionNo+=1;
		
		ServletActionContext.getRequest().getSession().setAttribute("score", score);
		ServletActionContext.getRequest().getSession().setAttribute("questionNo", questionNo);
		
		if (questionNo>MAX_QUESTION_NO) {
			//结束挑战,给评语
			
			this.setScore(score);
			return "challenge-score";
		}else {
			riddle = riddleService.recomend();
			Collection<String> messages = new ArrayList<String>();
			messages.add("现在是第"+questionNo+"道题。共"+MAX_QUESTION_NO+"道题");
			setActionMessages(messages);
			return "challenge";
		}
		
	}
	
	
	
	private String parseComment(Integer score2) {
		String c = "";
		if (score2<60) {
			c = ConfigUtil.getString("0");
		}else if (score2>=60&&score2<65) {
			c = ConfigUtil.getString("60");
		}else if (score2>=65&&score2<70) {
			c = ConfigUtil.getString("65");
		}else if (score2>=70&&score2<75) {
			c = ConfigUtil.getString("70");
		}else if (score2>=75&&score2<80) {
			c = ConfigUtil.getString("75");
		}else if (score2>=80&&score2<85) {
			c = ConfigUtil.getString("80");
		}else if (score2>=85&&score2<90) {
			c = ConfigUtil.getString("85");
		}else if (score2>=90&&score2<95) {
			c = ConfigUtil.getString("90");
		}else if (score2>=95&&score2<100) {
			c = ConfigUtil.getString("95");
		}else if (score2>=100) {
			c = ConfigUtil.getString("100");
		}else {
			c ="你怎么做到的？";
		}
		
		return c;
	}

	public String showRight() throws Exception {
		riddle = riddleService.getById(riddle.getId());
		return "right";
		
	}
	
	public String importExcel() throws Exception {
		InputStream inputXLS = new FileInputStream(dataExcel);
		InputStream inputXML = getClass().getResourceAsStream("/excelXMLConfig/excelMappingRiddle.xml");
		riddleService.importExcel( inputXLS,  inputXML);
		return "import";
		
	}
	
	
	public String check() throws Exception {
		Riddle r = riddleService.getById(riddle.getId());
		Collection<String> messages = new ArrayList<String>();
		if (riddle.getAnswer().trim().equals(r.getAnswer().trim())) {
			riddle =r;
			return "showRight";
		}else {
			
			messages.add("答错了！再想想。");
			setActionMessages(messages );
			riddle =r;
			return "detail";
		}
		
	}
	
	public String getAnsersByLen() throws Exception {
		Page<Riddle> page = riddleService.getRandomPageByLen(5,len);
		List<Riddle> ls = page.getResult();
		Collection<String> sls = CollectionUtils.collect(ls, new Transformer() {
			public Object transform(Object input) {
				Riddle riddle = (Riddle) input;
				return riddle.getAnswer();
			}
		});
		ServletUtils.writeJsonToPage(ServletActionContext.getResponse(), sls);
		
		return null;
	}
	

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (riddle==null) {
			riddle = new Riddle();
		}
	}

	public Riddle getRiddle() {
		return riddle;
	}

	public void setRiddle(Riddle riddle) {
		this.riddle = riddle;
	}

	public File getDataExcel() {
		return dataExcel;
	}

	public void setDataExcel(File dataExcel) {
		this.dataExcel = dataExcel;
	}

	public String getDataExcelContentType() {
		return dataExcelContentType;
	}

	public void setDataExcelContentType(String dataExcelContentType) {
		this.dataExcelContentType = dataExcelContentType;
	}

	public String getDataExcelFileName() {
		return dataExcelFileName;
	}

	public void setDataExcelFileName(String dataExcelFileName) {
		this.dataExcelFileName = dataExcelFileName;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDeduction() {
		return deduction;
	}

	public void setDeduction(int deduction) {
		this.deduction = deduction;
	}

	public static int getMaxQuestionNo() {
		return MAX_QUESTION_NO;
	}

	public static int getFullScore() {
		return FULL_SCORE;
	}

	public static int getPerScore() {
		return PER_SCORE;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}



	
	
}
