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
@ContextConfiguration(locations = {"classpath:spring/applicationContext-dao.xml",
        "classpath:spring/applicationContext-service.xml",
        "classpath:spring/applicationContext-trsaction.xml",
        "classpath:spring/applicationContext-mail.xml",
        "classpath:spring/springmvc.xml"})
public class FriendsControllerTest {
    private Logger logger = Logger.getLogger(getClass());
    private MockMvc mockMvc;
    @Autowired
    private FriendsController controller;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    /**
     * 主页
     * <p>
     * post("/loginTest").param("userName", "admin").param("password", "123")
     */
    @Test
    public void friendsList() throws Exception {
        String s = mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/friends/1?limit=0&offset=0")/*?limit=0&offset=0*/
        )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info(s);

    }

}