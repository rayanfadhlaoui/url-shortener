package ca.notarius.url.shortener.controller;

import ca.notarius.url.shortener.exceptions.InvalidPathException;
import ca.notarius.url.shortener.service.ShortenerUrlService;
import ca.notarius.url.shortener.stringifier.UrlStringifier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@AllArgsConstructor
@Slf4j
public class ShortenedUrlController {

    private final ShortenerUrlService shortenerUrlService;
    private final UrlStringifier urlStringifier;

    @PostMapping("/shortenedUrl")
    public @ResponseBody ResponseEntity<String> shortenedUrl(@RequestBody String url) {
        ResponseEntity<String> responseEntity;
        try {
            URL formattedUrl = getNormalUrl(url);
            String shortenedUrl = shortenerUrlService.createAndSaveUrl(formattedUrl);
            responseEntity = ResponseEntity.ok(shortenedUrl);
        } catch (MalformedURLException | InvalidPathException e) {
            log.warn("Url {} was malformed. {}", url, e.getMessage());
            responseEntity = ResponseEntity.badRequest().body("Url was malformed or path was missing");
        }

        return responseEntity;
    }

    @GetMapping("/fullUrl")
    public ResponseEntity<String> fullUrl(@RequestBody String shortenedUrl) {
        ResponseEntity<String> responseEntity;
        try {
            URL formattedUrl = getShortenedUrl(shortenedUrl);
            responseEntity = getFullUrlFromFormatted(formattedUrl);
        } catch (MalformedURLException | InvalidPathException e) {
            log.warn("Url {} was invalid. {}", shortenedUrl, e.getMessage());
            responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        }

        return responseEntity;
    }

    private URL getShortenedUrl(String shortenedUrl) throws MalformedURLException, InvalidPathException {
        URL formattedUrl = new URL(shortenedUrl);
        if (!getPathWithoutDash(formattedUrl).matches("[0-9]+")) {
            throw new InvalidPathException("Url was not shortened by this service.");
        }
        return formattedUrl;
    }

    private URL getNormalUrl(String url) throws MalformedURLException, InvalidPathException {
        URL formattedUrl = new URL(url);
        if (getPathWithoutDash(formattedUrl).isEmpty()) {
            throw new InvalidPathException("Url path is missing.");
        }
        return formattedUrl;
    }

    private ResponseEntity<String> getFullUrlFromFormatted(URL formattedUrl) {
        String domain = urlStringifier.getDomain(formattedUrl);
        return shortenerUrlService.getFullUrl(domain, getPathWithoutDash(formattedUrl))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private String getPathWithoutDash(URL formattedUrl) {
        return formattedUrl.getPath().substring(1);
    }
}
