package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.entity.Message;
import com.bartosektom.letsplayfolks.entity.User;
import com.bartosektom.letsplayfolks.exception.EntityNotFoundException;
import com.bartosektom.letsplayfolks.manager.SessionManager;
import com.bartosektom.letsplayfolks.model.MessageModel;
import com.bartosektom.letsplayfolks.repository.MessageRepository;
import com.bartosektom.letsplayfolks.repository.UserRepository;
import com.bartosektom.letsplayfolks.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/message")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
public class MessageController {

    private static final String MESSAGE_ID_PARAM = "messageId";
    private static final String MESSAGE_SENT_PARAM = "messageSent";
    private static final String USER_NOT_FOUND_PARAM = "userNotFound";

    private static final String MESSAGE_MODEL_KEY = "messageModel";
    private static final String MESSAGES_MODEL_KEY = "messages";

    private static final String MESSAGE_LIST_VIEW_NAME = "/message/list";
    private static final String MESSAGE_DETAIL_VIEW_NAME = "/message/detail";
    private static final String MESSAGE_CREATE_VIEW_NAME = "/message/create";

    @Autowired
    SessionManager sessionManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageService messageService;

    @GetMapping("/list")
    public ModelAndView messageList(@RequestParam(value = MESSAGE_SENT_PARAM, required = false) boolean messageSent,
                                    Map<String, Object> model) throws EntityNotFoundException {
        User user = userRepository.findById(sessionManager.getUserId()).orElseThrow(EntityNotFoundException::new);
        List<Message> messages = messageRepository.findByUserByToUserId(user);

        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MESSAGES);
        model.put(MESSAGES_MODEL_KEY, messageService.prepareMessagesModels(messages));
        model.put(MESSAGE_SENT_PARAM, messageSent);

        return new ModelAndView(MESSAGE_LIST_VIEW_NAME, model);
    }

    @GetMapping("/detail")
    public ModelAndView messageDetail(@RequestParam(MESSAGE_ID_PARAM) Integer messageId,
                                      Map<String, Object> model) throws EntityNotFoundException {
        Message message = messageRepository.findById(messageId).orElseThrow(EntityNotFoundException::new);

        model.put(MESSAGE_MODEL_KEY, messageService.prepareMessageModel(message));

        return new ModelAndView(MESSAGE_DETAIL_VIEW_NAME, model);
    }

    @GetMapping("/create")
    public ModelAndView messageCreate(@RequestParam(value = USER_NOT_FOUND_PARAM, required = false) boolean userNotFound,
                                      @RequestParam(value = MESSAGE_ID_PARAM, required = false) Integer messageId,
                                      @RequestParam(value = "userName", required = false) String userName,
                                      @ModelAttribute(MESSAGE_MODEL_KEY) MessageModel messageModel,
                                      Map<String, Object> model) throws EntityNotFoundException {
        if (messageId != null) {
            Message message = messageRepository.findById(messageId).orElseThrow(EntityNotFoundException::new);
            messageModel.setAuthor(message.getUserByFromUserId().getUserName());
        }
        if (userName != null) {
            messageModel.setAuthor(userName);
        }
        model.put(MESSAGE_MODEL_KEY, messageModel);
        model.put(USER_NOT_FOUND_PARAM, userNotFound);

        return new ModelAndView(MESSAGE_CREATE_VIEW_NAME, model);
    }

    @PostMapping("/send")
    public ModelAndView messageSend(@ModelAttribute(MESSAGE_MODEL_KEY) MessageModel messageModel,
                                    Map<String, Object> model, RedirectAttributes redirectAttributes) {
        try {
            messageService.createMessageFromModel(messageModel);
        } catch (EntityNotFoundException e) {
            model.put(MESSAGES_MODEL_KEY, messageModel);
            model.put(USER_NOT_FOUND_PARAM, true);
            return new ModelAndView(MESSAGE_CREATE_VIEW_NAME, model);
        }
        redirectAttributes.addAttribute(MESSAGE_SENT_PARAM, true);

        return new ModelAndView("redirect:/message/list");
    }
}
