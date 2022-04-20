package com.electrogrid.dao;

import com.electrogrid.entity.Unit;
import com.electrogrid.util.DBUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created By Rashani
 * Date: 4/20/2022
 */
public class UnitManagementServiceDAO {

    private static final ObjectMapper mapper = new ObjectMapper();

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

    public Response deleteUnit(int id) {

        Response response = null;

        if (!recordExist(id)) {

            ObjectNode json = mapper.createObjectNode();
            json.put("error", "Record not found !!!");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(json).build();
        }

        try {

            Connection conn = DBUtil.connect();

            if (conn != null) {

                String query = "DELETE FROM unit_prices WHERE id = ?";

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();

                ObjectNode json = mapper.createObjectNode();
                json.put("success", "Record deleted");
                response = Response.status(Response.Status.OK)
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

    public List<Unit> listUnits() {

        List<Unit> unitList = new ArrayList<>();

        try {
            Connection conn = DBUtil.connect();

            if (conn != null) {

                String query = "SELECT * FROM unit_prices";

                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                Unit unit = null;

                while (rs.next()) {
                    unit = new Unit();

                    unit.setId(rs.getInt("id"));
                    unit.setMinUnitValue(rs.getInt("minUnitValue"));
                    unit.setMaxUnitValue(rs.getInt("maxUnitValue"));
                    unit.setUnitPrice(rs.getFloat("unitPrice"));
                    unit.setInsertDate(rs.getDate("insertDate"));
                    unit.setModifiedDate(rs.getDate("modifiedDate"));

                    unitList.add(unit);
                }

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return unitList;
    }

    public Response updateUnit(Unit unit) {

        Response response = null;

        if (!recordExist(unit.getId())) {
            ObjectNode json = mapper.createObjectNode();
            json.put("error", "Record not found");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(json).build();
        }

        try {
            Connection conn = DBUtil.connect();
            String query = "UPDATE unit_prices SET minUnitValue = ?," +
                    " maxUnitValue = ?," +
                    " unitPrice = ?," +
                    " modifiedDate = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, unit.getMinUnitValue());
            stmt.setInt(2, unit.getMaxUnitValue());
            stmt.setFloat(3, unit.getUnitPrice());

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currTime = sdf.format(date);

            stmt.setString(4,currTime);
            stmt.setInt(5, unit.getId());

            stmt.executeUpdate();

            ObjectNode json = mapper.createObjectNode();
            json.put("success", "Unit updated");

            return Response.status(Response.Status.OK)
                    .entity(json).build();

        } catch (ClassNotFoundException | SQLException e) {
            ObjectNode json = mapper.createObjectNode();
            json.put("error", e.getMessage());
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(json).build();
        }

        return response;
    }

    private boolean recordExist(int id) {

        boolean flag = false;

        try {

            Connection conn = DBUtil.connect();

            if (conn != null) {
                String query = "SELECT * FROM unit_prices WHERE id = ?";

                PreparedStatement stmt = conn.prepareStatement(query);

                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    flag = true;
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return flag;

    }

}
