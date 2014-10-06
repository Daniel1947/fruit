package com.bean.ws;

import com.bean.model.data.item.ItemVO;
import com.bean.model.data.user.UserVO;
import com.bean.model.proxy.UserProxy;
import com.bean.util.ServiceUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Daniel on 14-8-18.
 */
@Path("/user")
public class UserService {
    @Path("/all")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() throws Exception{
        List<UserVO> allUser = UserProxy.getAllUsers();
        Map<String, List> result = new HashMap<String, List>();
        result.put("users", allUser);
        return ServiceUtil.buildResponse(result);
    }

    @Path("/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorizedUser(@PathParam("id")String id) throws Exception{
        UserVO user = UserProxy.getUserById(Long.valueOf(id));
        return ServiceUtil.buildResponse(user);
    }

    @Path("/account={account}&pwd={pwd}")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorizedUser(@PathParam("account")String account, @PathParam("pwd")String pwd) throws Exception{
        UserVO userAuthorized = UserProxy.getAuthorizedUser(account, pwd);
        return ServiceUtil.buildResponse(userAuthorized);
    }

    @Path("/{id}/listings")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserListings(@PathParam("id")String id) throws Exception{
        UserVO user = UserProxy.getUserById(Long.valueOf(id));
        List<ItemVO> listings = UserProxy.getUserListings(user);
        return ServiceUtil.buildResponse(listings);
    }

    @Path("/all/listings")
    @GET
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserListings() throws Exception{
        Map<Long, List> result = new HashMap<Long, List>();
        List<UserVO> allUser = UserProxy.getAllUsers();
        for (int i = 0; i < allUser.size(); i ++) {
            UserVO user = allUser.get(i);
            List<ItemVO> listings = UserProxy.getUserListings(user);
            result.put(user.getUserId(), listings);
        }
        return ServiceUtil.buildResponse(result);
    }
}
