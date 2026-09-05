package org.haven.havenapi.service;


import lombok.RequiredArgsConstructor;
import org.haven.havenapi.exception.ExternalApiException;
import org.haven.havenapi.model.Quote;
import org.haven.havenapi.repository.QuoteRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public Quote getTodayQuote() {
        LocalDate today = LocalDate.now();
        return quoteRepository.findByDate(today)
                .orElseGet(() -> fetchAndCache(today));
    }

    private Quote fetchAndCache(LocalDate date) {
        ZenQuoteResponse fetched = fetchFromZenQuotes();
        return quoteRepository.save(fetched.q(), fetched.a(), date);
    }

    private ZenQuoteResponse fetchFromZenQuotes() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://zenquotes.io/api/today"))
                .header("User-Agent", "Mozilla/5.0")
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ZenQuoteResponse[] results = mapper.readValue(response.body(), ZenQuoteResponse[].class);

            if (results.length == 0) {
                throw new ExternalApiException("ZenQuotes empty response", null);
            }
            return results[0];

        } catch (IOException | InterruptedException e) {
            throw new ExternalApiException("ZenQuotes call impossible", e);
        }
    }

    private record ZenQuoteResponse(String q, String a, String h) {
    }
}