package com.bartosektom.letsplayfolks.model;

import java.io.Serializable;
import java.util.List;

public class ChallengeDetailModel implements Serializable {

    private static final long serialVersionUID = 5793535853176020415L;

    private List<ChallengeDetailUserModel> firstTeam;
    private List<ChallengeDetailUserModel> secondTeam;
    private int maxPlayers;

    public List<ChallengeDetailUserModel> getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(List<ChallengeDetailUserModel> firstTeam) {
        this.firstTeam = firstTeam;
    }

    public List<ChallengeDetailUserModel> getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(List<ChallengeDetailUserModel> secondTeam) {
        this.secondTeam = secondTeam;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
