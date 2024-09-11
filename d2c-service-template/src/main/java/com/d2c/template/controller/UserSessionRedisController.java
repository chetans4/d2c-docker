package com.d2c.template.controller;

import com.d2c.template.dto.request.UserSessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Basic Redis CURD Controller.
 */
@RestController
@RequestMapping("/api/user-session/v1")
public class UserSessionRedisController {

  private static final String REDIS_INDEX_KEY = "USER_SESSION";

  @Autowired
  RedisTemplate<String, Object> redisTemplate;

  @GetMapping("/{id}")
  public Object getUserSession(@PathVariable("id") Integer id) {
    return redisTemplate.opsForHash().get(REDIS_INDEX_KEY, String.valueOf(id));
  }

  @GetMapping
  public ResponseEntity<Object> getAllUserSession() {
    return new ResponseEntity<>(redisTemplate.opsForHash().entries(REDIS_INDEX_KEY), HttpStatus.OK);
  }

  @PostMapping
  public String addUserSession(@RequestBody UserSessionDto userSession) {
    redisTemplate.opsForHash()
        .put(REDIS_INDEX_KEY, String.valueOf(userSession.getId()), userSession.toString());
    return "user session saved successfully!";
  }

  @PutMapping
  public String updateUserSession(@RequestBody UserSessionDto userSession) {
    redisTemplate.opsForHash()
        .put(REDIS_INDEX_KEY, String.valueOf(userSession.getId()), userSession.toString());
    return "user session updated successfully!";
  }

  @DeleteMapping("/{id}")
  public String deleteUserSession(@PathVariable Integer id) {
    redisTemplate.opsForHash().delete(REDIS_INDEX_KEY, String.valueOf(id));
    return "user session deleted successfully!";
  }

}
