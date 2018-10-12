package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.entity.Message;
import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import cz.upce.diplomovaprace.model.MessageModel;
import cz.upce.diplomovaprace.repository.MessageDao;
import cz.upce.diplomovaprace.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String MESSAGE_MODEL= "messageModel";

    @Autowired
    MessageDao messageDao;

    @Autowired
    UserDao userDao;

    @GetMapping("/list")
    public ModelAndView messageList(Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.MESSAGES);
        return new ModelAndView("message", model);
    }

    @GetMapping("/detail")
    public ModelAndView messageDetail(@RequestParam(MESSAGE_ID_PARAM) Integer messageId, Map<String, Object> model) {
        model.put("message", messageDao.findById(messageId));
        return new ModelAndView("messageDetail", model);
    }

    @GetMapping("/reply")
    public ModelAndView messageReply(@RequestParam(MESSAGE_ID_PARAM) Integer messageId,
                                     @ModelAttribute(MESSAGE_MODEL) MessageModel messageModel, BindingResult bindingResult,
                                     Map<String, Object> model) throws Exception {
        model.put("message", messageDao.findById(messageId).orElseThrow(Exception::new));
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
        m.setFromUserId(1);
        m.setUserByFromUserId(userDao.findById(1).orElse(null));
        m.setToUserId(1);
        m.setUserByToUserId(userDao.findById(1).orElseThrow(Exception::new));
        m.setSubject(messageModel.getSubject());
        m.setText(messageModel.getText());
        messageDao.save(m);
    }
}
