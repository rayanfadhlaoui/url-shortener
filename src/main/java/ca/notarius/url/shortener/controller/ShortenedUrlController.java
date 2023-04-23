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
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@AllArgsConstructor
@Slf4j
public class ShortenedUrlController {

    private final ShortenerUrlService shortenerUrlService;
    private final UrlStringifier urlStringifier;

    /**
     * Shorten the given url and create a permanent mapping between the original and the shorter version.
     *
     * @param url A valid url (https://www.notarius.com/careers)
     * @return a shorter url with a path of a maximum size of 10.
     */
    @PostMapping("/shortenedUrl")
    public @ResponseBody ResponseEntity<String> shortenUrl(@RequestBody String url) {
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

    /**
     * Return the original url of a given shortened url.
     *
     * @param shortenedUrl the shortened url.
     * @return the original url.
     */
    @GetMapping("/originalUrl/{shortenedUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortenedUrl) {
        ResponseEntity<String> responseEntity;
        try {
            String decodedUrl = URLDecoder.decode(shortenedUrl, StandardCharsets.UTF_8);
            URL formattedUrl = getShortenedUrl(decodedUrl);
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
