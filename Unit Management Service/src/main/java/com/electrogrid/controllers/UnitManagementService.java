package com.electrogrid.controllers;

import com.electrogrid.entity.Unit;
import com.electrogrid.util.DBUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created By Rashani
 * Date: 4/18/2022
 */

@Path("")
public class UnitManagementService {

    private static final ObjectMapper mapper = new ObjectMapper();

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

        Response response = null;

        try {
            Connection conn = DBUtil.connect();

            if (conn != null) {
                String query = "INSERT INTO unit_prices(minUnitValue, maxUnitValue, unitPrice, insertDate, modifiedDate) " +
                        "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement statement = conn.prepareStatement(query);

                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currTime = sdf.format(date);

                statement.setInt(1, unit.getMinUnitValue());
                statement.setInt(2, unit.getMaxUnitValue());
                statement.setFloat(3, unit.getUnitPrice());
                statement.setString(4, currTime);
                statement.setString(5, currTime);

                System.out.println(statement);

                statement.execute();

                ObjectNode json = mapper.createObjectNode();
                json.put("status", "OK");
                response = Response.status(Response.Status.CREATED)
                        .entity(json).build();

            }
        } catch (ClassNotFoundException | SQLException e) {
            ObjectNode json = mapper.createObjectNode();
            json.put("error", e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(json).build();
        }

        return response;

    }



}
