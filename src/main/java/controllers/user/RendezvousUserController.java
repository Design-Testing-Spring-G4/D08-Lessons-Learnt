
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("rendezvous/user")
public class RendezvousUserController extends AbstractController {

	//Services

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService		actorService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Rendezvous> rendezvouses;

		rendezvouses = ((User) this.actorService.findByPrincipal()).getAttendance();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/user/list.do");

		return result;
	}

	//Creation

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.create();
		result = this.createEditModelAndView(rendezvous);

		return result;
	}

	//Edition

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int varId) {
		final ModelAndView result;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.findOne(varId);
		Assert.notNull(rendezvous);
		result = this.createEditModelAndView(rendezvous);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Rendezvous rendezvous, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(rendezvous);
		else
			try {
				this.rendezvousService.save(rendezvous);
				result = new ModelAndView("redirect:/rendezvous/user/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Rendezvous rendezvous, final BindingResult binding) {
		ModelAndView result;

		try {
			this.rendezvousService.delete(rendezvous);
			result = new ModelAndView("redirect:/rendezvous/user/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
		}
		return result;
	}

	//Cancellation

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int varId) {
		final ModelAndView result;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.findOne(varId);
		Assert.notNull(rendezvous);
		result = this.createCancelModelAndView(rendezvous);

		return result;
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST, params = "cancel")
	public ModelAndView saveCancel(@Valid final Rendezvous rendezvous, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createCancelModelAndView(rendezvous);
		else
			try {
				this.rendezvousService.cancel(rendezvous); // Need the method to remove a rendezvous of the attendance collection in the user (principal)
				result = new ModelAndView("redirect:/rendezvous/user/list.do");
			} catch (final Throwable oops) {
				result = this.createCancelModelAndView(rendezvous, "rendezvous.cancel.error");
			}
		return result;
	}

	//RSVP

	@RequestMapping(value = "/rsvp", method = RequestMethod.GET)
	public ModelAndView rsvp(@Valid final int varId) {
		ModelAndView result;
		Rendezvous r;

		r = this.rendezvousService.findOne(varId);
		this.rendezvousService.addRendezvous(r);
		result = new ModelAndView("redirect:/rendezvous/user/list.do");

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
		result.addObject("requestURI", "rendezvous/user/edit.do");

		return result;

	}

	protected ModelAndView createCancelModelAndView(final Rendezvous rendezvous) {
		ModelAndView result;

		result = this.createCancelModelAndView(rendezvous, null);

		return result;
	}

	protected ModelAndView createCancelModelAndView(final Rendezvous rendezvous, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("rendezvous/cancel");
		result.addObject("rendezvous", rendezvous);
		result.addObject("message", messageCode);
		result.addObject("requestURI", "rendezvous/user/cancel.do");

		return result;
	}
}
