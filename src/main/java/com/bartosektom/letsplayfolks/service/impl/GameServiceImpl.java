package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.constants.RatingConstants;
import com.bartosektom.letsplayfolks.service.GameService;
import com.bartosektom.letsplayfolks.constants.GameConstants;
import com.bartosektom.letsplayfolks.constants.GameParamConstants;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.GameParam;
import com.bartosektom.letsplayfolks.entity.Rating;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.model.GameModel;
import com.bartosektom.letsplayfolks.repository.GameParamRepository;
import com.bartosektom.letsplayfolks.repository.GameRepository;
import com.bartosektom.letsplayfolks.repository.RatingRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.tools.LocalDateTimeJPAConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GameParamRepository gameParamRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public GameModel prepareGameModel(Integer gameId) throws EntityNotFoundException {
        GameModel gameModel = new GameModel();
        if (gameId != null) {
            Game game = gameRepository.findById(gameId).orElseThrow(EntityNotFoundException::new);
            gameModel.setId(gameId);
            LocalDateTimeJPAConverter converter = new LocalDateTimeJPAConverter();
            LocalDateTime gameCreated = converter.convertToEntityAttribute(game.getCreated());
            game.setId(gameId);
            gameModel.setCreated(gameCreated);
            gameModel.setDescription(game.getDescription());
            gameModel.setName(game.getName());
            int numberOfPlayers = Integer.parseInt(gameParamRepository.findByGameByGameIdAndName(
                    game, GameParamConstants.NUMBER_OF_PLAYERS).getValue());
            gameModel.setNumberOfPlayers(numberOfPlayers);
        }
        return gameModel;
    }

    @Override
    public List<GameModel> prepareGameModels() throws EntityNotFoundException {
        List<GameModel> gameModels = new ArrayList<>();
        for (Game game : gameRepository.findByApproved(GameConstants.GAME_UNAPPROVED)) {
            gameModels.add(prepareGameModel(game.getId()));
        }
        return gameModels;
    }

    @Override
    public void createGame(GameModel gameModel) {
        Game game = new Game();
        game.setName(gameModel.getName());
        game.setDescription(gameModel.getDescription());
        game.setApproved(gameModel.getApproved());
        gameRepository.save(game);

        GameParam gameParam = new GameParam();
        gameParam.setGameByGameId(game);
        gameParam.setName(GameParamConstants.NUMBER_OF_PLAYERS);
        gameParam.setValue(String.valueOf(gameModel.getNumberOfPlayers()));
        gameParamRepository.save(gameParam);

        for (User user : userRepository.findAll()) {
            Rating rating = new Rating();
            rating.setUserByUserId(user);
            rating.setGameByGameId(game);
            rating.setRating(RatingConstants.DEFAULT_RATING);
            ratingRepository.save(rating);
        }
    }

    @Override
    public void approveGame(GameModel gameModel) throws EntityNotFoundException {
        Game game = gameRepository.findById(gameModel.getId()).orElseThrow(EntityNotFoundException::new);
        game.setName(gameModel.getName());
        game.setDescription(gameModel.getDescription());
        game.setApproved(GameConstants.GAME_APPROVED);
        gameRepository.save(game);

        GameParam gameParam = gameParamRepository.findByGameByGameIdAndName(game, GameParamConstants.NUMBER_OF_PLAYERS);
        gameParam.setValue(String.valueOf(gameModel.getNumberOfPlayers()));
        gameParamRepository.save(gameParam);
    }
}
