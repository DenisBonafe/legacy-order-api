package com.luizalabs.legacyorderapi.controllers;

import com.luizalabs.legacyorderapi.entities.Order;
import com.luizalabs.legacyorderapi.responses.*;
import com.luizalabs.legacyorderapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static com.luizalabs.legacyorderapi.utils.Constants.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            List<Order> orders = service.processFile(file);
            int size = service.saveOrders(orders).size();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UploadResponse(UPLOAD_MESSAGE_SUCCESS, size));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UploadResponse(UPLOAD_MESSAGE_FAILURE, 0));
        }
    }

    @GetMapping()
    public ResponseEntity<UsersResponse> listOrders(
        @RequestParam(required = false) Integer orderId,
        @RequestParam(required = false) String startDate,
        @RequestParam(required = false) String endDate) {
        List<Order> orders = getFilteredOrders(orderId, startDate, endDate);
        
        if (orders == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UsersResponse());
        }
        
        return extracted(orders);
    }

    protected List<Order> getFilteredOrders(Integer orderId, String startDate, String endDate) {
        if (orderId == null && startDate == null && endDate == null) {
            return service.getAllOrders();
        } else if (orderId == null && startDate != null && endDate != null) {
            return service.getOrdersFilteredByOrderDateBetween(ToLocalDate(startDate), ToLocalDate(endDate));
        } else if (orderId != null && startDate == null && endDate == null) {
            return service.getOrdersFilteredByOrderId(orderId);
        } else if (orderId != null && startDate != null && endDate != null) {
            return service.getOrdersBetweenDateFilteredById(orderId, ToLocalDate(startDate), ToLocalDate(endDate));
        }
        return null;
    }

    private LocalDate ToLocalDate(String date) {
        return LocalDate.of(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(9, 10)));
    }

    protected ResponseEntity<UsersResponse> extracted(List<Order> orders) {
        try {
            UsersResponse usersResponse = service.getUsersResponse(orders);
            return ResponseEntity.status(HttpStatus.OK).body(usersResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UsersResponse());
        }
    }

}
