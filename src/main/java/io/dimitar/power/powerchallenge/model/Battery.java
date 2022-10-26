package io.dimitar.power.powerchallenge.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Persistence entity for a battery.
 *
 */
@Entity
@NamedQuery(name = "Battery.findWithinPostcodeRange", query = "SELECT b FROM Battery b WHERE b.postcode >= ?1 AND b.postcode <= ?2")
@NamedQuery(name = "Battery.findByNameAndPostcode", query = "SELECT b FROM Battery b WHERE b.name = ?1 AND b.postcode = ?2")
@Table(name = "Battery")
public class Battery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private int postcode;

    @Column
    private double capacity;

    public Battery() {
    }

    public Battery(String name, int postcode, double capacity) {
        this.name = name;
        this.postcode = postcode;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, postcode, capacity);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Battery other = (Battery) obj;
        return id == other.id && Objects.equals(name, other.name) && postcode == other.postcode
                && Double.doubleToLongBits(capacity) == Double.doubleToLongBits(other.capacity);
    }

    @Override
    public String toString() {
        return "Battery [id=" + id + ", name=" + name + ", postcode=" + postcode + ", capacity=" + capacity + "]";
    }

}
