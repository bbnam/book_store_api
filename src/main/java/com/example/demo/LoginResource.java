package com.example.demo;



import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedHashMap;

@Path("login")
public class LoginResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(User user){
        Messger mess = new Messger("Đăng nhập thành công");

        LinkedHashMap<String, String> hs = new LinkedHashMap<>();
        hs.put("name", user.getUsername());
        hs.put("mess", mess.getMess());

        return Response.ok(hs, MediaType.APPLICATION_JSON).build();

    }
}

