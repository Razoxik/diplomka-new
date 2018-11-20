package cz.upce.diplomovaprace.service;

import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.FriendModel;

import java.util.List;

public interface FriendService {

    List<FriendModel> prepareFriendModels() throws EntityNotFoundException;
}