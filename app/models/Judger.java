package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Judger {
    @Id
    public int id;
    public String address;
    public boolean available;
    public int queue;

    public static Model.Finder<Integer, Judger> find = new Model.Finder<Integer, Judger>(
            Integer.class, Judger.class
    );
}
