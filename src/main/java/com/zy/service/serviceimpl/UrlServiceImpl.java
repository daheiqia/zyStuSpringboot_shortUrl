package com.zy.service.serviceimpl;

import com.zy.mapper.UrlMapper;
import com.zy.pojo.UrlCreate;
import com.zy.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.util.Date;
import java.util.Locale;

/**
 * @Description:
 * @Author:小黑洽
 * @Date：2021/7/8
 */

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlMapper urlMapper;

    @Override
    public int createShortUrl(UrlCreate urlCreate) {
        return urlMapper.createShortUrl(urlCreate);
    }

    @Override
    public UrlCreate findByShortUrlId(String shortUrlId) {
        return urlMapper.findByShortUrlId(shortUrlId);
    }

    @Override
    public void updateShortUrl(String shorlUrlId) {
        String nowDate = DateUtils.format(new Date(),"yyyy-MM-dd HH-mm-ss", Locale.SIMPLIFIED_CHINESE);
        urlMapper.updateShortUrl(shorlUrlId,nowDate);

    }

    @Override
    public UrlCreate findByPwd(String viewPwd, String shortUrlId) {
        return urlMapper.findByPwd(viewPwd,shortUrlId);
    }
}
