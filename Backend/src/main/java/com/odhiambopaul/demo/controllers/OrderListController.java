package com.odhiambopaul.demo.controllers;

import com.odhiambopaul.demo.model.OrderList;
import com.odhiambopaul.demo.model.User;
import com.odhiambopaul.demo.services.OrderListService;
import com.odhiambopaul.demo.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderlist")
public class OrderListController {
    OrderListService orderlistService;
    UserService userService;

    public OrderListController(OrderListService orderlistService, UserService userService) {
        this.orderlistService = orderlistService;
        this.userService = userService;
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<OrderList>> getAllOrderLists(@PathVariable Long userId) {
        List<OrderList> orderlists = orderlistService.getOrderLists(userId);
        return new ResponseEntity<>(orderlists, HttpStatus.OK);
    }

    @GetMapping({"/{orderlistId}"})
    public ResponseEntity<OrderList> getOrderList(@PathVariable Long orderlistId) {
        return new ResponseEntity<>(orderlistService.getOrderListById(orderlistId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderList> addOrderList(@RequestBody OrderList orderlist) {
        OrderList orderlist1 = orderlistService.insert(orderlist);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("orderlist", "/api/v1/orderlist/" + orderlist1.getId().toString());
        return new ResponseEntity<>(orderlist1, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping({"/take/{orderlistId}/{driverId}"})
    public ResponseEntity<OrderList> takeOrderList(@PathVariable Long orderlistId, @PathVariable Long driverId) {
        OrderList orderList = orderlistService.getOrderListById(orderlistId);
        User driver = userService.getUserById(driverId);
        orderList.setDrivercontact(driver.getMobilephone());
        orderList.setDrivername(driver.getName());
        orderList.setDriverid(driver.getId());
        orderList.setStatus("DELIVERING");
        orderlistService.updateOrderList(orderList);
        return new ResponseEntity<>(orderlistService.getOrderListById(orderlistId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OrderList> updateOrderList(@RequestBody OrderList orderlist) {
        orderlistService.updateOrderList(orderlist);
        return new ResponseEntity<>(orderlistService.getOrderListById(orderlist.getId()), HttpStatus.OK);
    }

    @DeleteMapping({"/{orderlistId}"})
    public ResponseEntity<OrderList> deleteTodo(@PathVariable("orderlistId") Long orderlistId) {
        OrderList orderlist = orderlistService.getOrderListById(orderlistId);
        orderlistService.deleteOrderList(orderlistId);
        return new ResponseEntity<>(orderlist, HttpStatus.OK);
    }
}
