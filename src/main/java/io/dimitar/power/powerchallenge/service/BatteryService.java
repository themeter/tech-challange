package io.dimitar.power.powerchallenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dimitar.power.powerchallenge.model.Battery;
import io.dimitar.power.powerchallenge.model.BatteryRange;
import io.dimitar.power.powerchallenge.model.BatteryRepository;

/**
 * 
 */
@Service
public class BatteryService {

	@Autowired
	BatteryRepository batteryRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public BatteryRange retriveBatteries(int start, int end) {

		Query query = entityManager.createNamedQuery("Battery.findWithinPostcodeRange");
		query.setParameter(1, start);
		query.setParameter(2, end);

		List<Battery> results = query.getResultList();
		List<String> names = results.stream().map(Battery::getName).sorted().toList();
		double totalCapacity = results.stream().mapToDouble(Battery::getCapacity).sum();
		double averageCapacity = results.stream().mapToDouble(Battery::getCapacity).average().orElse(0);

		return new BatteryRange(start, end, names, totalCapacity, averageCapacity);
	}

	public List<Battery> storeBatteries(List<Battery> batteries) {
		return StreamSupport.stream(batteryRepository.saveAll(batteries).spliterator(), false).toList();
	}

}
