package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

import java.util.Date;

@Entity
public class SubmitComment extends Model {
    @Id
    public long id;
    @ManyToOne
    public Submit submit;
    @ManyToOne
    public Member member;

    @Lob
    public String content;
    public Date createdDate;
}
