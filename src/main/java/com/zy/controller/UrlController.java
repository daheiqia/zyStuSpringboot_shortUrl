package com.zy.controller;

import com.alibaba.fastjson.JSONObject;
import com.zy.pojo.UrlCreate;
import com.zy.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * @Description:
 * @Author:小黑洽
 * @Date：2021/7/8
 */

@Controller
public class UrlController {
    @Autowired
    private UrlService urlService;

    @RequestMapping("/")
    public String index(){
        return "index";
    }
    /**
     * 功能描述: <br>生成短链接
     * @Param:
     * @Return:
     * @Author: 小黑洽
     * @Date:
     */

    @RequestMapping("/create")
    @ResponseBody
    public String creatShortUrl(String longUrl, String viewPwd, HttpServletRequest request){
        JSONObject json = new JSONObject();
        String[] split = longUrl.split("\n\r");
        StringBuffer msg = new StringBuffer();
        for(int i = 0;i < split.length; i++){
            UrlCreate urlCreate = new UrlCreate();
            if(!split[i].contains("https://") && !split[i].contains("http://")){
                split[i] = "http://" + split[i];
            }
            String shortUrlId = getStringRandom(6);
            urlCreate.setShortUrlId(shortUrlId);
            urlCreate.setUid(UUID.randomUUID().toString());
            urlCreate.setLongUrl(split[i]);
            urlCreate.setCreateTime(DateUtils.format(new Date(),"yyyy-MM-dd HH-mm-ss", Locale.SIMPLIFIED_CHINESE));
            urlCreate.setViewPwd(viewPwd);
            int flag = urlService.createShortUrl(urlCreate);
            String toUrl = "/";
           int serverPort = request.getServerPort();
           if(serverPort == 80 || serverPort==443){
               toUrl = request.getScheme()+"://" + request.getServerName();
           }else{
               toUrl = request.getScheme() +"://" +request.getServerName()+":"+serverPort;
           }

           if(flag>0){
               msg.append(toUrl+"/"+shortUrlId+"<br>");
           }
        }
        json.put("shortUrl",msg);
        return json.toJSONString();
    }


    /**
     * 功能描述: <br>用短 链接访问
     * @Param:
     * @Return:
     * @Author: 小黑洽
     * @Date:
     */
    @RequestMapping(value = "/{shortUrlId}")
    public void view(@PathVariable("shortUrlId") String shortUrlId,  HttpServletResponse response,HttpServletRequest request)throws ServletException, IOException {
        UrlCreate urlCreate = urlService.findByShortUrlId(shortUrlId);

        if (urlCreate != null){
            if(urlCreate.getViewPwd() != null && !"".equals(urlCreate.getViewPwd())){
                request.setAttribute("shortUrlId",shortUrlId);
                request.getRequestDispatcher("/viewPwd").forward(request,response);
            }else{
                urlService.updateShortUrl(shortUrlId);
                response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                response.setHeader("Location",urlCreate.getLongUrl());
            }
        }else{
            request.getRequestDispatcher("/noPage").forward(request,response);
        }
    }

    /**
     * 功能描述: <br> 404页面
     * @Param:
     * @Return:
     * @Author: 小黑洽
     * @Date:
     */

    @RequestMapping("/noPage")
    public String noPage(){
        return "noPage";
    }

    /**
     * 功能描述: <br>短链加密
     * @Param:
     * @Return:
     * @Author: 小黑洽
     * @Date:
     */

    @RequestMapping("/viewPwd")
    public String viewPwd(HttpServletRequest request, Model model) {
        String shortUrlId = request.getAttribute("shortUrlId").toString();
        model.addAttribute("shortUrlId", shortUrlId);
        return "viewPwd";
    }
    /**
     * 功能描述: <br>验证密码
     * @Param:
     * @Return:
     * @Author: 小黑洽
     * @Date:
     */
    @RequestMapping("/VerifyPwd")
    @ResponseBody
    public String VerifyPwd(String viewPwd, String shortUrlId) {
        UrlCreate urlEntity = urlService.findByPwd(viewPwd, shortUrlId);

        JSONObject jsonObject = new JSONObject();
        if (urlEntity != null) {
            urlService.updateShortUrl(shortUrlId);
            jsonObject.put("longUrl", urlEntity.getLongUrl());
            jsonObject.put("flag", true);
        } else {
            jsonObject.put("flag", false);
        }
        return jsonObject.toJSONString();
    }

    /**
     * 功能描述: <br>短链接产生
     * @Param:
     * @Return:
     * @Author: 小黑洽
     * @Date:
     */

    private String getStringRandom(int length){
        String val = "";
        Random random = new Random();

        for(int i=0;i<length;i++){
            String cahrOrNum = random.nextInt(2)%2 == 0 ? "char" : "num";

            if("char".equalsIgnoreCase(cahrOrNum)){
                int textNUm = random.nextInt(2)%2 == 0 ? 65:97;
                val += (char)(random.nextInt(26) + textNUm);
            } else if ("num".equalsIgnoreCase(cahrOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
