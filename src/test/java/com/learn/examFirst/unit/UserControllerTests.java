package com.scott.betaexam.unit;

import com.scott.betaexam.controllers.UserController;
import com.scott.betaexam.dtos.UsersDto;
import com.scott.betaexam.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    public void givenFilter_whenGetUserDetails_thenReturnFilteredUser() throws Exception {
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
    public void givenValidUserDetails_whenAddUserDetails_thenReturnAddedUser() throws Exception {
        UsersDto userData = createUserDataDto();

        given(userService.addUserData(userData)).willReturn(userData);

        mockMvc.perform(post(BASE_ENDPOINT+ ADD_USER_ENDPOINT)
                        .content(VALID_ADD_USER_DETAILS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidUserDetails_whenAddUserDetails_thenReturnError() throws Exception {
        mockMvc.perform(post(BASE_ENDPOINT+ ADD_USER_ENDPOINT)
                        .content(INVALID_ADD_USER_DETAILS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(MISSING_FIELD_TEST));
    }

    @ParameterizedTest
    @ValueSource(strings = {INVALID_ADDRESS_ADD_USER_DETAILS, INVALID_CITY_ADD_USER_DETAILS, INVALID_STATE_ADD_USER_DETAILS})
    public void givenInvalidAddressDetails_whenAddUserDetails_thenReturnError(String input) throws Exception {
        mockMvc.perform(post(BASE_ENDPOINT+ ADD_USER_ENDPOINT)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(MISSING_ADDRESS_FIELD_TEST));
    }

}
