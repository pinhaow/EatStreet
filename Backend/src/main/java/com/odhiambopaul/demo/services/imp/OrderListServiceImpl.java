package com.odhiambopaul.demo.services.imp;

import com.odhiambopaul.demo.model.OrderList;
import com.odhiambopaul.demo.repositories.OrderListRepository;
import com.odhiambopaul.demo.services.OrderListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderListServiceImpl implements OrderListService {
    OrderListRepository orderlistRepository;

    public OrderListServiceImpl(OrderListRepository orderlistRepository) {
        this.orderlistRepository = orderlistRepository;
    }

    @Override
    public List<OrderList> getOrderLists(Long userId) {
        List<OrderList> orderlists = new ArrayList<>();
        if (userId > 0) {
            orderlistRepository.findAll().forEach(orderlist -> {
                if (orderlist.getUserid().equals(userId)) orderlists.add(orderlist);
            });
        }else
            orderlistRepository.findAll().forEach(orderlists::add);
        return orderlists;
    }

    @Override
    public OrderList getOrderListById(Long orderlistId) {
        return orderlistRepository.findById(orderlistId).get();
    }

    @Override
    public OrderList insert(OrderList orderlist) {
        return orderlistRepository.save(orderlist);
    }

    @Override
    public void updateOrderList(OrderList orderlist) {
        OrderList orderlistFromDb = orderlistRepository.findById(orderlist.getId()).get();
        System.out.println(orderlistFromDb.toString());
        orderlistFromDb.setDriverid(orderlist.getDriverid());
        orderlistFromDb.setDrivername(orderlist.getDrivername());
        orderlistFromDb.setStatus(orderlist.getStatus());
        orderlistRepository.save(orderlistFromDb);
    }

    @Override
    public void deleteOrderList(Long orderlistId) {
        orderlistRepository.deleteById(orderlistId);
    }
}
