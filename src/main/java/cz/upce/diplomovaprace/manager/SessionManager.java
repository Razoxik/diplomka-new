package cz.upce.diplomovaprace.manager;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public interface SessionManager {

    Object getSessionAttribute(String name);

    HttpSession getHttpSession();

    void setSessionAttribute(String name, Object value);

    int getUserId();
}
