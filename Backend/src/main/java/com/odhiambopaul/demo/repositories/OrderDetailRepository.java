package com.odhiambopaul.demo.repositories;

import com.odhiambopaul.demo.model.Item;
import com.odhiambopaul.demo.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
}
