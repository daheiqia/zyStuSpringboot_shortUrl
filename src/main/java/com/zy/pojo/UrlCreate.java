package com.zy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author:小黑洽
 * @Date：2021/7/8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlCreate {
    private String uid;
    private String shortUrlId;
    private String longUrl;
    private String createTime;
    private String lastView;
    private String viewPwd;
}
