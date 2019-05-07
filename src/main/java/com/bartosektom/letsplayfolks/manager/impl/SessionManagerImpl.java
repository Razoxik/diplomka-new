package com.bartosektom.letsplayfolks.manager.impl;

import com.bartosektom.letsplayfolks.constants.CommonConstants;
import com.bartosektom.letsplayfolks.manager.SessionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Component
public class SessionManagerImpl implements SessionManager {

    private static final Logger LOGGER = Logger.getLogger(SessionManagerImpl.class.getName());

    @Override
    public HttpSession getHttpSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (request.getSession() == null) {
            LOGGER.info("HttpServletRequest or HttpSession is null");
            return null;
        }
        return request.getSession();
    }

    @Override
    public Object getSessionAttribute(String name) {
        HttpSession session = getHttpSession();
        if (session == null) {
            LOGGER.info("Session is null. Can not get session attribute name: " + name);
            return null;
        } else {
            return session.getAttribute(name);
        }
    }

    @Override
    public int getUserId() {
        return (int) getSessionAttribute(CommonConstants.USER_ID);
    }
}
