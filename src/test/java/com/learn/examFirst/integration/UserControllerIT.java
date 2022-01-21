package com.learn.examFirst.integration;

import com.learn.examFirst.BetaExamApplication;
import com.learn.examFirst.TestData;
import com.learn.examFirst.dtos.UsersDto;
import com.learn.examFirst.services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BetaExamApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerIT {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeAll
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenFilterBy_whenGetUserDetails_thenStatue200() throws Exception {
        List<UsersDto> createdTestUser = TestData.createdTestUserDtoList();

        given(userService.getUserDetails(Optional.of(TestData.FILTER_BY_EMAIL))).willReturn(createdTestUser);

        mockMvc.perform(MockMvcRequestBuilders.get(TestData.BASE_ENDPOINT+ TestData.USER_DETAILS_ENDPOINT, TestData.FILTER_BY_EMAIL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(TestData.EXPECTED_TEST_GET_USER_DETAILS));
    }

    @Test
    public void givenUser_whenAddUser_thenStatue200() throws Exception {
        UsersDto userData = TestData.createUserDataDto();

        given(userService.addUserData(userData)).willReturn(userData);

        mockMvc.perform(MockMvcRequestBuilders.post(TestData.BASE_ENDPOINT+ TestData.ADD_USER_ENDPOINT)
                        .content(TestData.VALID_ADD_USER_DETAILS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
