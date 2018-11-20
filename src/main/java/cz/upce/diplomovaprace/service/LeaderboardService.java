package cz.upce.diplomovaprace.service;

import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.LeaderboardModel;

import java.util.List;

public interface LeaderboardService {

    List<LeaderboardModel> prepareLeaderboardModels(Integer gameId) throws EntityNotFoundException;
}
