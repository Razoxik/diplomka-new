package cz.upce.diplomovaprace.service;

import cz.upce.diplomovaprace.exception.EntityNotFoundException;
import cz.upce.diplomovaprace.model.UserModel;
import cz.upce.diplomovaprace.model.UserRatingModel;

import java.util.List;

public interface UserService {

    List<UserRatingModel> prepareUserRatingModels(Integer userId) throws EntityNotFoundException;

    UserModel prepareUserModel(Integer userId) throws EntityNotFoundException;

    void saveUserModel(Integer userId, UserModel userModel) throws EntityNotFoundException;

    boolean isOwnerOfProfile(Integer userId);
}
