package ru.shop;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.shop.model.User;
import ru.shop.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
        "file:src/main/webapp/WEB-INF/security-context.xml"})
public class UserTest {

    @Autowired(required = true)
    @Qualifier(value = "userService")
    private UserService userService;

    User user = new User();
    @Before
    public void init() {
        user.setName("Test");
        user.setTele("+1111111");
        user.setCity("Владимир");
        user.setUsername("TestUsername");
        user.setPassword("123");
    }

    @Test
    public void crud() {
        // create and read
        userService.saveUser(user);
        User user1 = (User) userService.loadUserByUsername("TestUsername");
        Assert.assertEquals("Test", user1.getName());
        Assert.assertEquals("+1111111", user1.getTele());
        Assert.assertEquals("Владимир", user1.getCity());
        Assert.assertNotEquals("123", user1.getPassword());

        //update and read
        user.setName("Test1");
        user.setTele("+2222222");
        user.setCity("Москва");
        user.setUsername("TestUsername1");
        user.setPassword("1234");
        userService.updateUser(user);
        user1 = (User) userService.loadUserByUsername("TestUsername1");
        Assert.assertEquals("Test1", user1.getName());
        Assert.assertEquals("+2222222", user1.getTele());
        Assert.assertEquals("Москва", user1.getCity());
        Assert.assertNotEquals("1234", user1.getPassword());

        //delete and read
        Assert.assertEquals(true, userService.deleteUser(user1.getId()));
        Assert.assertNull(userService.getUserById(user1.getId()));
    }
}
