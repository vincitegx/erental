package com.erental.service;

import com.erental.domain.NotificationEmail;


/**
 *
 * @author TEGA
 */
public interface MailService {
    
    void sendMail(NotificationEmail notificationEmail);
    
}
