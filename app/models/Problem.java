package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Problem extends Model {
    @Id
    public int id;

    public String title;
    @Lob
    public String description;
    public String tags;
    public String source;
    public int timeLimit; // in ms. 0 for not specified.
    public int memoryLimit; // in MB. 0 for not specified.
    public boolean specialJudge;

    public Date createdDate;
    public Date lastModifiedDate;
    public long timeStamp; // test case version.
    @ManyToOne
    public Member author;
    public int status; // 0 normal; 1 view only; 2 deleted;

    public int submitCount;
    public int passCount;
    public double passRatio;
    public double difficulty;
    public int vote;

    @OneToMany(mappedBy = "problem")
    public List<Submit> submitList;

    public static Finder<Integer, Problem> find = new Finder<Integer, Problem>(
            Integer.class, Problem.class
    );

    public void updateCreatedDate() {
        createdDate = new Date();
    }

    public void updateLastModifiedDate() {
        lastModifiedDate = new Date();
    }
}
