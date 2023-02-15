package com.erental.service.impl;

import com.erental.service.MailContentBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author TEGA
 */
@Service
@AllArgsConstructor
public class MailContentBuilderImpl implements MailContentBuilder{

    private final TemplateEngine templateEngine;
    
    @Override
    public String build(String message) {
        
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
        
    }
    
}
