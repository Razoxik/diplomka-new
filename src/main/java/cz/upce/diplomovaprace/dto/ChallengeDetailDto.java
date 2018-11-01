package cz.upce.diplomovaprace.dto;

import java.util.List;

public class ChallengeDetailDto {
    private List<UserDto> firstTeam;
    private List<UserDto> secondTeam;
    private int maxPlayers;


    public List<UserDto> getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(List<UserDto> firstTeam) {
        this.firstTeam = firstTeam;
    }

    public List<UserDto> getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(List<UserDto> secondTeam) {
        this.secondTeam = secondTeam;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }


}
