package com.bean.model.dao;

/**
 * Created by Daniel on 14-8-17.
 */
public class DBConstants {
    /**
     * query for users
     */
    public static final String GET_ALL_USERS="SELECT * FROM t_user";
    public static final String GET_USER_BY_ID="SELECT * FROM t_user WHERE user_id=?";
    public static final String GET_AUTHORIZED_USER="SELECT * FROM t_user WHERE user_login_name=? AND user_login_pwd=?";
    public static final String GET_USER_LISTINGS="SELECT * FROM t_item WHERE item_id IN (SELECT item_id FROM t_rel_item_user WHERE user_id=?)";

    /**
     * query for items
     *
     * item_id` int(11) NOT NULL AUTO_INCREMENT,
     `item_name` varchar(255) NOT NULL,
     `item_picture1` varchar(255) NOT NULL,
     `item_picture2` varchar(255) DEFAULT NULL,
     `item_picture3` varchar(255) DEFAULT NULL,
     `item_picture4` varchar(255) DEFAULT NULL,
     `item_picture5` varchar(255) DEFAULT NULL,
     `item_description` varchar(255) NOT NULL,
     `item_used` int(11) NOT NULL,
     `item_seller_id` int(11) NOT NULL,
     `item_max_price` double NOT NULL,
     `item_min_price` double NOT NULL,
     `item_onshelf_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     `item_auc_length` int(11) NOT NULL,
     `item_add_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
     */
    public static final String GET_ALL_ITEMS="select * from t_item";
    public static final String INSERT_ITEM = "insert into t_item(" +
            " item_name, item_picture1, item_picture2, item_picture3, item_picture4, item_picture5," +
            " item_description, item_used, item_seller_id, item_max_price, item_min_price, item_onshelf_time," +
            " item_auc_length, item_add_time)" +
            "values" +
            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static  final String DELETE_ITEM_BY_ID = "delete from t_item where item_id=?";

    /**
     * query for Categories
     */
    public static final String GET_ALL_CATEGORIES="select * from t_category";

}
