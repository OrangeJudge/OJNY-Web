package models;

import play.db.ebean.Model;
import utils.OJException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

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
}
