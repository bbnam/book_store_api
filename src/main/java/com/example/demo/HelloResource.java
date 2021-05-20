package com.example.demo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


@Path("signin")
public class HelloResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public int hello(User user) throws SQLException, ClassNotFoundException {
        Connection con = MySQLConnUtils.getMySQL();


        String sql = "INSERT INTO `mydb`.`user`(username , password, email) " + "VALUES(?,?,?)";
       try( PreparedStatement pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.executeUpdate();
            con.close();
        }
       catch (SQLException e){
           System.out.println(e.getMessage());
       }
        return 1;
    }


}
