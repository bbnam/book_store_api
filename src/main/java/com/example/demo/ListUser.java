package com.example.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Path("listUser")
public class ListUser {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListUser() throws Exception{
        ArrayList<Object> listUser = new ArrayList<>();
        Connection conn = MySQLConnUtils.getMySQL();
        String sql = "select * from user;";
        try (PreparedStatement pstm = conn.prepareStatement(sql)){
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                User u = new User(rs.getString("username") ,rs.getString("email"));
                listUser.add(u);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        conn.close();
        return Response.ok(listUser, MediaType.APPLICATION_JSON).build();
    }
}
