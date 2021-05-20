package com.example.demo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("addbook")
public class AddBook {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public int add(ArrayList<Book> book) throws SQLException, ClassNotFoundException {
        Connection con = MySQLConnUtils.getMySQL();
        String sql = "INSERT INTO `mydb`.`book` (`name`, `publisher`, `amount`) VALUES (?, ?, ?);";

        for (int i = 0; i < book.size(); i++){
            try (PreparedStatement pstm = con.prepareStatement(sql)){
                pstm.setString(1, book.get(i).getName());
                pstm.setString(2, book.get(i).getPublisher());
                pstm.setInt(3, book.get(i).getAmount());
                pstm.executeUpdate();
            }
        }
        con.close();
        return 1;
    }
}
