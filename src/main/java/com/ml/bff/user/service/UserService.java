package com.ml.bff.user.service;

import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;

public interface UserService {
    Future<Buffer> getUser(Integer id);

    Future<Buffer> getUserRestrictions(Integer id);

    Future<Buffer> getUserLevel(String id);

    Future<Buffer> getPurcharseByUser(Integer id, Integer limit, Integer offset);

}
