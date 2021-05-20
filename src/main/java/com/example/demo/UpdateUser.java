package com.example.demo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Path("updateUser")
public class UpdateUser {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public int Update(User user) throws SQLException, ClassNotFoundException {

        Connection conn = MySQLConnUtils.getMySQL();
        String sql = "UPDATE `mydb`.`user` SET `email` = ?, `password` = ? WHERE (`username` = ?);";
        try (PreparedStatement stm = conn.prepareStatement(sql)){
            stm.setString(1, user.getEmail());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getUsername());
            stm.executeUpdate();
            conn.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return 1;
    }
}
