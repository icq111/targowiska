package pl.minicode.targowiska.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.common.INotificationService;
import pl.minicode.targowiska.common.NotificationMessage;
import pl.minicode.targowiska.common.NotificationMessageType;

@Service
public class NotificationServiceImpl implements INotificationService {
	
	public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";
	
	@Autowired
	private HttpSession httpSession;

	@Override
    public void addInfoMessage(String msg) {
        addNotificationMessage(NotificationMessageType.NOTIF_INFO, msg);
    }

    @Override
    public void addErrorMessage(String msg) {
        addNotificationMessage(NotificationMessageType.NOTIF_ERROR, msg);
    }

    private void addNotificationMessage(NotificationMessageType type, String msg) {
        List<NotificationMessage> notifyMessages = (List<NotificationMessage>)
        httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
        if (notifyMessages == null) {
            notifyMessages = new ArrayList<NotificationMessage>();
        }
        notifyMessages.add(new NotificationMessage(type, msg));
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notifyMessages);
    }

}
