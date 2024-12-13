package com.testtask.usersservicev1.database.fetcher;

import com.testtask.usersservicev1.database.entity.User;

import java.util.List;
import java.util.Map;

public interface UserFetcher {

    List<User> fetch(Map<String, String> filters);
}
