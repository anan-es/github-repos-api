package me.jakub.githubreposapi.github;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import me.jakub.githubreposapi.GithubReposApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = GithubReposApiApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WireMockTest(httpPort = 8090)
public class ApiErrorsTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenNotFound_thenErrorMessage() throws Exception {
        stubFor(WireMock.get(WireMock.anyUrl()).willReturn(WireMock.aResponse().withStatus(404)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/github/repositories/github"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NOT_FOUND"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Not Found"));
    }

    @Test
    void givenError_thenReplicateErrorCode() throws Exception {
        stubFor(WireMock.get(WireMock.anyUrl()).willReturn(WireMock.aResponse().withStatus(500)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/github/repositories/github"))
                .andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").exists());
    }
}