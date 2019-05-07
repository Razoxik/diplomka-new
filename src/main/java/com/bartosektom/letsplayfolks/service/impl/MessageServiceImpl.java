package com.bartosektom.letsplayfolks.service.impl;

import com.bartosektom.letsplayfolks.entity.Message;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.manager.SessionManager;
import com.bartosektom.letsplayfolks.model.MessageModel;
import com.bartosektom.letsplayfolks.repository.MessageRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.service.MessageService;
import com.bartosektom.letsplayfolks.tools.LocalDateTimeJPAConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    SessionManager sessionManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Override
    public MessageModel prepareMessageModel(Message message) {
        int id = message.getId();
        String author = message.getUserByFromUserId().getUserName();
        String subject = message.getSubject();
        String text = message.getText();
        LocalDateTimeJPAConverter converter = new LocalDateTimeJPAConverter();
        LocalDateTime sentDate = converter.convertToEntityAttribute(message.getCreated());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedSentDate = sentDate.format(formatter);

        MessageModel messageModel = new MessageModel();
        messageModel.setId(id);
        messageModel.setAuthor(author);
        messageModel.setSubject(subject);
        messageModel.setText(text);
        messageModel.setSentDate(sentDate);
        messageModel.setFormattedSentDate(formattedSentDate);

        return messageModel;
    }

    @Override
    public List<MessageModel> prepareMessagesModels(List<Message> messages) {
        List<MessageModel> messageModels = new ArrayList<>();
        for (Message message : messages) {
            MessageModel messageModel = prepareMessageModel(message);
            messageModels.add(messageModel);
        }
        return messageModels;
    }

    @Override
    public void createMessageFromModel(MessageModel messageModel) throws EntityNotFoundException {
        String subject = messageModel.getSubject();
        String text = messageModel.getText();
        User userFrom = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        User userTo = userRepository.findByUserName(messageModel.getAuthor());

        if (userTo == null) {
            throw new EntityNotFoundException("User from or user to cannot be null!");
        }

        Message message = new Message();
        message.setSubject(subject);
        message.setText(text);
        message.setUserByFromUserId(userFrom);
        message.setUserByToUserId(userTo);

        messageRepository.save(message);
    }
}
