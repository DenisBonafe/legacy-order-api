package com.luizalabs.legacyorderapi.services;

import com.luizalabs.legacyorderapi.entities.Order;
import com.luizalabs.legacyorderapi.repositories.OrderRepository;
import com.luizalabs.legacyorderapi.responses.UserResponse;
import com.luizalabs.legacyorderapi.responses.UsersResponse;
import com.luizalabs.legacyorderapi.responses.OrderResponse;
import com.luizalabs.legacyorderapi.responses.ProductResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public UsersResponse getUsersResponse(List<Order> orders) {
        List<UserResponse> userResponses = orders.stream()
                .collect(Collectors.groupingBy(Order::getUserId))
                .entrySet().stream()
                .map(entry -> {
                    List<OrderResponse> orderResponseList = entry.getValue().stream()
                            .collect(Collectors.groupingBy(Order::getOrderId))
                            .entrySet().stream()
                            .map(orderEntry -> {
                                List<ProductResponse> products = orderEntry.getValue().stream()
                                        .map(order -> new ProductResponse(order.getProductId(), String.valueOf(order.getProductValue())))
                                        .collect(Collectors.toList());
                                double total = orderEntry.getValue().stream()
                                        .mapToDouble(Order::getProductValue)
                                        .sum();
                                return new OrderResponse(orderEntry.getKey(), String.valueOf(total), orderEntry.getValue().get(0).getOrderDate().toString(), products);
                            })
                            .collect(Collectors.toList());
                    return new UserResponse(entry.getKey(), entry.getValue().get(0).getUserName(), orderResponseList);
                }).collect(Collectors.toList());

        return new UsersResponse(userResponses, userResponses.size());
    }

    public List<Order> getOrders() {
        List<Order> orders = repository.findAll();
        return orders;
    }
}
