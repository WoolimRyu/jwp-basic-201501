package core.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.controller.ShowController;
import next.dao.AnswerDao;
import next.dao.DaoFactory;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.utils.ServletRequestUtils;

public class EditController extends AbstractController {
	private static final Logger logger = LoggerFactory
			.getLogger(ShowController.class);

	private QuestionDao questionDao = new QuestionDao();
	private AnswerDao answerDao = new AnswerDao();
	private Question question;

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		question = questionDao.findById(questionId);
		
		ModelAndView mav = jstlView("modifyForm.jsp");
		mav.addObject("question", question);
		return mav;
	}
}