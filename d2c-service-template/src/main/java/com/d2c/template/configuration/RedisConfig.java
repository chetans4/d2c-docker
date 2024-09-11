package com.d2c.template.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Value("${redis.host.name:localhost}")
  private String hostName;

  @Value("${redis.host.port:6379}")
  private int hostPort;

  @Bean
  RedisConnectionFactory getRedisConnectionFactory() {
    var redisStandaloneConfiguration = new RedisStandaloneConfiguration(
        hostName, hostPort);
    redisStandaloneConfiguration.setDatabase(5);
    return new LettuceConnectionFactory(redisStandaloneConfiguration);
  }

  @Bean
  RedisTemplate<String, Object> getRedisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(getRedisConnectionFactory());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new StringRedisSerializer());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    return redisTemplate;
  }

}
