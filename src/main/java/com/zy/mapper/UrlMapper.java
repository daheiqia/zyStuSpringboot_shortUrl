package com.zy.mapper;

import com.zy.pojo.UrlCreate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlMapper {

    //增加对应短网址记录
    int createShortUrl(UrlCreate urlCreate);

    //按短网址信息进行查找，找到对应的原网址
    UrlCreate findByShortUrlId(String shortUrlId);

    //记录 每次使用短网址登录时间
    void updateShortUrl(@Param("shortUrlId") String shortUrlId,@Param("lastView") String lastView);


    //查询加密短网址
    UrlCreate findByPwd(@Param("viewPwd") String viewPwd,@Param("shortUrlId") String shortUrlId);
}
