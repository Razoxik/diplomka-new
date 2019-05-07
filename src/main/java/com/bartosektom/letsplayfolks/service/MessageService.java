package com.bartosektom.letsplayfolks.service;

import com.bartosektom.letsplayfolks.entity.Message;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.model.MessageModel;

import java.util.List;

public interface MessageService {
    MessageModel prepareMessageModel(Message message);

    List<MessageModel> prepareMessagesModels(List<Message> messages);

    void createMessageFromModel(MessageModel messageModel) throws EntityNotFoundException;
}
