package cz.upce.diplomovaprace.service;


import cz.upce.diplomovaprace.dto.UserDto;
import cz.upce.diplomovaprace.entity.Challenge;
import cz.upce.diplomovaprace.entity.User;
import javafx.util.Pair;

import java.util.List;

public interface ChallengeService {

    boolean isUserAlreadyInChallenge(Challenge challenge) ;

    boolean isChallengeFinished(Challenge challenge);

    Pair<List<User>, List<User>> prepareTeamsUsers(Challenge challenge);

    List<UserDto> prepareTeamDtos(Challenge challenge, List<User> users);

    Pair<List<UserDto>, List<UserDto>> prepareTeamsDtos(Challenge challenge);

}
