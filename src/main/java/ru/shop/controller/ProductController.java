package ru.shop.controller;

import ru.shop.model.*;
import ru.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;
    private UserService userService;
    private CityService cityService;
    private OrderService orderService;
    private TypeService typeService;
    private SizeService sizeService;
    private ConditionService conditionService;

    @Autowired(required = true)
    @Qualifier(value = "productService")
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "cityService")
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired(required = true)
    @Qualifier(value = "orderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired(required = true)
    @Qualifier(value = "typeService")
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Autowired(required = true)
    @Qualifier(value = "sizeService")
    public void setSizeService(SizeService sizeService) {
        this.sizeService = sizeService;
    }

    @Autowired(required = true)
    @Qualifier(value = "conditionService")
    public void setConditionService(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public String listProducts(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("listProducts", this.productService.listProducts());
        model.addAttribute("userService", userService);
        return "products";
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product){
        if(product.getId() == 0) {
            this.productService.addProduct(product);
        } else {
            this.productService.updateProduct(product);
        }
        return "redirect:/products";
    }

    @RequestMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") int id){
        this.productService.removeProduct(id);
        return "redirect:/products";
    }

    @RequestMapping("edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("listProducts", this.productService.listProducts());
        return "books";
    }

    @RequestMapping("product/{id}")
    public String productData(@PathVariable("id") int id, Model model){
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("userService", userService);
        return "product";
    }

    @RequestMapping("seller/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listProductsByUser", this.productService.listProductsByUser(id));
        return "seller";
    }

    @RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
    public String buyProduct(@PathVariable("id") int id, Model model) {
        if (this.productService.getProductById(id).getSold()==0) {
            List<City> listCities = this.cityService.listCities();
            List<String> nameCities = new ArrayList<String>();
            for (City city:listCities) {
                nameCities.add(city.getName());}
            model.addAttribute("product", this.productService.getProductById(id));
            model.addAttribute("listCities", nameCities);
            model.addAttribute("orderForm", new Order());
        } else {
            model.addAttribute("errormess", "Извини, товар недоступен");
        }
        return "buy";
    }

    @RequestMapping(value = "buy/{id}", method = RequestMethod.POST)
    public String createOrder(@PathVariable("id")int id, @ModelAttribute("orderForm") Order orderForm, Model model) {
        orderForm.setPaid((byte) 0);
        orderForm.setProductId(id);
        orderService.addOrder(orderForm);
        String mess = "Ваш заказ оформлен, форма оплаты будет прислана на почту " + orderForm.getEmail();
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("mess", mess);
        model.addAttribute("orderMade", true);
        return "buy";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("productSearchCriteria", new ProductSearchCriteria());
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(@ModelAttribute("productSearchCriteria") ProductSearchCriteria productSearchCriteria, Model model) {
        List<Size> sizeList = this.sizeService.listSizes();
        List<String> nameSizes = new ArrayList<String>();
        for(Size size:sizeList){
            nameSizes.add(size.getSize());
        }
        List<Type> typeList = this.typeService.listTypes();
        List<String> nameTypes = new ArrayList<String>();
        for(Type type:typeList){
            nameTypes.add(type.getType());
        }
        List<Condition> conditionList = this.conditionService.listConditions();
        List<String> nameConditions = new ArrayList<String>();
        for(Condition condition:conditionList){
            nameConditions.add(condition.getCondition());
        }
        model.addAttribute("nameSizes", nameSizes);
        model.addAttribute("nameTypes", nameTypes);
        model.addAttribute("nameConditions", nameConditions);
        if (productSearchCriteria != null){
            List<Product> productList =  this.productService.findByCriteria(productSearchCriteria);
            if (productList != null) {
                model.addAttribute("listProducts", productList);
            } else {
                model.addAttribute("err", "Извините, товар не найден");
            }
        }
        return "search";
    }

}