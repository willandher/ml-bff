package com.ml.bff.shared;

import com.ml.bff.config.PropertiesConfig;

public abstract class WebService {

    private  WebClientService webClientService;
    private  Integer port;
    private  String host;

    public WebService(WebClientService webClientService, PropertiesConfig.Service.BaseService baseService) {
        this.webClientService = webClientService;
        this.port = baseService.port();
        this.host = baseService.host();
    }

    public WebClientService getWebClientService() {
        return webClientService;
    }

    public void setWebClientService(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
