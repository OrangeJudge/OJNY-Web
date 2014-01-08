package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProblemVote {
    @Id
    public long id;
    @ManyToOne
    public Problem problem;
    @ManyToOne
    public Member member;
    public boolean up;
}
