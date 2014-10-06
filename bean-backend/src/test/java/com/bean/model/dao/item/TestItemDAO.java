package com.bean.model.dao.item;

import com.bean.model.dao.user.UserDAO;
import com.bean.model.data.item.ItemVO;
import com.bean.model.data.user.UserVO;
import com.bean.util.FormatUtil;
import junit.framework.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by zundeng on 2014/8/26.
 */

@org.junit.runner.RunWith(org.junit.runners.JUnit4.class)
public class TestItemDAO {
    @Test
    public void createItem(){
        try{
            System.out.println("---------- Test Create Item ----------");
            ItemVO voIn = new ItemVO();
            /**
             * item_name` varchar(255) NOT NULL,
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
            voIn.setItemName("This is a test item");
            voIn.setItemPicture1("/user/pic/pic1.png");
            voIn.setItemDescription("Desc for test item");
            voIn.setItemUsed(0L);

            UserDAO userDao = new UserDAO();
            Long sellerId = null;
            List<UserVO> userList = userDao.getAllUsers();
            if (userList != null && userList.size() > 0){
                sellerId = userList.get(0).getUserId();
            }else{
                sellerId = 10000L;
            }
            voIn.setItemSellerId(sellerId);
            voIn.setItemMaxPrice(100.0D);
            voIn.setItemMinPrice(80.0D);
            voIn.setItemOnShelfTime(FormatUtil.formatTimeAsStringFromTimestamp(new Timestamp((new Date()).getTime())));
            voIn.setItemAucLength(3600L);
            voIn.setItemAddTime(FormatUtil.formatTimeAsStringFromTimestamp(new Timestamp((new Date()).getTime())));

            ItemDAO itemDao = new ItemDAO();
            Long itemId = itemDao.createItem(voIn);
            System.out.println("create new item successfully.");

            System.out.println("---------- Delete Test Item ----------");
            itemDao.removeItemById(itemId);
            System.out.println("remove item:" + itemId + " successfully.");
        }catch(Exception e){
            Assert.fail("Failed create item  @ItemDAO.createItem " + e.getMessage());
        }
    }
}
