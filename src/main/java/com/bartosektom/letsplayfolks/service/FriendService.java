package com.bartosektom.letsplayfolks.service;

import com.bartosektom.letsplayfolks.model.FriendModel;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;

import java.util.List;

public interface FriendService {

    List<FriendModel> prepareFriendModels() throws EntityNotFoundException;

    boolean canBeAddedToFriends(Integer userId) throws EntityNotFoundException;

    void addToFriend(Integer userId) throws EntityNotFoundException;
}