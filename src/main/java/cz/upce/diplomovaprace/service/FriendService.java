package cz.upce.diplomovaprace.service;

import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.FriendModel;

import java.util.List;

public interface FriendService {

    List<FriendModel> prepareFriendModels() throws EntityNotFoundException;

    boolean canBeAddedToFriends(Integer userId) throws EntityNotFoundException;

    void addToFriend(Integer userId) throws EntityNotFoundException;
}