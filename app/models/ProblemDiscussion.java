package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProblemDiscussion extends Model {
    @Id
    public long id;
    public String title;
    @ManyToOne
    public Problem problem;
    @OneToOne
    public ProblemDiscussion parent;
    @ManyToOne
    public Member member;
    @Lob
    public String content;
    public Date createdDate;
}
