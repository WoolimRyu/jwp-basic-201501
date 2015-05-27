package core.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import next.dao.AnswerDao;
import next.dao.DaoFactory;
import next.dao.QuestionDao;
import next.model.Question;

public class ApiDeleteController extends AbstractController {
	private QuestionDao questionDao = DaoFactory.getQuestionDao();
	private AnswerDao answerDao = DaoFactory.getAnswerDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int questionId = Integer.parseInt(request.getParameter("questionId"));

		if (questionDao.findById(questionId).getCountOfComment() == 0) {
			questionDao.delete(new Question(), questionId);
		} else {
			String a = answerDao.findWriter(questionId).getWriter();
			String b = questionDao.findById(questionId).getWriter();

			if (a == b) {
				questionDao.delete(new Question(), questionId);
			} else {
				ModelAndView mav = jstlView("/error.jsp");
				return mav;
			}
		}

		return jstlView("redirect:/list.next");
	}
}