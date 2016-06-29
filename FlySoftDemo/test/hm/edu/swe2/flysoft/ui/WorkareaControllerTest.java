package hm.edu.swe2.flysoft.ui;

import hm.edu.swe2.flysoft.ui.controller.WorkareaController;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author Philipp Chavaroche
 * @version 27.06.2016
 */
public class WorkareaControllerTest {
    
    /*@Test
    public void TestInitWorkarea() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WorkareaController()).build();
        mockMvc.perform(get("/workarea")
            //.param("username", "john")
        ).andReturn();
        //.andExpect(request().sessionAttribute(LoginController.ACCOUNT_ATTRIBUTE, this.account))
        //.andExpect(redirectedUrl("/workarea.htm"));
    }*/
    
    @Test
    public void TestGetGraphData() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new WorkareaController()).build();
        mockMvc.perform(get("/workarea/graphdata")
            .param("yaxis", "Frequencies")
            .param("xaxis", "Time")
            .param("thirddim", "nothing")
            .param("timedim", "Week")
            .param("timerange[]", "01.01.2015")
            .param("timerange[]", "01.03.2015"))
            .andExpect(status().isOk())
        ;
        //.andExpect(request().sessionAttribute(LoginController.ACCOUNT_ATTRIBUTE, this.account))
        //.andExpect(redirectedUrl("/workarea.htm"));
    }
    
}
