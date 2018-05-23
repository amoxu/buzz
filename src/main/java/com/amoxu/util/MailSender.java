package com.amoxu.util;


import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * <code>MailServiceImpl</code>
 * </p>
 * Description:
 * 邮件系统实现类
 *
 * @author jianzh5
 * @version 2017/3/31 17:10
 * @since 1.0
 */
@Component
public class MailSender {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SimpleMailMessage simpleMailMessage;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Autowired
    private TaskExecutor taskExecutor;

    private final Logger logger = Logger.getLogger(getClass());

    /**
     * 构建邮件内容，发送邮件。
     *
     * @param user 用户
     * @param url  链接
     */
    public void send(String user, String subject, String url, String to) {
        String nickname = user;
        String text = "";
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("user", user);
        map.put("url", url);
        try {
//            从FreeMarker模板生成邮件内容
            Template template;
            if (StaticEnum.MAIL_ACTIVE.equals(subject)) {
                template = freeMarkerConfigurer.getConfiguration().getTemplate("register.ftl");

            } else if (StaticEnum.MAIL_REGISTER.equals(subject)) {
                template = freeMarkerConfigurer.getConfiguration().getTemplate("active.ftl");
            } else if (StaticEnum.MAIL_FIND_PASSWORD.equals(subject)) {
                template = freeMarkerConfigurer.getConfiguration().getTemplate("find.ftl");
            } else {
                template = freeMarkerConfigurer.getConfiguration().getTemplate("find.ftl");
            }

//            模板中用${XXX}站位，map中key为XXX的value会替换占位符内容。
            text = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        } catch (IOException | TemplateException e) {
            logger.error("Exception: ",e);
        }
        /*sendMail(to, null, text);*/
        this.taskExecutor.execute(new SendMailThread(to, subject, text));

        /*       Thread.sleep(10000);
         */
    }

    //    内部线程类，利用线程池异步发邮件。
    private class SendMailThread implements Runnable {
        private String to;
        private String subject;
        private String content;

        private SendMailThread(String to, String subject, String content) {
            super();
            this.to = to;
            this.subject = subject;
            this.content = content;
        }

        @Override
        public void run() {
            sendMail(to, subject, content);
        }
    }

    /**
     * 发送邮件
     *
     * @param to      收件人邮箱
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    public void sendMail(String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setFrom(simpleMailMessage.getFrom());

            messageHelper.setSubject(subject);

            messageHelper.setTo(to);
            messageHelper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("Exception: ",e);
        }
    }
}