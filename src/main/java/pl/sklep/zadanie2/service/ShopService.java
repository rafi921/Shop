package pl.sklep.zadanie2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.sklep.zadanie2.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ShopService {
    private List<Product> list;
    private double minPrice;
    private double maxPrice;
    @Value("${shop-service.vat}")
    private int vat;
    @Value("${shop-service.discount}")
    private int discount;

    public ShopService(List<Product> list) {
        this.list = list;
    }

    public ShopService() {
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }


    private double randomPrice(double min, double max) {
        setMinPrice(min);
        setMaxPrice(max);

        return minPrice + new Random().nextDouble() * (maxPrice - minPrice);


    }

    public void calculateBill() {
        double sum = 0;
        double sumVat;
        double discountPrice;
        double result;

        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getPrice();
        }
        if (vat > 0) {
            sumVat = ((sum * vat) / 100.00) + sum;

            System.out.println("Kwota z podatkiem wynosi:" + sumVat);
            if (discount > 0) {
                discountPrice = (sumVat * discount) / 100.00;
                result = sumVat - discountPrice;

                System.out.println("Kwota po rabacie z podatkiem wynosi:" + result);
            }
        }

        System.out.println("Cena wszystkich produktów bez bez podatku wynosi:" + sum);

    }

    public void addProduct() {
        list = new ArrayList<>();
        list.add(new Product("Laptop", randomPrice(50, 300)));
        list.add(new Product("Karta graficzna", randomPrice(50, 300)));
        list.add(new Product("Pralka", randomPrice(50, 300)));
        list.add(new Product("Telewizor", randomPrice(50, 300)));
        list.add(new Product("Lodówka", randomPrice(50, 300)));

        setList(list);

    }
}
