package com.example.demo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.transform.Result;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Path("UserBook")
public class UserHasBook {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Messger UserBook(ArrayList<User> users) throws Exception {
        Connection conn = MySQLConnUtils.getMySQL();
        String sql = "select * from book where name = ?";
        String sql_u = "select * from user where username = ?;";

        int user_id = 1;
        for (int i = 0; i < users.size(); i++){
            for (int j = 0; j< users.get(i).getBook().size(); j++){

                try (PreparedStatement p1 = conn.prepareStatement(sql_u)){
                    p1.setString(1, users.get(i).getUsername());

                    ResultSet rs_u = p1.executeQuery();
                    while (rs_u.next()){
                    user_id = rs_u.getInt("id");
                    }

                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
//
                try (PreparedStatement pstm = conn.prepareStatement(sql)){

                    pstm.setString(1, users.get(i).getBook().get(j).getName());
                    ResultSet rs_1 = pstm.executeQuery();
                    while (rs_1.next()){
                        System.out.println(rs_1.getInt("amount"));
                        if (rs_1.getInt("amount") == 0){
                            Messger mess = new Messger("Số lượng sách trong thư viện không đủ");
                            return mess;
                        }
                        else {
                            int amount = rs_1.getInt("amount");
                            amount = amount - 1;
                            String update_sql = "UPDATE `mydb`.`book` SET `amount` = ? WHERE (`name` = ?);";
                            try (PreparedStatement p = conn.prepareStatement(update_sql)){
                                p.setInt(1, amount);
                                p.setString(2, rs_1.getString("name"));
                                p.executeUpdate();
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                            String sqlx = "INSERT INTO `mydb`.`user_has_book` (`user_id`, `book_id`, `time_borrowed`, `time_out`) VALUES (?, ?, ?, ?);";

                            try (PreparedStatement p2 = conn.prepareStatement(sqlx)){
                                p2.setInt(1, user_id);
                                p2.setInt(2, rs_1.getInt("id"));

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                                Calendar cal = Calendar.getInstance();
                                System.out.println("Current Date: "+sdf.format(cal.getTime()));
                                cal.add(Calendar.DAY_OF_MONTH, 7);
                                String newDate = sdf.format(cal.getTime());

                                p2.setString(3, sdf.format(cal.getTime()));
                                p2.setString(4, newDate);

                                p2.executeUpdate();
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }

                        }
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }

        Messger mess = new Messger("Mượn sách thành công!");
        return mess;
    }
}

