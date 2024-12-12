package com.testtask.usersservicev1.service.impl;

import com.testtask.usersservicev1.database.entity.User;
import com.testtask.usersservicev1.database.fetcher.UserFetcher;
import com.testtask.usersservicev1.dto.UserDto;
import com.testtask.usersservicev1.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class UserServiceImpl implements UserService {

    private final List<UserFetcher> userFetchers;

    public UserServiceImpl(List<UserFetcher> userFetchers) {
        this.userFetchers = userFetchers;
    }

    @Override
    public List<UserDto> collectAllUsers(String query) {
        Map<String, String> filters = parseQuery(query);

        List<CompletableFuture<List<User>>> futures = userFetchers.stream()
                .map(fetcher -> CompletableFuture.supplyAsync(() -> fetcher.fetch(filters)))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        return futures.stream()
                .map(CompletableFuture::join)
                .flatMap(List::stream)
                .map(user -> new UserDto(user.getId(), user.getUsername(), user.getName(), user.getSurname()))
                .toList();
    }

    private Map<String, String> parseQuery(String query) {
        Map<String, String> filters = new HashMap<>();

        if (query != null && !query.isEmpty()) {
            String[] queryParams = query.split("&");
            for (String param : queryParams) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    filters.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return filters;
    }
}
