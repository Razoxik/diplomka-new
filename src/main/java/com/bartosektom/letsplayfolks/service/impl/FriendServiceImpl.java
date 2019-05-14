package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.entity.Friend;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.manager.SessionManager;
import com.bartosektom.letsplayfolks.model.FriendModel;
import com.bartosektom.letsplayfolks.repository.FriendRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.service.FriendService;
import com.bartosektom.letsplayfolks.tools.LocalDateTimeJPAConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    SessionManager sessionManager;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<FriendModel> prepareFriendModels() throws EntityNotFoundException {
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);

        List<FriendModel> friendModels = new ArrayList<>();
        for (Friend friend : friendRepository.findByUserByFromUserId(user)) {
            User us = friend.getUserByToUserId();

            long userId = us.getId();
            String userName = us.getUserName();
            String firstName = us.getFirstName();
            String lastName = us.getLastName();
            LocalDateTimeJPAConverter converter = new LocalDateTimeJPAConverter();
            LocalDateTime friendsFrom = converter.convertToEntityAttribute(friend.getCreated());
            LocalDateTime lastLogin = converter.convertToEntityAttribute(us.getLastLogin());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String formattedFriendsFrom = friendsFrom.format(formatter);
            String formattedLastLogin = lastLogin.format(formatter);

            FriendModel friendModel = new FriendModel();
            friendModel.setUserId(userId);
            friendModel.setUserName(userName);
            friendModel.setFirstName(firstName);
            friendModel.setLastName(lastName);
            friendModel.setFriendsFrom(formattedFriendsFrom);
            friendModel.setLastLogin(formattedLastLogin);
            friendModels.add(friendModel);
        }
        return friendModels;
    }

    @Override
    public boolean canBeAddedToFriends(Integer userId) throws EntityNotFoundException {
        User userInSession = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        User userDisplayed = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        boolean isSameUser = userInSession.equals(userDisplayed);
        boolean isAlreadyInFriends = userInSession.getFriendsByFromUserId().contains(friendRepository.findByUserByToUserIdAndUserByFromUserId(userDisplayed, userInSession));

        return !isSameUser && !isAlreadyInFriends;
    }

    @Override
    public void addToFriend(Integer userId) throws EntityNotFoundException {
        User userInSession = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        User userDisplayed = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        Friend friend = new Friend();
        friend.setUserByFromUserId(userInSession);
        friend.setUserByToUserId(userDisplayed);
        friendRepository.save(friend);
    }
}
