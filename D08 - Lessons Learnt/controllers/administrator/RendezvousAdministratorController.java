
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import domain.Rendezvous;

@Controller
@RequestMapping("rendezvous/administrator")
public class RendezvousAdministratorController extends AbstractController {

	//Services

	@Autowired
	private RendezvousService	rendezvousService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Rendezvous> rendezvouses;

		rendezvouses = this.rendezvousService.findAll();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/administrator/list.do");

		return result;
	}
	
	RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Rendezvouz rendezvous, final BindingResult binding) {
		ModelAndView result;

		try {
			this.rendezvousService.delete(rendezvous);
			result = new ModelAndView("redirect:/rendezvous/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
		}
		return result;
	}

	//Ancillary methods

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous) {
		ModelAndView result;

		result = this.createEditModelAndView(rendezvous, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("rendezvous/edit");
		result.addObject("rendezvous", rendezvous);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "rendezvous/administrator/edit.do");

		return result;

	}
}
