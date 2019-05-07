package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.constants.GameConstants;
import com.bartosektom.letsplayfolks.constants.RatingConstants;
import com.bartosektom.letsplayfolks.entity.Game;
import com.bartosektom.letsplayfolks.entity.Rating;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.exception.UserAlreadyExistException;
import com.bartosektom.letsplayfolks.manager.SessionManager;
import com.bartosektom.letsplayfolks.model.UserModel;
import com.bartosektom.letsplayfolks.model.UserRatingModel;
import com.bartosektom.letsplayfolks.repository.AvatarRepository;
import com.bartosektom.letsplayfolks.repository.GameRepository;
import com.bartosektom.letsplayfolks.repository.RatingRepository;
import com.bartosektom.letsplayfolks.repository.RoleRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.service.UserService;
import com.bartosektom.letsplayfolks.tools.LocalDateTimeJPAConverter;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    GameRepository gameRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AvatarRepository avatarRepository;

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

    @Override
    public void createUserFromModel(UserModel userModel) throws EntityNotFoundException, UserAlreadyExistException {
        String userName = userModel.getUserName().substring(0, 1).toUpperCase() + userModel.getUserName().substring(1).toLowerCase();
        if (userRepository.findByUserName(userName) != null) {
            throw new UserAlreadyExistException();
        }
        User user = new User();
        user.setUserName(userName);
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setAboutMe(userModel.getAboutMe());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRoleByRoleId(roleRepository.findById(1).orElseThrow(EntityNotFoundException::new));
        user.setAvatarByAvatarId(avatarRepository.findById(1).orElseThrow(EntityNotFoundException::new));

        userRepository.save(user);
        // After registration set default rating
        for (Game game : gameRepository.findByApproved(GameConstants.GAME_APPROVED)) {
            Rating rating = new Rating();
            rating.setGameByGameId(game);
            rating.setUserByUserId(user);
            rating.setRating(RatingConstants.DEFAULT_RATING);
            ratingRepository.save(rating);
        }
    }
}
