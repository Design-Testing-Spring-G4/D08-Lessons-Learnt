
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("comment/administrator")
public class CommentAdministratorController extends AbstractController {

	//Services

	@Autowired
	private CommentService	commentService;


	//Creation

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int varId) {
		ModelAndView result;
		Comment c;
		c = this.commentService.findOne(varId);
		Assert.notNull(c);

		try {
			this.commentService.delete(c);
			result = new ModelAndView("redirect:/comments/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(c, "comment.commit.error");
		}
		return result;
	}
	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "comment/administrator/edit.do");

		return result;

	}
}
