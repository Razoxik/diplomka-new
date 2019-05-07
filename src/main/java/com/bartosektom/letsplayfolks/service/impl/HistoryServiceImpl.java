package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.entity.Challenge;
import com.bartosektom.letsplayfolks.entity.ChallengeResult;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.ResultState;
import com.bartosektom.letsplayfolks.model.HistoryModel;
import com.bartosektom.letsplayfolks.service.HistoryService;
import com.bartosektom.letsplayfolks.tools.LocalDateTimeJPAConverter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Override
    public HistoryModel prepareHistoryModel(ChallengeResult challengeResult) {
        Challenge challenge = challengeResult.getChallengeByChallengeId();
        Game game = challenge.getGameByGameId();
        ResultState resultState = challengeResult.getResultStateByResultStateId();

        Integer challengeId = challenge.getId();
        String gameName = game.getName();
        LocalDateTimeJPAConverter converter = new LocalDateTimeJPAConverter();
        LocalDateTime startDate = converter.convertToEntityAttribute(challenge.getStart());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedStartDate = startDate.format(formatter);
        String result = resultState.getState();
        Integer scoreWinner = challengeResult.getScoreWinner();
        Integer scoreLooser = challengeResult.getScoreDefeated();

        HistoryModel historyModel = new HistoryModel();
        historyModel.setChallengeId(challengeId);
        historyModel.setStart(formattedStartDate);
        historyModel.setGameName(gameName);
        historyModel.setResultState(result);
        historyModel.setScoreWinner(scoreWinner);
        historyModel.setScoreLooser(scoreLooser);

        return historyModel;
    }

    @Override
    public List<HistoryModel> prepareHistoryModels(List<ChallengeResult> challengeResults) {
        List<HistoryModel> historyModels = new ArrayList<>();
        for (ChallengeResult challengeResult : challengeResults) {
            HistoryModel historyModel = prepareHistoryModel(challengeResult);
            historyModels.add(historyModel);
        }
        return historyModels;
    }
}
