package com.bean.model.proxy;

import com.bean.model.data.user.UserVO;
import junit.framework.Assert;
import org.junit.Test;

import javax.naming.InitialContext;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Daniel on 14-8-17.
 */
@org.junit.runner.RunWith(org.junit.runners.JUnit4.class)
public class TestUserProxy {
    private static final Logger logger = Logger.getLogger(TestUserProxy.class.getName());
    private static InitialContext initialContext;

    @Test
    public void getAllUsers(){
        try{
            List<UserVO> allUser = UserProxy.getAllUsers();
            System.out.println("---------- Test Get All Users ----------");
            for(UserVO vo : allUser){
                print(vo);
            }
        }catch(Exception e){
            Assert.fail("Failed get all users @UserProxy.getAllUsers " + e.getMessage());
        }

    }

    private void print(UserVO vo){
        System.out.println("UserVO.userId:" + vo.getUserId());
        System.out.println("UserVO.userLoginName:" + vo.getUserLoginName());
        System.out.println("UserVO.userDName:" + vo.getUserDname());
    }
}
