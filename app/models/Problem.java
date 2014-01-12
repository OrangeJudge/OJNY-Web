package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Problem extends Model {
    @Id
    public int id;

    public String title;
    @Lob
    public String description;
    public String tags;
    public String source;
    public int timeLimit; // in ns. 0 for not specified.
    public int memoryLimit; // in MB. 0 for not specified.
    public boolean specialJudge;

    public Date createdDate;
    public Date lastModifiedDate;
    public long timeStamp; // test case version.
    @ManyToOne
    public Member author;
    public int status; // 0 normal; 1 view only; 2 deleted;

    public double difficulty;
    public int vote;

    public void updateCreatedDate() {
        createdDate = new Date();
    }

    public void updateLastModifiedDate() {
        lastModifiedDate = new Date();
    }
}
