package com.amoxu.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml",
        "classpath:spring/applicationContext-service.xml",
        "classpath:spring/applicationContext-trsaction.xml",
        "classpath:spring/applicationContext-shiro.xml",
        "classpath:spring/applicationContext-mail.xml",
        "classpath:spring/springmvc.xml"})
public class CommentControllerTest {
    /*
        private Logger logger = Logger.getLogger(getClass());
        private MockMvc mockMvc;
        public Subject subject;
        @Autowired
        private DefaultWebSecurityManager securityManager;
        @Autowired(required = false)
        private WebApplicationContext webApplicationContext;
        protected MockHttpSession mockHttpSession;
        protected MockHttpServletResponse mockHttpServletResponse;
        protected MockHttpServletRequest mockHttpServletRequest;

        @Autowired
        ShiroFilterFactoryBean shiroFilter;

        @Autowired
        CommentController commentController;
    */
    private Logger logger = Logger.getLogger(getClass());
    private MockMvc mockMvc;
    @Autowired
    CommentController controller;


    @Autowired
    private DefaultSecurityManager securityManager;


    @Before
    public void setUp()  {

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)

                .build();

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("amoxuk", "8080a8f5e558d5fbb86f0ef97c101d5edd0e75b1"));



    }

    @Test


    public void pushComment() {
        String s = null;

        try {
            s = mockMvc.perform(MockMvcRequestBuilders.get("/index/comment"))
                    .andExpect(status().isOk())
                    .andReturn().getResponse()
                    .getContentAsString();
        } catch (Exception e) {
            logger.error("Exception: ",e);
        }

        logger.info(s);
    }
}