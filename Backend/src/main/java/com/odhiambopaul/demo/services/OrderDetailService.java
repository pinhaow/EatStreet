package com.odhiambopaul.demo.services;

import com.odhiambopaul.demo.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getOrderDetails(Long orderlistId);

    OrderDetail getOrderDetailById(Long orderdetailId);

    OrderDetail insert(OrderDetail orderdetail);

    void updateOrderDetail(OrderDetail orderdetail);

    void deleteOrderDetail(Long orderdetailId);
}
