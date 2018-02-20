
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Question extends DomainEntity {

	//Attributes

	private String	question;
	private String	answer;


	//Getters

	@NotBlank
	public String getQuestion() {
		return this.question;
	}

	@NotBlank
	public String getAnswer() {
		return this.answer;
	}

	//Setters

	public void setQuestion(final String question) {
		this.question = question;
	}

	public void setAnswer(final String answer) {
		this.answer = answer;
	}
}
