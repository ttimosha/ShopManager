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
        try
        {
            model.addAttribute("listProducts", this.productService.listProducts());
            model.addAttribute("userService", userService);
        } catch (Exception e){ }
        return "products";
    }

    @RequestMapping(value = "/account/{username}")
    public String accountPage (@PathVariable("username") String username, Model model) {
        try {
            User user = (User) this.userService.loadUserByUsername(username);
            model.addAttribute("user", user);
            model.addAttribute("listProductsByUser", this.productService.listProductsByUser(user.getId()));
        } catch (Exception e) { }
        return "account";
    }

    @RequestMapping(value = "/account/{username}/add/{user_id}", method = RequestMethod.GET)
    public String showAdd(@ModelAttribute("user_id") int user_id,@ModelAttribute("username") String username, Model model) {
        model.addAttribute("added", false);
        try {
            model.addAttribute("product", new Product());
        } catch (Exception e) { }
        return "add";
    }

    @RequestMapping(value = "/account/{username}/add/{user_id}", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") Product product, @PathVariable("user_id") int id, Model model){
        try {
                product.setSeller(id);
                product.setSold((byte) 0);
                this.productService.addProduct(product);
                model.addAttribute("added", true);
                model.addAttribute("mess", "Товар успешно добавлен");
        } catch (Exception e) { model.addAttribute("mess", "Произошла ошибка"); }
        return "add";
    }

    @RequestMapping(value = "/account/{username}/edit/{product_id}", method = RequestMethod.GET)
    public String showEdit(@PathVariable("product_id") int product_id,@ModelAttribute("username") String username, Model model){
        try {
            model.addAttribute("product", this.productService.getProductById(product_id));
        } catch (Exception e) { }
        return "edit";
    }

    @RequestMapping(value = "/account/{username}/edit", method = RequestMethod.POST)
    public String editProduct(@ModelAttribute("product") Product product, Model model){
        try {
            this.productService.updateProduct(product);
            model.addAttribute("mess", "Товар успешно отредактирован");
        } catch (Exception e) { model.addAttribute("mess", "Произошла ошибка"); }
        return "edit";
    }

    @RequestMapping("/remove/{product_id}")
    public String removeProduct(@PathVariable("product_id") int id){
        try {
            this.productService.removeProduct(id);
        } catch (Exception e) { }
        return "redirect:/products";
    }

    @RequestMapping("/account/{username}/orders/{user_id}")
    public String showOrders(@PathVariable("user_id") int user_id, @ModelAttribute("username") String username, Model model){
        try {
            model.addAttribute("username", username);
            List<Order> orderList = this.orderService.listOrdersByUser(user_id);
            model.addAttribute("orderList", orderList);
        } catch (Exception e) { }
        return "orders";
    }

    @RequestMapping("product/{id}")
    public String productData(@PathVariable("id") int id, Model model){
        try {
            Product product = this.productService.getProductById(id);
            model.addAttribute("product", product);
            model.addAttribute("user", this.userService.getUserById(product.getSeller()));
        } catch (Exception e){ }
        return "product";
    }

    @RequestMapping("seller/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        try {
            model.addAttribute("user", this.userService.getUserById(id));
            model.addAttribute("listProductsByUser", this.productService.listProductsByUser(id));
        } catch (Exception e) { }
        return "seller";
    }

    @RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
    public String buyProduct(@PathVariable("id") int id, Model model) {
        try {
                if (this.productService.getProductById(id).getSold()==0) {
                    Product product = this.productService.getProductById(id);
                    model.addAttribute("product", product);
                    model.addAttribute("user", this.userService.getUserById(product.getSeller()));
                    model.addAttribute("orderForm", new Order());
            } else {
                model.addAttribute("errormess", "Извини, товар недоступен");
            }
        } catch (Exception e) { }
        return "buy";
    }

    @RequestMapping(value = "buy/{id}", method = RequestMethod.POST)
    public String createOrder(@PathVariable("id")int id, @ModelAttribute("orderForm") Order orderForm, @ModelAttribute("product") Product product, Model model) {
        try {
            orderForm.setPaid((byte) 0);
            orderForm.setProductId(id);
            orderForm.setSeller_id(this.productService.getProductById(id).getSeller());
            orderService.addOrder(orderForm);
            String mess = "Ваш заказ оформлен, форма оплаты будет прислана на почту " + orderForm.getEmail();
            model.addAttribute("mess", mess);
            model.addAttribute("orderMade", true);
        }
        catch (Exception e) {
            String mess = "Извините, произошла ошибка";
            model.addAttribute("mess", mess);
        }
        return "buy";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        try {
            model.addAttribute("productSearchCriteria", new ProductSearchCriteria());
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
            List<City> listCities = this.cityService.listCities();
            List<String> nameCities = new ArrayList<String>();
            for (City city:listCities) {
                nameCities.add(city.getName());}
            model.addAttribute("listCities", nameCities);
            model.addAttribute("nameSizes", nameSizes);
            model.addAttribute("nameTypes", nameTypes);
            model.addAttribute("nameConditions", nameConditions);
        } catch (Exception e) { }
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(@ModelAttribute("productSearchCriteria") ProductSearchCriteria productSearchCriteria, Model model) {
        try {
            model.addAttribute("userService", userService);
                if (productSearchCriteria != null){
                    List<Product> productList = null;
                    try {
                        productList =  this.productService.findByCriteria(productSearchCriteria);
                    } catch (Exception e) { }
                    if (productList != null) {
                        model.addAttribute("listProducts", productList);
                    } else {
                        model.addAttribute("err", "Извините, товар не найден");
                    }
                }
            } catch (Exception e) { }
        return "search";
    }



}