package ca.nautarius.url.shortener.service;

import ca.nautarius.url.shortener.adapter.UrlShortenerAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@AllArgsConstructor
public class UrlShortenerService {

    private static final int URL_MAX_SIZE = 10;
    private final UrlShortenerAdapter urlShortenerAdapter;

    private static final String PROTOCOL_DELIMITER = "://";

    public String shortenerUrl(URL url) {
        String key = generateShorterUrl(url);
        return rebuildUrl(url, key);
    }

    private String generateShorterUrl(URL url) {
        String fullPath = url.getFile();
        String key = fullPath.length() <= URL_MAX_SIZE ? fullPath : fullPath.substring(0, 10);
        int count = urlShortenerAdapter.getCount(key) + 1;
        if (count != 1) {
            String countAsString = String.valueOf(count);
            int lengthToRemove = 10 - countAsString.length();
            key = key.substring(0, lengthToRemove) + countAsString;
        }
        return key;
    }

    private String rebuildUrl(URL url, String key) {
        return url.getProtocol() + PROTOCOL_DELIMITER + url.getHost() + key;
    }
}
