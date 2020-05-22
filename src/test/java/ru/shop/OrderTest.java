package ru.shop;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.service.OrderService;
import ru.shop.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
        "file:src/main/webapp/WEB-INF/security-context.xml"})
public class OrderTest {

    @Autowired(required = true)
    @Qualifier(value = "orderService")
    private OrderService orderService;

    @Autowired(required = true)
    @Qualifier(value = "productService")
    private ProductService productService;

    Product product = new Product();
    private Order order = new Order();
    int id = 2147483646;

    @Before
    public void init() {
        product.setBrand("Test");
        product.setTypeOfProduct("Джинсы");
        product.setPrice(1);
        product.setSize("M");
        product.setSeller(1);
        product.setSold((byte) 0);
        product.setCondition("Хорошее");
        product.setId(id);
        productService.addProduct(product);

        order.setProductId(product.getId());
        order.setStreet("Test");
        order.setHouse(1);
        order.setApartment(1);
        order.setCity("Владимир");
        order.setEmail("123@mail.ru");
        order.setSeller_id(product.getSeller());
        order.setId(id);
    }
    @Test
    public void crud() {
        // create and read
        orderService.addOrder(order);
        Order order1 = orderService.getOrderById(id);
        Assert.assertEquals("Test", order1.getStreet());
        Assert.assertEquals(1, order1.getHouse());
        Assert.assertEquals(1, order1.getApartment());
        Assert.assertEquals("Владимир", order1.getCity());
        Assert.assertEquals("123@mail.ru", order1.getEmail());
        Assert.assertEquals(product.getId(), order1.getProductId());
        Assert.assertEquals(product.getSeller(), order1.getSeller_id());

        //update and read
        order.setStreet("Test1");
        order.setHouse(11);
        order.setApartment(11);
        order.setCity("Москва");
        order.setEmail("321@mail.ru");
        orderService.updateOrder(order);

        order1 = orderService.getOrderById(id);
        Assert.assertEquals("Test1", order1.getStreet());
        Assert.assertEquals(11, order1.getHouse());
        Assert.assertEquals(11, order1.getApartment());
        Assert.assertEquals("Москва", order1.getCity());
        Assert.assertEquals("321@mail.ru", order1.getEmail());

        //delete and read
        orderService.removeOrder(id);
        Assert.assertNull(orderService.getOrderById(id));
        productService.removeProduct(id);
    }
}
