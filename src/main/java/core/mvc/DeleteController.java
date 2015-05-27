package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.DaoFactory;

public class DeleteController extends AbstractController {
	private QuestionDao questionDao = DaoFactory.getQuestionDao();
	private AnswerDao answerDao = DaoFactory.getAnswerDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		int questionId = Integer.parseInt(req.getParameter("questionId"));
		
		if (questionDao.findById(questionId).getCountOfComment() == 0){
			questionDao.delete(new Question(), questionId);
		}  else {
			String a = answerDao.findWriter(questionId).getWriter();
			String b = questionDao.findById(questionId).getWriter();
			
			if (a == b){
				questionDao.delete(new Question(), questionId);
			}
		};

		return jstlView("redirect:/list.next");
	}
}