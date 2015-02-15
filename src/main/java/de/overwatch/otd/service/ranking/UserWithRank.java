package de.overwatch.otd.service.ranking;


public class UserWithRank {

    private Integer id;
    private String username;
    private int score;
    private Integer rank;
    private boolean dungeonExists;

    public UserWithRank(Integer id, String username, int score, int rank, boolean dungeonExists) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.rank = rank;
        this.dungeonExists = dungeonExists;
    }

    public boolean getDungeonExists() {
        return dungeonExists;
    }

    public void setDungeonExists(boolean dungeonExists) {
        this.dungeonExists = dungeonExists;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
