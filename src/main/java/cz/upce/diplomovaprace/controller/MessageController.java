package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.entity.Message;
import cz.upce.diplomovaprace.entity.User;
import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import cz.upce.diplomovaprace.manager.SessionManager;
import cz.upce.diplomovaprace.model.MessageModel;
import cz.upce.diplomovaprace.repository.MessageRepository;
import cz.upce.diplomovaprace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping("/message")
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class MessageController {

    private static final String MESSAGE_ID_PARAM = "messageId";
    private static final String MESSAGE_MODEL = "messageModel";

    @Autowired
    SessionManager sessionManager;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ROLE')")
    public ModelAndView messageList(Map<String, Object> model) throws Exception {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MESSAGES);
        User user =  userRepository.findById(sessionManager.getUserId()).orElseThrow(Exception::new);
        model.put("messages", messageRepository.findMessagesByUserByToUserId(user));
        return new ModelAndView("message", model);
    }

    @GetMapping("/detail")
    public ModelAndView messageDetail(@RequestParam(MESSAGE_ID_PARAM) Integer messageId, Map<String, Object> model) {
        model.put("message", messageRepository.findById(messageId));
        return new ModelAndView("messageDetail", model);
    }

    @GetMapping("/reply")
    public ModelAndView messageReply(@RequestParam(MESSAGE_ID_PARAM) Integer messageId,
                                     @ModelAttribute(MESSAGE_MODEL) MessageModel messageModel, BindingResult bindingResult,
                                     Map<String, Object> model) throws Exception {
        model.put("message", messageRepository.findById(messageId).orElseThrow(Exception::new));
        return new ModelAndView("createMessage", model);
    }

    @PostMapping("/send")
    public ModelAndView sendMessage(@ModelAttribute("messageModel") MessageModel messageModel, BindingResult bindingResult,
                                    Map<String, Object> model) throws Exception {
        createMessageFromModel(messageModel);
        return new ModelAndView("createMessage", model);
    }

    private void createMessageFromModel(MessageModel messageModel) throws Exception {
        Message m = new Message();
        m.setCreated(new Timestamp(2));
       // m.setFromUserId(sessionManager.getUserId());
        m.setUserByFromUserId(userRepository.findById(sessionManager.getUserId()).orElse(null));
       // m.setToUserId(userRepository.findUserByUsername(messageModel.getNickname()).getUserId());
        m.setUserByToUserId(userRepository.findUserByUsername(messageModel.getNickname()));
        m.setSubject(messageModel.getSubject());
        m.setText(messageModel.getText());
        messageRepository.save(m);
    }
}
