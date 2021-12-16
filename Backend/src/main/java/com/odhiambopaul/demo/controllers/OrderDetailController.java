package com.odhiambopaul.demo.controllers;

import com.odhiambopaul.demo.model.OrderDetail;
import com.odhiambopaul.demo.model.Todo;
import com.odhiambopaul.demo.services.OrderDetailService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderdetail")
public class OrderDetailController {
    OrderDetailService orderdetailService;

    public OrderDetailController(OrderDetailService orderdetailService) {
        this.orderdetailService = orderdetailService;
    }

    @GetMapping("/all/{orderlistId}")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails(@PathVariable Long orderlistId) {
        List<OrderDetail> orderdetails = orderdetailService.getOrderDetails(orderlistId);
        return new ResponseEntity<>(orderdetails, HttpStatus.OK);
    }

    @GetMapping({"/{orderdetailId}"})
    public ResponseEntity<OrderDetail> getOrderDetail(@PathVariable Long orderdetailId) {
        return new ResponseEntity<>(orderdetailService.getOrderDetailById(orderdetailId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDetail> addOrderDetail(@RequestBody OrderDetail orderdetail) {
        OrderDetail orderdetail1 = orderdetailService.insert(orderdetail);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("orderdetail", "/api/v1/orderdetail/" + orderdetail1.getId().toString());
        return new ResponseEntity<>(orderdetail1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<OrderDetail> updateOrderDetail(@RequestBody OrderDetail orderdetail) {
        orderdetailService.updateOrderDetail(orderdetail);
        return new ResponseEntity<>(orderdetailService.getOrderDetailById(orderdetail.getId()), HttpStatus.OK);
    }

    @DeleteMapping({"/{orderdetailId}"})
    public ResponseEntity<Todo> deleteTodo(@PathVariable("orderdetailId") Long orderdetailId) {
        orderdetailService.deleteOrderDetail(orderdetailId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
