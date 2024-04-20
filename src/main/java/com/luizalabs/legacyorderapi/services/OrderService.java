package com.luizalabs.legacyorderapi.services;

import com.luizalabs.legacyorderapi.entities.Order;
import com.luizalabs.legacyorderapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> processFile(MultipartFile file) throws IOException {
        List<Order> orders = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Order order = new Order();
            order.setUserId(Integer.parseInt(line.substring(0, 10).trim()));
            order.setUserName(line.substring(10, 55).trim());
            order.setOrderId(Integer.parseInt(line.substring(55, 65).trim()));
            order.setProductId(Integer.parseInt(line.substring(65, 75).trim()));
            order.setProductValue(Double.parseDouble(line.substring(75, 87).trim()));
            order.setOrderDate(LocalDate.of(
                    Integer.parseInt(line.substring(87, 91).trim()),
                    Integer.parseInt(line.substring(91, 93).trim()),
                    Integer.parseInt(line.substring(93, 95).trim())));
            orders.add(order);
        }

        return orders;
    }

    public List<Order> saveOrders(List<Order> orders) {
        return repository.saveAll(orders);
    }

}
