package io.dimitar.power.powerchallenge.model;

import java.util.List;

/**
 * Response entity for batteries within a given postcode range.
 *
 */
public class BatteryRange {

    int startPostcode;
    int endPostcode;
    List<String> names;
    double totalCapacity;
    double averageCapacity;

    public BatteryRange(
            int startPostcode,
            int endPostcode,
            List<String> names,
            double totalCapacity,
            double averageCapacity) {

        this.startPostcode = startPostcode;
        this.endPostcode = endPostcode;
        this.names = names;
        this.totalCapacity = totalCapacity;
        this.averageCapacity = averageCapacity;
    }

    public int getStartPostcode() {
        return startPostcode;
    }

    public void setStartPostcode(int startPostcode) {
        this.startPostcode = startPostcode;
    }

    public int getEndPostcode() {
        return endPostcode;
    }

    public void setEndPostcode(int endPostcode) {
        this.endPostcode = endPostcode;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public double getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(double totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public double getAverageCapacity() {
        return averageCapacity;
    }

    public void setAverageCapacity(double averageCapacity) {
        this.averageCapacity = averageCapacity;
    }

}
