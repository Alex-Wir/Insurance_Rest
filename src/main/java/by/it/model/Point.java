package by.it.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "point")
    private Set<Pos> poses;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @ManyToMany(mappedBy = "points", fetch = FetchType.LAZY)
    private Set<User> users;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Pos> getPoses() {
        return poses;
    }

    public void setPoses(Set<Pos> poses) {
        this.poses = poses;
    }
}
