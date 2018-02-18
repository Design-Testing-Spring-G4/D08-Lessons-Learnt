
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Question {

	//Attributes

	private String	question;


	//Getters

	@NotBlank
	public String getQuestion() {
		return this.question;
	}

	//Setters

	public void setQuestion(final String question) {
		this.question = question;
	}
}
