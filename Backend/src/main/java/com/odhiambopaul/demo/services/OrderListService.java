package com.odhiambopaul.demo.services;

import com.odhiambopaul.demo.model.OrderList;

import java.util.List;

public interface OrderListService {
    List<OrderList> getOrderLists(Long userId);

    OrderList getOrderListById(Long orderlistId);

    OrderList insert(OrderList orderlist);

    void updateOrderList(OrderList orderlist);

    void deleteOrderList(Long orderlistId);
}
