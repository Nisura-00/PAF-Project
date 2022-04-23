package com.electrogrid.controllers;

import com.electrogrid.dao.UnitManagementServiceDAO;
import com.electrogrid.entity.Unit;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created By Rashani
 * Date: 4/18/2022
 */

@Path("")
public class UnitManagementService {

    private final UnitManagementServiceDAO unitMgmt = new UnitManagementServiceDAO();

//    @Path("/test")
//    @GET
//    public Response testConnection() {
//        try {
//            Connection connection = DBUtil.connect();
//
//            if (connection != null) {
//                return Response.status(200).build();
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            return Response.status(500).build();
//        }
//
//        return Response.status(404).build();
//    }

    @Path("/insert")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUnit(Unit unit) {
        return unitMgmt.insertUnit(unit);
    }

    @Path("/delete/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUnit(@PathParam("id") int id) {
        return unitMgmt.deleteUnit(id);
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unit> listUnits() {
        return unitMgmt.listUnits();
    }

    @Path("/units/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Unit getUnitDetailsById(@PathParam("id") int id) {
        return unitMgmt.getUnitDetailsById(id);
    }

    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUnit(Unit unit) {
        return unitMgmt.updateUnit(unit);
    }

}
