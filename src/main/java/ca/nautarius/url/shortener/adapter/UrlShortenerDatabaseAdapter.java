package ca.nautarius.url.shortener.adapter;

import org.springframework.stereotype.Component;

@Component
public class UrlShortenerDatabaseAdapter implements UrlShortenerAdapter {
    @Override
    public Integer getCount(String key) {
        return null;
    }
}
