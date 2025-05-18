package com.cow.util.mail.impl;

import com.cow.util.mail.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Slf4j
@Service
public class IMailServiceImpl implements IMailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.nickname}")
    private String nickname;  // 应配置为纯昵称（如 "JSA"）

    @Value("${spring.mail.username}")
    private String from;      // 完整邮箱地址（如 "js2844054578@163.com"）

    //--------------------- 统一发件人设置方法 ---------------------
    private InternetAddress getFromAddress() throws Exception {
        return new InternetAddress(from, nickname);  // 正确组合昵称和邮箱
    }

    //--------------------- 简单文本邮件 --------------------------
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(getFromAddress().toString());  // 使用标准格式
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            mailSender.send(message);
            log.info("简单邮件已发送至 {}", to);
        } catch (Exception e) {
            log.error("简单邮件发送失败: {}", e.getMessage(), e);
            throw new RuntimeException("邮件发送失败", e);
        }
    }

    //--------------------- HTML邮件 -----------------------------
    @Async
    @Override
    public void sendHtmlMail(String to, String subject, String content) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(getFromAddress());
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
        log.info("HTML邮件已发送至 {}", to);
    }

    //--------------------- 带附件的邮件 --------------------------
    @Async
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(getFromAddress());
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = file.getFilename();
        helper.addAttachment(fileName, file);

        mailSender.send(message);  // 必须调用发送
        log.info("附件邮件已发送至 {}", to);
    }

    //--------------------- 模板邮件 -----------------------------
    @Async
    @Override
    public void sendTemplateMail(String to, String subject, String template, Map<String, Object> data) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(getFromAddress());
            helper.setTo(to);
            helper.setSubject(subject);

            Context context = new Context();
            context.setVariables(data);
            String emailContent = templateEngine.process(template, context);
            helper.setText(emailContent, true);

            mailSender.send(mimeMessage);
            log.info("模板邮件已发送至 {}", to);
        } catch (Exception e) {
            log.error("模板邮件发送失败: {}", e.getMessage(), e);
            throw new RuntimeException("邮件发送失败", e);
        }
    }
}