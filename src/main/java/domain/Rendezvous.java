
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

public class Rendezvous extends DomainEntity {

	//Attributes

	private String						name;
	private String						description;
	private Date						moment;
	private String						picture;
	private String						coordinates;
	private boolean						finalMode;
	private boolean						delete;
	private boolean						adultOnly;

	//Relationships

	private Collection<Question>		questions;
	private User						creator;
	private Collection<User>			attendants;
	private Collection<Rendezvous>		links;
	private Collection<Announcement>	announcements;
	private Collection<Comment>			comments;


	//Getters

	@NotBlank
	public String getName() {
		return this.name;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getMoment() {
		return this.moment;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}

	@Pattern(regexp = "^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$")
	public String getCoordinates() {
		return this.coordinates;
	}

	public boolean getFinalMode() {
		return this.finalMode;
	}

	public boolean getDelete() {
		return this.delete;
	}

	public boolean getAdultOnly() {
		return this.adultOnly;
	}

	@Valid
	@NotNull
	@ElementCollection
	public Collection<Question> getQuestions() {
		return this.questions;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getCreator() {
		return this.creator;
	}

	@Valid
	@NotNull
	@ManyToMany
	public Collection<User> getAttendants() {
		return this.attendants;
	}

	@Valid
	@NotNull
	@OneToMany
	public Collection<Rendezvous> getLinks() {
		return this.links;
	}

	@Valid
	@NotNull
	@OneToMany
	public Collection<Announcement> getAnnouncements() {
		return this.announcements;
	}

	@Valid
	@NotNull
	@OneToMany
	public Collection<Comment> getComments() {
		return this.comments;
	}

	//Setters

	public void setName(final String name) {
		this.name = name;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public void setCoordinates(final String coordinates) {
		this.coordinates = coordinates;
	}

	public void setFinalMode(final boolean finalMode) {
		this.finalMode = finalMode;
	}

	public void setDelete(final boolean delete) {
		this.delete = delete;
	}

	public void setAdultOnly(final boolean adultOnly) {
		this.adultOnly = adultOnly;
	}

	public void setQuestions(final Collection<Question> questions) {
		this.questions = questions;
	}

	public void setCreator(final User creator) {
		this.creator = creator;
	}

	public void setAttendants(final Collection<User> attendants) {
		this.attendants = attendants;
	}

	public void setLinks(final Collection<Rendezvous> links) {
		this.links = links;
	}

	public void setAnnouncements(final Collection<Announcement> announcements) {
		this.announcements = announcements;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}
}
