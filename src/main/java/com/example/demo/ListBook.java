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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("ListBook")
public class ListBook {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListBook() throws Exception{

        Connection con = MySQLConnUtils.getMySQL();
        String sql = "Select * from book";

        List<Object> listBook = new ArrayList<>();
        try(PreparedStatement pstm = con.prepareStatement(sql)){
            ResultSet a = pstm.executeQuery();
            while (a.next()){
                Book b = new Book(a.getString("name"), a.getString("publisher"), a.getInt("amount"));
                listBook.add(b);
                System.out.println(listBook);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
//        System.out.println(listBook);
        return Response.ok(listBook, MediaType.APPLICATION_JSON).build();
    }
}
