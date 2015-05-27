package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;
import next.dao.DaoFactory;

public class UpdateController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(SaveController.class);
	private QuestionDao questionDao = DaoFactory.getQuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		String writer = ServletRequestUtils.getRequiredStringParameter(req, "writer");
		String title = ServletRequestUtils.getRequiredStringParameter(req, "title");
		String contents = ServletRequestUtils.getRequiredStringParameter(req, "contents");
		
		int questionId = Integer.parseInt(req.getParameter("questionId"));
		questionDao.update(new Question(writer, title, contents), questionId);
		
		return jstlView("redirect:/list.next");
	}
}