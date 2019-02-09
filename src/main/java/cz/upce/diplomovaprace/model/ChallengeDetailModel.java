package cz.upce.diplomovaprace.model;

import java.util.List;

public class ChallengeDetailModel {
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
