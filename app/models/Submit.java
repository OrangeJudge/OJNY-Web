package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class Submit extends Model {
    @Id
    public long id;
    public int language;
    @Lob
    public String source;
    @ManyToOne
    public Problem problem;
    @ManyToOne
    public Member member;
    public long problemTimeStamp;
}
