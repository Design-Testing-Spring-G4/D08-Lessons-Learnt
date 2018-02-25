
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import repositories.CommentRepository;
import repositories.QuestionRepository;
import repositories.RendezvousRepository;
import domain.Announcement;
import domain.Comment;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class RendezvousService {

	@Autowired
	private RendezvousRepository	rendezvousRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AnnouncementRepository	announcementRepository;

	@Autowired
	private CommentRepository		commentRepository;

	@Autowired
	private QuestionRepository		questionRepository;


	public Rendezvous create() {
		final Rendezvous r = new Rendezvous();
		final User u = (User) this.actorService.findByPrincipal();

		r.setCreator(u);
		r.setAttendants(new ArrayList<User>());
		r.setLinks(new ArrayList<Rendezvous>());
		r.setAnnouncements(new ArrayList<Announcement>());
		r.setComments(new ArrayList<Comment>());
		r.setQuestions(new ArrayList<Question>());
		return r;

	}

	public Rendezvous findOne(final int id) {
		Assert.notNull(id);

		return this.rendezvousRepository.findOne(id);
	}

	public Collection<Rendezvous> findAll() {
		return this.rendezvousRepository.findAll();
	}

	public Rendezvous save(final Rendezvous r) {
		Assert.notNull(r);
		Assert.isTrue(this.actorService.findByPrincipal() == r.getCreator());
		final Rendezvous saved = this.rendezvousRepository.save(r);
		return saved;
	}
	public void delete(final Rendezvous r) {
		Assert.notNull(r);
		Assert.isTrue(r.getFinalMode() == false);
		Assert.isTrue(this.actorService.findByPrincipal() == r.getCreator());
		if (!(r.getAnnouncements()).isEmpty())
			for (final Announcement a : r.getAnnouncements())
				this.announcementRepository.delete(a);
		if (!(r.getComments()).isEmpty())
			for (final Comment c : r.getComments())
				this.commentRepository.delete(c);
		if (!(r.getQuestions()).isEmpty())
			for (final Question q : r.getQuestions())
				this.questionRepository.delete(q);
		for (final Rendezvous x : this.rendezvousRepository.findAll())
			if (x.getLinks().contains(r))
				x.getLinks().remove(r);
		this.rendezvousRepository.delete(r);
	}
}
