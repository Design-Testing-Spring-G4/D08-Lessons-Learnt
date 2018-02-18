
package domain;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

public class Announcement extends DomainEntity {

	//Attributes

	private Date	moment;
	private String	title;
	private String	description;

	//Relationships

	private User	user;


	//Getters

	@Past
	public Date getMoment() {
		return this.moment;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	//Setters

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setUser(final User user) {
		this.user = user;
	}
}
