
package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("comment/user")
public class CommentUserController extends AbstractController {

	//Services

	@Autowired
	private CommentService	commentService;

	@Autowired
	private ActorService	actorService;


	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Comment comment;

		comment = this.commentService.create();
		result = this.createEditModelAndView(comment);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Comment comment, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(comment);
		else
			try {
				this.commentService.save(comment);
				result = new ModelAndView("redirect:/comment/user/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(comment, "comment.commit.error");
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
		result.addObject("requestURI", "comment/user/edit.do");

		return result;

	}
}
