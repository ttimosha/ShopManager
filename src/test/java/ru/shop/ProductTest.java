package ru.shop;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.shop.model.Product;
import ru.shop.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml",
                                    "file:src/main/webapp/WEB-INF/security-context.xml"})
public class ProductTest {

    @Autowired(required = true)
    @Qualifier(value = "productService")
    private ProductService productService;

    private Product product = new Product();
    int id = 2147483647;

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
    }
    @Test
    public void crud() {
        // create and read
        productService.addProduct(product);
        Product product1 = productService.getProductById(id);
        Assert.assertEquals("Test", product1.getBrand());
        Assert.assertEquals("Джинсы", product1.getTypeOfProduct());
        Assert.assertEquals(1, product1.getPrice());
        Assert.assertEquals("M", product1.getSize());
        Assert.assertEquals(1, product1.getSeller());
        Assert.assertEquals("Хорошее", product1.getCondition());

        //update and read
        product.setBrand("Test1");
        product.setTypeOfProduct("Кофта");
        product.setPrice(11);
        product.setSize("S");
        product.setCondition("Отличное");
        productService.updateProduct(product);

        product1 = productService.getProductById(id);
        Assert.assertEquals("Test1", product1.getBrand());
        Assert.assertEquals("Кофта", product1.getTypeOfProduct());
        Assert.assertEquals(11, product1.getPrice());
        Assert.assertEquals("S", product1.getSize());
        Assert.assertEquals("Отличное", product1.getCondition());

        //delete and read
        productService.removeProduct(id);
        Assert.assertNull(productService.getProductById(id));
    }

}
