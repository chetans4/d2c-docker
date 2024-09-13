package com.d2c.notification.configuration;

import com.d2c.notification.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Extending functionality of UriComponentsBuilder to prevent adding query params passed with null
 * values.
 */
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class D2CURIBuilder {

    @NonNull
    private final UriComponentsBuilder uriComponentsBuilder;

    public static D2CURIBuilder fromHttpUrl(String httpUrl) {
        return new D2CURIBuilder(UriComponentsBuilder.fromHttpUrl(httpUrl));
    }

    public D2CURIBuilder queryParam(String name, String value) {
        if (Strings.isNotBlank(value)) {
            this.uriComponentsBuilder.queryParam(name, value);
        } else {
            log.trace("Empty value : {}", name);
        }
        return this;
    }

    public D2CURIBuilder queryParams(MultiValueMap<String, String> paramMap) {
        if (Objects.nonNull(paramMap) && !paramMap.isEmpty()) {
            for (Entry<String, List<String>> entry : paramMap.entrySet()) {
                if (Objects.nonNull(entry)) {
                    if (Objects.nonNull(entry.getValue()) && !entry.getValue().isEmpty()) {
                        this.uriComponentsBuilder.queryParam(entry.getKey(), entry.getValue());
                    } else {
                        log.trace("Empty value : {}", entry.getKey());
                    }
                }
            }
        }
        return this;
    }

    public D2CURIBuilder queryParam(String name, Object value) {
        if (Objects.nonNull(value)) {
            this.uriComponentsBuilder.queryParam(name, value);
        } else {
            log.trace("Empty value : {}", name);
        }
        return this;
    }

    public String buildInString() {
        return this.uriComponentsBuilder.build().toUriString();
    }

    public URI build() {
        try {
            return new URI(this.uriComponentsBuilder.build().toUriString());
        } catch (URISyntaxException e) {
            log.error("Problem while building URI", e);
            throw new ApplicationException("Problem while building URI");
        }
    }

}
