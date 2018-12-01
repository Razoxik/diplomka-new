package cz.upce.diplomovaprace.service.impl;

import com.google.common.base.Preconditions;
import cz.upce.diplomovaprace.entity.Game;
import cz.upce.diplomovaprace.entity.Rating;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.manager.SessionManager;
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
    SessionManager sessionManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RatingRepository ratingRepository;

    @Override
    public List<UserRatingModel> prepareUserRatingModels(Integer userId) throws EntityNotFoundException {
        Preconditions.checkNotNull(userId);
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
        Preconditions.checkNotNull(userId);
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        LocalDateTimeJPAConverter converter = new LocalDateTimeJPAConverter();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime friendsFrom = converter.convertToEntityAttribute(user.getCreated());
        LocalDateTime lastLogin = converter.convertToEntityAttribute(user.getLastLogin());

        UserModel userModel = new UserModel();
        userModel.setUserId(userId);
        userModel.setUserName(user.getUserName());
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmail());
        userModel.setCreated(friendsFrom.format(formatter));
        userModel.setLastLogin(lastLogin.format(formatter));
        userModel.setAboutMe(user.getAboutMe());

        return userModel;
    }

    @Override
    public void saveUserModel(Integer userId, UserModel userModel) throws EntityNotFoundException {
        Preconditions.checkNotNull(userId);
        Preconditions.checkNotNull(userModel);

        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        user.setUserName(userModel.getUserName());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setAboutMe(userModel.getAboutMe());

        userRepository.save(user);
    }

    @Override
    public boolean isOwnerOfProfile(Integer userId) {
        Preconditions.checkNotNull(userId);

        return sessionManager.getUserId() == userId;
    }
}
