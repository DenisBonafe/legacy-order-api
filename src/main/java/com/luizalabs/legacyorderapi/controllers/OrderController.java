package com.luizalabs.legacyorderapi.controllers;

import com.luizalabs.legacyorderapi.entities.Order;
import com.luizalabs.legacyorderapi.responses.*;
import com.luizalabs.legacyorderapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<UsersResponse> listOrders() {
        try {
            List<Order> orders = service.getOrders();
            UsersResponse usersResponse = service.getUsersResponse(orders);
            return ResponseEntity.status(HttpStatus.OK).body(usersResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UsersResponse());
        }
    }

}
