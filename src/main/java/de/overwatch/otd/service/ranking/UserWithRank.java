package de.overwatch.otd.service.ranking;


public class UserWithRank {

    private Integer id;
    private String username;
    private int score;
    private int rank;

    public UserWithRank(Integer id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
