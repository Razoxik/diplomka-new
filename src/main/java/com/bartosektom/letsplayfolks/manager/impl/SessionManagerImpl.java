package com.bartosektom.letsplayfolks.manager.impl;

import com.bartosektom.letsplayfolks.manager.SessionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// PRO KAZDOU CLASSU MEJ INTERFACE, JE TO PREJ DOBRY viz google or tyhel dva odkazy
//https://softwareengineering.stackexchange.com/questions/159813/do-i-need-to-use-an-interface-when-only-one-class-will-ever-implement-it
//https://softwareengineering.stackexchange.com/questions/150045/what-is-the-point-of-having-every-service-class-have-an-interface
@Component
public class SessionManagerImpl implements SessionManager {

    @Override
    public Object getSessionAttribute(String name) {
        HttpSession session = getHttpSession();
        if (session == null) {
            // logger.info("Session is null cant get session attribute name: " + name);
            return null;
        } else {
            return session.getAttribute(name);
        }
    }

    @Override
    public void setSessionAttribute(String name, Object value) {
        HttpSession session = getHttpSession();
        if (session == null) {
            // logger.info("Session is null cant get session attribute name: " + name);
        } else {
            session.setAttribute(name, value);
        }
    }

    @Override
    public int getUserId() {
        return (int) getSessionAttribute("userId");
    }

    @Override
    public HttpSession getHttpSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (request == null || request.getSession() == null) {
            //logger.info("HttpServletRequest or HttpSession is null");
            return null;
        }
        return request.getSession();
    }
}

