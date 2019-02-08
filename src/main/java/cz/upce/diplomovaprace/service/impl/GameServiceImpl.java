package cz.upce.diplomovaprace.service.impl;

import cz.upce.diplomovaprace.constants.GameConstants;
import cz.upce.diplomovaprace.constants.GameParamConstants;
import cz.upce.diplomovaprace.constants.RatingConstants;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.GameParam;
import cz.upce.diplomovaprace.entity.Rating;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.GameModel;
import cz.upce.diplomovaprace.repository.GameParamRepository;
import cz.upce.diplomovaprace.repository.GameRepository;
import cz.upce.diplomovaprace.repository.RatingRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import cz.upce.diplomovaprace.service.GameService;
import cz.upce.diplomovaprace.tools.LocalDateTimeJPAConverter;
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
