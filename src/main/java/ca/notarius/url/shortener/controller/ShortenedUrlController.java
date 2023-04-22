package ca.notarius.url.shortener.controller;

import ca.notarius.url.shortener.service.ShortenerUrlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@AllArgsConstructor
@Slf4j
public class ShortenedUrlController {

    private final ShortenerUrlService shortenerUrlService;

    @PostMapping("/shortenedUrl")
    public ResponseEntity<String> shortenedUrl(@RequestBody String url) {
        ResponseEntity<String> responseEntity;
        try {
            URL formattedUrl = new URL(url);
            String shortenedUrl = shortenerUrlService.createAndSaveUrl(formattedUrl);
            responseEntity = ResponseEntity.ok(shortenedUrl);
        } catch (MalformedURLException e) {
            log.warn("Url {} was malformed. {}", url, e.getMessage());
            responseEntity = ResponseEntity.badRequest().body("Url was malformed");
        }

        return responseEntity;
    }
}
