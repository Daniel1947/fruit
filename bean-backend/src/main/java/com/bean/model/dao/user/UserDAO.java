package com.bean.model.dao.user;

import com.bean.model.dao.DBConstants;
import com.bean.model.data.item.ItemVO;
import com.bean.model.data.user.UserVO;
import com.bean.model.persistance.jdbc.item.Item;
import com.bean.model.persistance.jdbc.user.User;
import com.bean.util.DBConnection;
import com.bean.util.FormatUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 14-8-17.
 */
public class UserDAO {
    public List<UserVO> getAllUsers() throws Exception{
        Connection con = DBConnection.getConnection();
        List<UserVO> allUsers = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(DBConstants.GET_ALL_USERS);

            while (rs.next()) {
                if (allUsers == null) {
                    allUsers = new ArrayList<UserVO>();
                }
                User user = new User();
                user.setUserDname(rs.getString("user_dname"));
                user.setUserLoginName(rs.getString("user_login_name"));
                user.setUserLoginPwd(rs.getString("user_login_pwd"));
                user.setUserMobile(rs.getString("user_mobile"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserId(Long.valueOf(rs.getInt("user_id")));
                UserVO vo = (UserVO)user.getState();
                allUsers.add(vo);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally{
            DBConnection.closeConnection(con);
        }
        return allUsers;
    }

    public UserVO getUserById(Long id) throws Exception{
        Connection con = DBConnection.getConnection();
        UserVO userVO = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();

            PreparedStatement ps = con.prepareStatement(DBConstants.GET_USER_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserDname(rs.getString("user_dname"));
                user.setUserLoginName(rs.getString("user_login_name"));
                user.setUserLoginPwd(rs.getString("user_login_pwd"));
                user.setUserMobile(rs.getString("user_mobile"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserId(Long.valueOf(rs.getInt("user_id")));
                userVO = (UserVO)user.getState();
            }
        }catch (SQLException se){
            throw new Exception("Error occurred in UserDAO.getAuthorizedUserWithAccountAndPwd():" + se);
        }finally {
            DBConnection.closeConnection(con);
        }
        return userVO;
    }

    public UserVO getAuthorizedUserWithAccountAndPwd(String account, String password) throws Exception{
        Connection con = DBConnection.getConnection();
        UserVO userAuthorized = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();

            PreparedStatement ps = con.prepareStatement(DBConstants.GET_AUTHORIZED_USER);
            ps.setString(1, account);
            ps.setString(2, password);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserDname(rs.getString("user_dname"));
                user.setUserLoginName(rs.getString("user_login_name"));
                user.setUserLoginPwd(rs.getString("user_login_pwd"));
                user.setUserMobile(rs.getString("user_mobile"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserId(Long.valueOf(rs.getInt("user_id")));
                userAuthorized = (UserVO)user.getState();
            }
        }catch (SQLException se){
            throw new Exception("Error occurred in UserDAO.getAuthorizedUserWithAccountAndPwd():" + se);
        }finally {
            DBConnection.closeConnection(con);
        }
        return userAuthorized;
    }

    public List<ItemVO> getUserListings(UserVO userVO) throws Exception {
        Connection con = DBConnection.getConnection();
        Statement statement = null;
        ResultSet rs = null;
        List<ItemVO> listings = null;
        try {
            statement = con.createStatement();

            PreparedStatement ps = con.prepareStatement(DBConstants.GET_USER_LISTINGS);
            ps.setLong(1, userVO.getUserId());
            rs = ps.executeQuery();

            while (rs.next()) {
                if (listings == null) {
                    listings = new ArrayList<ItemVO>();
                }
                Item item = new Item();
                item.setItemId(Long.valueOf(rs.getInt("item_id")));
                item.setItemName(rs.getString("item_name"));
                item.setItemPicture1(rs.getString("item_picture1"));
                item.setItemPicture2(rs.getString("item_picture2"));
                item.setItemPicture3(rs.getString("item_picture3"));
                item.setItemPicture4(rs.getString("item_picture4"));
                item.setItemPicture5(rs.getString("item_picture5"));
                item.setItemDescription(rs.getString("item_description"));
                item.setItemUsed(Long.valueOf(rs.getInt("item_used")));
                item.setItemSellerId(Long.valueOf(rs.getInt("item_seller_id")));
                item.setItemMaxPrice(rs.getDouble("item_max_price"));
                item.setItemMinPrice(rs.getDouble("item_min_price"));
                item.setItemOnShelfTime(FormatUtil.formatTimeAsStringFromTimestamp(rs.getTimestamp("item_onshelf_time")));
                item.setItemAucLength(Long.valueOf(rs.getInt("item_auc_length")));
                item.setItemAddTime(FormatUtil.formatTimeAsStringFromTimestamp(rs.getTimestamp("item_add_time")));

                ItemVO vo = (ItemVO)item.getState();
                listings.add(vo);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally{
            DBConnection.closeConnection(con);
        }
        return listings;
    }
}

