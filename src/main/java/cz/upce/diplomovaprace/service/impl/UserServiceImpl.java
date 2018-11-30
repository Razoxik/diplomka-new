package cz.upce.diplomovaprace.service.impl;

import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.Rating;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.UserModel;
import cz.upce.diplomovaprace.model.UserRatingModel;
import cz.upce.diplomovaprace.repository.RatingRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import cz.upce.diplomovaprace.service.UserService;
import cz.upce.diplomovaprace.tools.LocalDateTimeJPAConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<UserRatingModel> prepareUserRatingModels(Integer userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        List<Rating> ratings = ratingRepository.findByUserByUserId(user);

        List<UserRatingModel> userRatingModels = new ArrayList<>();
        for (Rating rating : ratings) {
            Game game = rating.getGameByGameId();
            long ratingValue = rating.getRating();
            String gameName = game.getName();

            UserRatingModel userRatingModel = new UserRatingModel();
            userRatingModel.setUserId(userId);
            userRatingModel.setGame(gameName);
            userRatingModel.setRating(ratingValue);
            userRatingModels.add(userRatingModel);
        }
        return userRatingModels;
    }

    @Override
    public UserModel prepareUserModel(Integer userId) throws EntityNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        String userName = user.getUserName();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        LocalDateTimeJPAConverter converter = new LocalDateTimeJPAConverter();
        LocalDateTime friendsFrom = converter.convertToEntityAttribute(user.getCreated());
        LocalDateTime lastLogin = converter.convertToEntityAttribute(user.getLastLogin());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedCreated = friendsFrom.format(formatter);
        String formattedLastLogin = lastLogin.format(formatter);

        UserModel userModel = new UserModel();
        userModel.setUserId(userId);
        userModel.setUserName(userName);
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setCreated(formattedCreated);
        userModel.setLastLogin(formattedLastLogin);
        return userModel;
    }
}
