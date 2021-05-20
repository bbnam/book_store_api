package com.example.demo;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("updateBook")
public class UpdateBook {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Messger UpdateBook(Book book) throws SQLException, ClassNotFoundException {
        Connection conn = MySQLConnUtils.getMySQL();
        String select_sql = "Select * from book where name = ?";
        ArrayList<String> name = new ArrayList<>();
        try (PreparedStatement ptsm = conn.prepareStatement(select_sql)){
            ptsm.setString(1, book.getName());
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                name.add(rs.getString("name"));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (name.size() == 0){
            Messger mess = new Messger("Sách thay đổi thông tin không tồn tại.");
            return mess;
        }
        else{
            String update = "UPDATE `mydb`.`book` SET `publisher` = ?, `amount` = ? WHERE (`name` = ?);";
            try(PreparedStatement ptsm = conn.prepareStatement(update)) {
                ptsm.setString(1, book.getPublisher());
                ptsm.setInt(2, book.getAmount());
                ptsm.setString(3, book.getName());
                ptsm.executeUpdate();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            conn.close();
            Messger mess = new Messger("Cập nhập thành công!");
            return mess;
        }

    }
}
