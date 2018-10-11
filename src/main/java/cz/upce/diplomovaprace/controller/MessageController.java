package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.repository.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageDao messageDao;

    @GetMapping("/list")
    public ModelAndView renderMap(Map<String, Object> model) {
        model.put("activeTab", "Message");
        return new ModelAndView("message", model);
    }

    @GetMapping("/detail")
    public ModelAndView detailMessage(@RequestParam("messageId") Integer messageId, Map<String, Object> model) {
        model.put("activeTab", "Message");
        model.put("message", messageDao.findById(messageId));
        return new ModelAndView("messageDetail", model);
    }

    @GetMapping("/reply")
    public ModelAndView replyMessage(@RequestParam("messageId") Integer messageId, Map<String, Object> model) {
        model.put("activeTab", "Message");
        model.put("message", messageDao.findById(messageId).get());
        return new ModelAndView("createMessage", model);
    }
}
