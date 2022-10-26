package io.dimitar.power.powerchallenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.dimitar.power.powerchallenge.model.Battery;
import io.dimitar.power.powerchallenge.model.BatteryRange;
import io.dimitar.power.powerchallenge.model.BatteryRepository;
import io.dimitar.power.powerchallenge.util.ValidationUtil;

/**
 *
 */
@Service
public class BatteryService {
    private static Logger logger = Logger.getLogger(BatteryService.class.getName());

    @Autowired
    BatteryRepository batteryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public BatteryRange retriveBatteries(int start, int end) {
        ValidationUtil.validateRange(start, end);

        Query query = entityManager.createNamedQuery("Battery.findWithinPostcodeRange");
        query.setParameter(1, start);
        query.setParameter(2, end);

        List<Battery> results = query.getResultList();
        List<String> names = results.stream().map(Battery::getName).sorted().toList();
        double totalCapacity = results.stream().mapToDouble(Battery::getCapacity).sum();
        double averageCapacity = results.stream().mapToDouble(Battery::getCapacity).average().orElse(0);

        return new BatteryRange(start, end, names, totalCapacity, averageCapacity);
    }

    @Transactional
    public List<Battery> storeBatteries(List<Battery> batteries) {
        List<Battery> existing = new ArrayList<>();

        Query query = entityManager.createNamedQuery("Battery.findByNameAndPostcode");
        for (ListIterator<Battery> iter = batteries.listIterator(); iter.hasNext();) {
            Battery battery = iter.next();

            ValidationUtil.validatePostcode(battery.getPostcode());
            ValidationUtil.validateCapacity(battery.getCapacity());

            query.setParameter(1, battery.getName());
            query.setParameter(2, battery.getPostcode());

            List<Battery> results = query.getResultList();

            if (!results.isEmpty()) {
                iter.remove();
                existing.add(results.get(0));
            }
        }

        if (!existing.isEmpty()) {
            logger.warning("The following batteries are already persisted: " + existing);
        }

        if (!batteries.isEmpty()) {
            return StreamSupport.stream(batteryRepository.saveAll(batteries).spliterator(), false).toList();
        } else {
            logger.warning("No batteries will be persisted");
            return batteries;
        }
    }

}
