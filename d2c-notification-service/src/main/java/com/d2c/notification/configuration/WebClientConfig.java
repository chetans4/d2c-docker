package com.d2c.notification.configuration;

import com.d2c.notification.exception.handler.D2CCustomExchangeFilterFunction;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                                .wiretap("reactor.netty.http.client.HttpClient",
                                        LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL)))
                .filter(new D2CCustomExchangeFilterFunction())
                .build();
    }

}
