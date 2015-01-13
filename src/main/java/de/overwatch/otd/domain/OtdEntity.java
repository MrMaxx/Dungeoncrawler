package de.overwatch.otd.domain;


import javax.persistence.*;

@MappedSuperclass
public class OtdEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
