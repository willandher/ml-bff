package com.ml.bff.user.service;

import com.ml.bff.config.PropertiesConfig;
import com.ml.bff.shared.WebClientService;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;

public class UserServiceImpl implements UserService{
    private final WebClientService webClientService;
    private final Integer port;
    private final String host;


    public UserServiceImpl(WebClientService webClientService, PropertiesConfig propertiesConfig) {
        this.webClientService = webClientService;
        this.port = propertiesConfig.service().user().port();
        this.host = propertiesConfig.service().user().host();
    }


    @Override
    public Future<Buffer> getUser(Integer id){
        return webClientService.requestGetCircuitBreaker(this.port,this.host,"/api/v1/users/"+id,false);
    }

    @Override
    public Future<Buffer> getUserRestrictions(Integer id) {
        return webClientService.requestGetCircuitBreaker(this.port,this.host,"/api/v1/users/"+id+"/restrictions",false);
    }

    @Override
    public Future<Buffer> getUserLevel(String id) {
        return webClientService.requestGetCircuitBreaker(this.port,this.host,"/api/v1/users/levels/"+id,false);
    }

    @Override
    public Future<Buffer> getPurcharseByUser(Integer id, Integer limit, Integer offset) {
        return webClientService.requestGetCircuitBreaker(this.port,
                this.host,
                "/api/v1/users/"+id+"/purcharses/?limit="+limit+"&offset="+offset
                ,false);
    }
}
