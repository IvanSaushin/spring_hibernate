package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private String series;

    @OneToOne(mappedBy = "car")
    @PrimaryKeyJoinColumn
    private User user;

    public Car() {}

    public Car(String model, String series) {
        this.model = model;
        this.series = series;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }
    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return getModel() + ", " + getSeries();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;
        Car car = (Car) obj;
        return getModel() == car.getModel() && getSeries() == car.getSeries();
    }
}
