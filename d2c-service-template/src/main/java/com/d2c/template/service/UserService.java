package com.d2c.template.service;


import com.d2c.template.data.entity.User;

public interface UserService {

  User findByUserId(String userId);

}
