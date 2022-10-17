package com.ml.bff.purcharse.api;

import com.ml.bff.purcharse.dto.BffPurcharseInfo;
import com.ml.bff.purcharse.service.BffPurcharseService;
import com.ml.bff.user.service.UserService;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.UniHelper;
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
public class PurcharseController {

    @Inject
    BffPurcharseService bffPurcharseService;

    private static final String API_ML_GET_USER_PURCHARSE =   "/users/{id}/purcharses";



    @Path(API_ML_GET_USER_PURCHARSE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Buffer> getPurcharseByUserId(
            @PathParam("id") Integer id,
            @DefaultValue("10") @QueryParam("limit") Integer limit,
            @DefaultValue("0") @QueryParam("offset") Integer offset){
        return UniHelper.toUni(bffPurcharseService.getPurcharseByUserId(id,limit,offset))
                .onItem().transform(a -> a);
    }

}
