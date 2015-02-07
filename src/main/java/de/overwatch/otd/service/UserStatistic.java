package de.overwatch.otd.service;


public class UserStatistic {


    private int attacksTotal = 0;
    private int attacksWon = 0;
    private int defendsTotal = 0;
    private int defendsWon = 0;

    public int getAttacksTotal() {
        return attacksTotal;
    }

    public void setAttacksTotal(int attacksTotal) {
        this.attacksTotal = attacksTotal;
    }

    public int getAttacksWon() {
        return attacksWon;
    }

    public void setAttacksWon(int attacksWon) {
        this.attacksWon = attacksWon;
    }

    public int getDefendsTotal() {
        return defendsTotal;
    }

    public void setDefendsTotal(int defendsTotal) {
        this.defendsTotal = defendsTotal;
    }

    public int getDefendsWon() {
        return defendsWon;
    }

    public void setDefendsWon(int defendsWon) {
        this.defendsWon = defendsWon;
    }
}