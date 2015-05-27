package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.DaoFactory;
import next.dao.QuestionDao;
import next.model.Answer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AnswerController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);
	
	private AnswerDao answerDao = DaoFactory.getAnswerDao();
	private QuestionDao questionDao =  DaoFactory.getQuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		String writer =  ServletRequestUtils.getRequiredStringParameter(request, "writer");
		String contents =  ServletRequestUtils.getRequiredStringParameter(request, "contents");
		
		answerDao.insert(new Answer(writer, contents, questionId));
		questionDao.addAnswerCount(questionId);
		
		ModelAndView mav = jsonView();
		return mav;
	}
}