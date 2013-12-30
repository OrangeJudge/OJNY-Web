package models;

import play.db.ebean.Model;
import utils.OJException;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Member extends Model {
    @Id
    public int id;
    private String username;
    private String email;
    private String password; // need hash
    
    public boolean gender; // female is true.

    public Date createdDate;

    public static Finder<Integer, Member> find = new Finder<Integer, Member>(
            Integer.class, Member.class
    );

    public Member() {
        createdDate = new Date();
    }

    public void setUsername(String username) throws OJException {
        if (!find.where("username = '" + username + "'").findList().isEmpty()) {
            throw new OJException(1001, "username is taken");
        }
        this.username = username;
    }

    public void setEmail(String email) throws OJException {
        if (!find.where("email = '" + email + "'").findList().isEmpty()) {
            throw new OJException(1002, "email is taken");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
