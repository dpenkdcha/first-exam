package com.scott.betaexam.integration;

import com.scott.betaexam.BetaExamApplication;
import com.scott.betaexam.dtos.UsersDto;
import com.scott.betaexam.services.UserService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static com.scott.betaexam.TestData.*;
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
        List<UsersDto> createdTestUser = createdTestUserDtoList();

        given(userService.getUserDetails(Optional.of(FILTER_BY_EMAIL))).willReturn(createdTestUser);

        mockMvc.perform(get(BASE_ENDPOINT+ USER_DETAILS_ENDPOINT, FILTER_BY_EMAIL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(EXPECTED_TEST_GET_USER_DETAILS));
    }

    @Test
    public void givenUser_whenAddUser_thenStatue200() throws Exception {
        UsersDto userData = createUserDataDto();

        given(userService.addUserData(userData)).willReturn(userData);

        mockMvc.perform(post(BASE_ENDPOINT+ ADD_USER_ENDPOINT)
                        .content(VALID_ADD_USER_DETAILS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
