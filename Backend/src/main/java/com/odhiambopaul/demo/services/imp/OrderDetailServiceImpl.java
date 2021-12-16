package com.odhiambopaul.demo.services.imp;

import com.odhiambopaul.demo.model.OrderDetail;
import com.odhiambopaul.demo.repositories.OrderDetailRepository;
import com.odhiambopaul.demo.services.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailRepository orderdetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderdetailRepository) {
        this.orderdetailRepository = orderdetailRepository;
    }

    @Override
    public List<OrderDetail> getOrderDetails(Long orderlistId) {
        List<OrderDetail> orderdetails = new ArrayList<>();
        orderdetailRepository.findAll().forEach(orderdetail -> {
            if (orderdetail.getOrderlistid().equals(orderlistId)) orderdetails.add(orderdetail);
        });
        return orderdetails;
    }

    @Override
    public OrderDetail getOrderDetailById(Long orderdetailId) {
        return orderdetailRepository.findById(orderdetailId).get();
    }

    @Override
    public OrderDetail insert(OrderDetail orderdetail) {
        return orderdetailRepository.save(orderdetail);
    }

    @Override
    public void updateOrderDetail(OrderDetail orderdetail) {
        OrderDetail orderdetailFromDb = orderdetailRepository.findById(orderdetail.getId()).get();
        System.out.println(orderdetailFromDb.toString());
        orderdetailFromDb.setOrderlistid(orderdetail.getOrderlistid());
        orderdetailFromDb.setQuantity(orderdetail.getQuantity());
        orderdetailFromDb.setItemname(orderdetail.getItemname());
        orderdetailFromDb.setItemprice(orderdetail.getItemprice());
        orderdetailRepository.save(orderdetailFromDb);
    }

    @Override
    public void deleteOrderDetail(Long orderdetailId) {
        orderdetailRepository.deleteById(orderdetailId);
    }
}
