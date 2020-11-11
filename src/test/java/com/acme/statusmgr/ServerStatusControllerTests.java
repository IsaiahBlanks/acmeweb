/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.statusmgr;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ServerStatusControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/server/status")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up"));
    }

    @Test
    public void paramGreetingShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/server/status").param("name", "RebYid"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by RebYid"));
    }

    @Test
    public void missingDetailsListShouldReturnBadRequestErrorMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(is("Required List parameter 'details' is not present in request")));
    }

    @Test
    public void operationsDetailShouldReturnOperatingStatusMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "operations"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Anonymous"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally"));
    }

    @Test
    public void operationsAndExtensionDetailsShouldReturnOperationAndExtensionMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "operations")
                .param("details", "extensions"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6]"));
    }

    @Test
    public void operationsExtensionAndMemoryDetailsShouldReturnOperationExtensionAndMemoryMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "operations")
                .param("details", "extensions")
                .param("details", "memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));
    }

    @Test
    public void namedOperationsExtensionAndMemoryDetailsShouldReturnNameAndOperationExtensionAndMemoryMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "operations")
                .param("details", "extensions")
                .param("details", "memory")
                .param("name", "Noach"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));
    }

    @Test
    public void namedOperationsAndMemoryDetailsShouldReturnNameAndOperationAndMemoryMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "operations")
                .param("details", "memory")
                .param("name", "Noach"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is operating normally, and its memory is running low"));
    }

    @Test
    public void namedExtensionsAndMemoryDetailsShouldReturnNameAndExtensionsAndMemoryMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "extensions")
                .param("details", "memory")
                .param("name", "Noach"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));
    }

    @Test
    public void nameFirstExtensionsAndMemoryDetailsShouldReturnNameAndExtensionsAndMemoryMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("name", "Noach")
                .param("details", "extensions")
                .param("details", "memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.contentHeader").value("Server Status requested by Noach"))
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));
    }

    @Test
    public void repeatedDetailParamsReturnRepeatedAdditionsToMessage() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "memory")
                .param("details", "operations")
                .param("details", "extensions")
                .param("details", "memory"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.statusDesc").value("Server is up, and its memory is running low, and is operating normally, and is using these extensions - [Hypervisor, Kubernetes, RAID-6], and its memory is running low"));
    }

    @Test
    public void invalidDetailParamReturnsBadRequest() throws Exception {
        this.mockMvc.perform(get("/server/status/detailed")
                .param("details", "memory")
                .param("details", "operations")
                .param("details", "junkERROR"))
                .andDo(print()).andExpect(status().isBadRequest())
                .andExpect(status().reason(is("Invalid details option: junkERROR")));
    }
}
