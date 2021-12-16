package com.odhiambopaul.demo.repositories;

import com.odhiambopaul.demo.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends CrudRepository<Store, Long> {
}
