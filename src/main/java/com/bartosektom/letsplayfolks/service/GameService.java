package com.bartosektom.letsplayfolks.service;

import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.model.GameModel;

import java.util.List;

public interface GameService {

    GameModel prepareGameModel(Integer gameId) throws EntityNotFoundException;

    List<GameModel> prepareGameModels() throws EntityNotFoundException;

    void createGame(GameModel gameModel) throws EntityNotFoundException;

    void approveGame(GameModel gameModel) throws EntityNotFoundException;
}
