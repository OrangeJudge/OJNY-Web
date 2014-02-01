package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Judger {
    @Id
    public int id;
    public String address;
    public boolean available;
    public int queue;
}
