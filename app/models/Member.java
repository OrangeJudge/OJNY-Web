package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Member extends Model {
    @Id
    public int id;
    public String username;
    public String email;
    public String password; // need hash

    public Date createdDate;
}
