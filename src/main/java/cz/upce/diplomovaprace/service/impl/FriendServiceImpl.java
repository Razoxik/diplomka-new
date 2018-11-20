package cz.upce.diplomovaprace.service.impl;

import cz.upce.diplomovaprace.entity.Friend;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.manager.SessionManager;
import cz.upce.diplomovaprace.model.FriendModel;
import cz.upce.diplomovaprace.repository.FriendRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import cz.upce.diplomovaprace.service.FriendService;
import cz.upce.diplomovaprace.tools.LocalDateTimeJPAConverter;
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
}
