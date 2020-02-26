package com.muzi.weshop.common;

import com.muzi.weshop.common.contants.Constants;
import com.muzi.weshop.model.LoginModel;
import com.muzi.weshop.model.PersonnelModel;
import com.orhanobut.hawk.Hawk;

public class LoginManager {
    
    private static final class Singleton{
        public static final LoginManager INSTANCE = new LoginManager();
    }
    
    public static LoginManager getInstance(){
        return Singleton.INSTANCE;
    }
    
    public void setLoginInfo(LoginModel model){
        //hawk数据存储框架，类似于SharePrefrense
        Hawk.put(Constants.LOGIN_INFO , model);
    }
    
    public LoginModel getLoginInfo(){
        LoginModel model = Hawk.get(Constants.LOGIN_INFO);
        return model;
    }

    public String getAccount(){
        if(getLoginInfo() == null || getLoginInfo().getPersonnel() == null){
            return "";
        }

        return getLoginInfo().getPersonnel().getAccount();
    }

    public PersonnelModel getPersonal(){
        return getLoginInfo().getPersonnel();
    }
    
    public int getPersonalId(){
        return getLoginInfo().getPersonnel().getId();
    }
    
    public void clearLoginInfo(){
        Hawk.delete(Constants.LOGIN_INFO);
    }
}
