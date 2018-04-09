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
        "classpath:spring/springmvc.xml"})
public class UserControllerTest {
    private Logger logger = Logger.getLogger(getClass());
    private MockMvc mockMvc;
    @Autowired
    private UserController controller;

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
    public void teatGetCollege() throws Exception {
        String s = mockMvc.perform(
                MockMvcRequestBuilders.
                        post("/user/login")
                        .sessionAttr("captcha", "capw")
                        .content("Fb6ieYk4I3fnNOIJH3echWOQWa98OzdHn1dnwbC0QKwAzrROXmQLDI0KbgkHLrV9sJVpM4pfnLUfA7mFxsGbgKhCRmI1roc9MD86sjU+i8Y=")
        )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info(s);

    }

    @Test
    public void getUserInfo() throws Exception {
        String s = mockMvc.perform(
                MockMvcRequestBuilders.
                        get("/user/info/1")
        )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        logger.info(s);
    }
}