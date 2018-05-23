package com.amoxu.controller;

import org.apache.log4j.Logger;
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
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-dao.xml",
        "classpath:spring/applicationContext-service.xml",
        "classpath:spring/applicationContext-trsaction.xml",
        "classpath:spring/applicationContext-mail.xml",
        "classpath:spring/applicationContext-shiro.xml",
        "classpath:spring/springmvc.xml"})
public class ThumbsControllerTest {

    private Logger logger = Logger.getLogger(getClass());
    private MockMvc mockMvc;
    @Autowired
    private ThumbsController thumbsController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(thumbsController).build();

    }

    @Test
    public void eventsLike() {
        String s = null;
        try {
            s = mockMvc.perform(
                    MockMvcRequestBuilders.
                            post("/like/event/11")

            )
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            logger.error("Exception: ",e);
        }

        logger.info(s);
    }

    @Test
    public void commentsLike() {
    }

    @Test
    public void sharesLike() {
    }

    @Test
    public void topicLike() {
    }
}