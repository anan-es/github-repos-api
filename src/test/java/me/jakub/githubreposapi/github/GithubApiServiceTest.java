package me.jakub.githubreposapi.github;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import me.jakub.githubreposapi.github.model.Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import wiremock.org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@WireMockTest(httpPort = 8090)
public class GithubApiServiceTest {

    @Autowired
    GithubApiService githubApiService;

    @Test
    void singleRepositoryMapping() throws IOException {
        String username = "github";
        String responseBody =
                IOUtils.resourceToString("/github_service_test/repositories-response.json", UTF_8);

        stubFor(get(urlPathEqualTo("/users/github/repos"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(responseBody)));

        Repository fetchedRepository = githubApiService.getUserRepositories(username).blockFirst();

        assertNotNull(fetchedRepository);
        assertEquals(".github", fetchedRepository.getName());
        assertEquals(false, fetchedRepository.getFork());
        assertEquals("github", fetchedRepository.getOwner().getLogin());
    }

    @Test
    void multipleRepositoriesMapping() throws IOException {
        String username = "github";
        String responseBody =
                IOUtils.resourceToString("/github_service_test/repositories-multiple-response.json", UTF_8);

        stubFor(get(urlPathEqualTo("/users/github/repos"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(responseBody)));

        List<Repository> fetchedRepository = githubApiService.getUserRepositories(username).collectList().block();

        assertNotNull(fetchedRepository);
        assertEquals(10, fetchedRepository.size());

        assertNotNull(fetchedRepository.getFirst());
        assertEquals(".github", fetchedRepository.getFirst().getName());
        assertEquals(false, fetchedRepository.getFirst().getFork());
        assertEquals("github", fetchedRepository.getFirst().getOwner().getLogin());
    }

    @Test
    void noRepositories() throws IOException {
        String username = "github";
        String responseBody =
                IOUtils.resourceToString("/github_service_test/repositories-empty-response.json", UTF_8);

        stubFor(get(urlPathEqualTo("/users/github/repos"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(responseBody)));

        assertNull(githubApiService.getUserRepositories(username).blockFirst());
    }
}
