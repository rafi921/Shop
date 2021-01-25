package pl.sklep.zadanie2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import pl.sklep.zadanie2.model.Product;
import pl.sklep.zadanie2.service.ShopService;

import java.util.List;

@Controller
@Profile({"Pro", "Plus", "Start"})
public class ShopController {
    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;

    }

    @EventListener(ApplicationReadyEvent.class)
    public void runApplication() {
        shopService.addProduct();
        List<Product> list = shopService.getList();
        list.forEach(System.out::println);
        shopService.calculateBill();
    }
}
