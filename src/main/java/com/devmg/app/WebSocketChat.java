package com.devmg.app;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ServerEndpoint(value="/echo.do")
public class WebSocketChat {
    
    private static final List<Session> sessionList=new ArrayList<Session>();;
    private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);
    
    public WebSocketChat() {
    	System.out.println("웹 소켓(서버) 객체 생성");
    }
    
    @OnOpen
    public void onOpen(Session session) {
        logger.info("연결된 아이디 : "+session.getId());
        try {
            final Basic basic=session.getBasicRemote();
            basic.sendText("대화방에 연결되었습니다!");
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        sessionList.add(session);
    }
    
    /*
     * ��� ����ڿ��� �޽����� �����Ѵ�.
     * @param self
     * @param sender
     * @param message
     */
    private void sendAllSessionToMessage(Session self, String sender, String message) {
    	
        try {
            for(Session session : WebSocketChat.sessionList) {
                if(!self.getId().equals(session.getId())) {
                    session.getBasicRemote().sendText(sender+" : "+message);
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }
    
    /*
     * ���� �Է��ϴ� �޼���
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session) {
    	
    	session.getId();
    	String sender = message.split(",")[1];
    	message = message.split(",")[0];
    	
        logger.info("Message From "+sender + ": " + message);
        try {
            final Basic basic=session.getBasicRemote();
            basic.sendText(sender +" : " + message);
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        sendAllSessionToMessage(session, sender, message);
    }
    
    @OnError
    public void onError(Throwable e,Session session) {
        
    }
    
    @OnClose
    public void onClose(Session session) {
        logger.info("Session "+session.getId()+" has ended");
        sessionList.remove(session);
    }
}
