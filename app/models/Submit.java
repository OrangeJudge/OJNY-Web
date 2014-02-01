package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.db.ebean.Model;
import utils.OJException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Submit extends Model {
    @Id
    public long id;
    public int language;
    @Lob
    public String source;
    @JsonIgnore
    @ManyToOne
    public Problem problem;
    @JsonIgnore
    @ManyToOne
    public Member member;
    public long problemTimeStamp;

    public Date createTime;
    public Date updateTime;

    public int status;
    public String detail;

    public int judger = 0;

    public Submit() {
        createTime = new Date();
    }
    public static Finder<Integer, Submit> find = new Finder<Integer, Submit>(
            Integer.class, Submit.class
    );

    public void setMember(int memberId) throws OJException {
        Member member = Member.find.byId(memberId);
        if (member == null) {
            throw new OJException(1001, "Member not found");
        }
        this.member = member;
    }
    public void setProblem(int problemId) throws OJException {
        Problem problem = Problem.find.byId(problemId);
        if (problem == null) {
            throw new OJException(1001, "Problem not found.");
        }
        this.problem = problem;
    }

    public Integer getProblemId() {
        return problem == null ? null : problem.id;
    }

    public Long getProblemTimeStamp() {
        return problem == null ? null : problem.timeStamp;
    }

}
