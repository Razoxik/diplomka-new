package com.bartosektom.letsplayfolks.service;

import com.bartosektom.letsplayfolks.exception.UserAlreadyExistException;
import com.bartosektom.letsplayfolks.model.UserModel;
import com.bartosektom.letsplayfolks.model.UserRatingModel;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;

import java.util.List;

public interface UserService {

    List<UserRatingModel> prepareUserRatingModels(Integer userId) throws EntityNotFoundException;

    UserModel prepareUserModel(Integer userId) throws EntityNotFoundException;

    void saveUserModel(Integer userId, UserModel userModel) throws EntityNotFoundException;

    boolean isOwnerOfProfile(Integer userId);

    void createUserFromModel(UserModel userModel) throws EntityNotFoundException, UserAlreadyExistException;
}
