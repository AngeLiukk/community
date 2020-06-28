package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    // thymeleaf模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("jzdkklll@outlook.com", "TEST", "Welcome.");
    }

    // 不同于springMVC dispatcherServlet自动调用模板引擎（通过返回模板路径），
    // 此处需要注入并手动调用
    @Test
    public void testHtmlMail() {
        // thymeleaf用来传参的容器
        Context context = new Context();
        context.setVariable("username", "sunday");

        // 返回一个字符串（html文件）
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("jzdkklll@outlook.com", "HTML", content);
    }

}
