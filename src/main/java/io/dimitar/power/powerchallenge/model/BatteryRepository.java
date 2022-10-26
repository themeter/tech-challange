package io.dimitar.power.powerchallenge.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Battery Repository
 */
@Repository
public interface BatteryRepository extends CrudRepository<Battery, Long> {

}
