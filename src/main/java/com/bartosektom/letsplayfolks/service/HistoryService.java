package com.bartosektom.letsplayfolks.service;

import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.model.HistoryModel;

import java.util.List;

public interface HistoryService {
    HistoryModel prepareHistoryModel(ChallengeResult challengeResult);

    List<HistoryModel> prepareHistoryModels(List<ChallengeResult> challengeResults);
}
