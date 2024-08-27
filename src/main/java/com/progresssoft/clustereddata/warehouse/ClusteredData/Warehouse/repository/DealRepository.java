package com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.repository;

import com.progresssoft.clustereddata.warehouse.ClusteredData.Warehouse.model.entity.DealEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealRepository extends MongoRepository<DealEntity, String> {

    Optional<DealEntity> findByDealUniqueId(String dealUniqueId);
}
