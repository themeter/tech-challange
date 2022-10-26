package io.dimitar.power.powerchallenge.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Battery Repository for CRUD operations
 */
@Repository
public interface BatteryRepository extends CrudRepository<Battery, Long> {

}
