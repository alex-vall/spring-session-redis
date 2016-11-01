package com.vall.gameserver.controller;

import com.vall.gameserver.model.GameServerObject;
import com.vall.gameserver.model.GameServerResponse;
import com.vall.gameserver.model.RequestStatus;
import com.vall.gameserver.service.GameRequestService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * Created by Vall on 01.11.2016.
 */


public class TestGameRequestController {

    private GameRequestController gameRequestController;

    @Mock
    private GameRequestService mockGameRequestService;

    private MockMvc mockMvc;


    @Before
    public void onSetUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        gameRequestController = new GameRequestController(mockGameRequestService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(gameRequestController).build();

    }

    @Test
    public void testDoPlayNoSession() throws Exception {

        when(mockGameRequestService.execute(Matchers.<GameServerObject>anyObject(), eq(1), eq(5)))
                .thenReturn(new GameServerResponse(RequestStatus.ok, new ArrayList<Integer>()));

        MvcResult result = mockMvc.perform(get("/doPlay")
                .param("counter", "1")
                .param("spinCount", "5"))
                .andExpect(status().isOk())
        .andReturn();

        System.out.println("Try to print response");
        System.out.println(result.getResponse().getStatus());
        System.out.println(result.getResponse().getContentAsString());
        System.out.println(result.getResponse().getHeaderNames());

        assertEquals("{\"requestStatus\":\"ok\",\"gameResult\":[]}", result.getResponse().getContentAsString());
    }


}
