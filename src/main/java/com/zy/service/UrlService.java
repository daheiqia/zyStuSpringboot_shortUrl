package com.zy.service;

import com.zy.pojo.UrlCreate;


public interface UrlService {

    int createShortUrl(UrlCreate urlCreate);

    UrlCreate findByShortUrlId(String shortUrl);

    void updateShortUrl(String shorlUrlId);


    UrlCreate findByPwd(String viewPwd,String shortUrlId);

}
