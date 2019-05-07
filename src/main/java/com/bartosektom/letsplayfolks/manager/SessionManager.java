package com.bartosektom.letsplayfolks.manager;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public interface SessionManager {

    HttpSession getHttpSession();

    Object getSessionAttribute(String name);

    int getUserId();
}
