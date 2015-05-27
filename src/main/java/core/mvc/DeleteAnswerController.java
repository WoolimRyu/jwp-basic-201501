package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.DaoFactory;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class DeleteAnswerController extends AbstractController {
	private static final Logger logger = LoggerFactory
			.getLogger(AnswerController.class);

	private AnswerDao answerDao = DaoFactory.getAnswerDao();
	private QuestionDao questionDao = DaoFactory.getQuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		long answerId = ServletRequestUtils.getRequiredLongParameter(request,
				"answerId");
		long questionId = ServletRequestUtils.getRequiredLongParameter(request,
				"questionId");

		answerDao.delete(answerId);
		questionDao.subtractAnswerCount(questionId);

		ModelAndView mav = jsonView();
		return mav;
	}
}
