package com.odhiambopaul.demo.repositories;

import com.odhiambopaul.demo.model.OrderList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository extends CrudRepository<OrderList, Long> {
}
