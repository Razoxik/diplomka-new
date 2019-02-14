package com.bartosektom.letsplayfolks.service;

import com.bartosektom.letsplayfolks.model.LeaderboardModel;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;

import java.util.List;

public interface LeaderboardService {

    List<LeaderboardModel> prepareLeaderboardModels(Integer gameId) throws EntityNotFoundException;
}
