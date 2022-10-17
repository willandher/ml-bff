package com.ml.bff.config;
import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "ml.config")
public interface PropertiesConfig  {
Service service();

interface Service {

  User user();

  Transaction transaction();



  interface User extends BaseService{ }

  interface Transaction extends BaseService { }

  interface BaseService  {
    String host();
    Integer port();
  }
}

}
