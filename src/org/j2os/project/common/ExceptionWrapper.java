package org.j2os.project.common;

import org.json.simple.JSONObject;

import java.sql.SQLException;

public class ExceptionWrapper extends Exception{
    private ExceptionWrapper(){}
    public static String getMassage(Exception e){
        JSONObject jsonObject=new JSONObject();
        e.printStackTrace();

        if(e instanceof NotFindException){
            jsonObject.put("code","100");
            jsonObject.put("msg","mojod nist");
            return jsonObject.toJSONString();

        }else if(e instanceof SQLException){
            jsonObject.put("code", "101");
            jsonObject.put("msg", "KHATA DATABASE");
            System.out.println(e.getMessage());
            return jsonObject.toJSONString();
        }else{
            jsonObject.put("code", "200");
            jsonObject.put("msg", "NASHENAKHTE");
            return jsonObject.toJSONString();
        }
    }
}
