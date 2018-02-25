
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class UserServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private UserService	userService;


	//Tests

	@Test
	public void testCreateUser() {
		//Using create() to initialise a new entity.
		final User user = this.userService.create();

		user.setName("testName");
		user.setSurname("testSurname");
		user.setPhone("999888777");
		user.setAddress("c/test, 1");
		user.setEmail("test@mail.com");
		user.getUserAccount().setUsername("Fren");
		user.getUserAccount().setPassword("12345678");

		final User saved = this.userService.save(user);
		final User bbdd = this.userService.findOne(saved.getId());
		Assert.notNull(bbdd);
	}

}
