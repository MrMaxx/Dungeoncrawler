package de.overwatch.otd.service;

import de.overwatch.otd.domain.Fight;

import java.util.Date;


public class PublicFight {

    private Integer id;
    private Integer attackingUserId;
    private Integer defendingUserId;
    private String attackingUserName;
    private String defendingUserName;
    private Date createdAt;
    private Fight.FightState fightState;
    private Fight.Outcome outcome;

    public PublicFight(Fight fight) {
        this.id = fight.getId();
        this.createdAt = fight.getCreated();
        this.fightState = fight.getFightState();
        if( fight.getAttackForce() != null && fight.getAttackForce().getUser() != null){
            this.attackingUserId = fight.getAttackForce().getUser().getId();
            this.attackingUserName = fight.getAttackForce().getUser().getUsername();
        }
        if( fight.getDungeon() != null && fight.getDungeon().getUser() != null){
            this.defendingUserId = fight.getDungeon().getUser().getId();
            this.defendingUserName = fight.getDungeon().getUser().getUsername();
        }
        this.outcome = fight.getOutcome();
    }

    public Integer getId() {
        return id;
    }

    public Fight.FightState getFightState() {
        return fightState;
    }

    public Integer getAttackingUserId() {
        return attackingUserId;
    }

    public Integer getDefendingUserId() {
        return defendingUserId;
    }

    public String getAttackingUserName() {
        return attackingUserName;
    }

    public String getDefendingUserName() {
        return defendingUserName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Fight.Outcome getOutcome() {
        return outcome;
    }
}
