package com.ml.bff.user;
import com.ml.bff.user.service.UserService;
import com.ml.bff.user.service.UserServiceImpl;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.UniHelper;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.vertx.core.buffer.Buffer;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("/api/ml/v1/bff")
public class UserController {

    @Inject
    UserService userService;

    private static final String API_ML_GET_USER = "/users/{id}";
    private static final String API_ML_GET_USER_LEVEL = "/users/levels/{id}";
    private static final String API_ML_GET_USER_RESTRICTION = API_ML_GET_USER + "/restrictions";

    @Path(API_ML_GET_USER)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Buffer> apiGetUser() {
         return UniHelper.toUni(userService.getUser(null))
                .onItem().transform(a -> a);
    }

    @Path(API_ML_GET_USER_RESTRICTION)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Buffer> getUserRestrictions(@PathParam("id") Integer id) {
        return UniHelper.toUni(userService.getUserRestrictions(id))
                .onItem().transform(a -> a);
    }

    @Path(API_ML_GET_USER_LEVEL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Buffer> getLevel(@PathParam("id") String id) {
        return UniHelper.toUni(userService.getUserLevel(id))
                .onItem().transform(a -> a);
    }

}
