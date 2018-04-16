package com.amoxu.entity;

import com.amoxu.util.ToolKit;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class MailUrlBuilder {
    private StringBuilder mailUrl;
    /**
     * note 参数 内容：时间戳$mail
     *
     * */
    private StringBuilder note = new StringBuilder();

    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();



    /**
     * @param name 用户名
     * @descript 构造用户验证邮箱地址
     */
    public MailUrlBuilder builder(String mail, String name) {
        setNote(mail);
        mailUrl = new StringBuilder()
                .append(request.getScheme())
                .append("://")
                .append(request.getServerName())
                .append(":")
                .append(request.getServerPort())
                .append("/user/active?id=")
                .append(ToolKit.aesEncrypt(name))
                .append("&key=")
                .append(ToolKit.md5Hex(getNote()));
        return this;
    }

    public String getMailUrl() {
        return mailUrl.toString();
    }


    public MailUrlBuilder setMailUrl(StringBuilder mailUrl) {
        this.mailUrl = mailUrl;
        return this;
    }

    public String getNote() {
        return note.toString();
    }

    public MailUrlBuilder setNote(String mail) {
        this.note.append(new Date().getTime());
        this.note.append("$");
        this.note.append(mail);
        return this;
    }
}
