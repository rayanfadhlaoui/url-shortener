package ca.notarius.url.shortener.service;

import ca.notarius.url.shortener.stringifier.UrlStringifier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@AllArgsConstructor
public class UrlShortenerService {
    private final ShortenedUrlCreatorService shortenedUrlCreatorService;
    private final ShortenedUrlSaverService urlSaverService;
    private final UrlStringifier urlStringifier;

    public String createAndSaveUrl(URL url) {
        var urlEntity = shortenedUrlCreatorService.create(url);
        var newUrlEntity = urlSaverService.save(urlEntity);
        return urlStringifier.getShortUrl(newUrlEntity);
    }
}
