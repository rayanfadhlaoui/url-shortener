package ca.nautarius.url.shortener.service;

import ca.nautarius.url.shortener.model.ShortenedUrlEntity;
import ca.nautarius.url.shortener.stringifier.UrlStringifier;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class UrlShortenerService {
    private final ShortenedUrlCreatorService shortenedUrlCreatorService;
    private final ShortenedUrlSaverService urlSaverService;
    private final UrlStringifier urlStringifier;

    public String createAndSaveUrl(String url) {
        var urlEntity = shortenedUrlCreatorService.create(url);
        var newUrlEntity = urlSaverService.save(urlEntity);
        return urlStringifier.getShortUrl(newUrlEntity);
    }
}
