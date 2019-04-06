package by.it.app.model;

import javax.persistence.*;

/**
 * Class for represent car information
 */
@Entity
@Table
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String carNumber;
    private String vin;

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, orphanRemoval = true)
    private Insurance insurance;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", carNumber='" + carNumber + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
