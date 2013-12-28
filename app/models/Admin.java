package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin extends Model {
    @Id
    public int id;
    public String username;
    public String password; // need hash
}
