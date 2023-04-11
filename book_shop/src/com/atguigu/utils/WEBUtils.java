package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.math.BigDecimal;
import java.util.Map;


public class WEBUtils {
    public static <T> T copyParamToBean(Map value, T bean){

        try {
            /**
             * 把所有请求参数注入到bean对象中
             */
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    public static int parseInt(String strInt,int defualtValue){
        try {
            return  Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defualtValue;
    }


    public static BigDecimal parseBigDemical(String strInt, BigDecimal defualtValue){
        try {
            return new BigDecimal(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defualtValue;
    }

}
