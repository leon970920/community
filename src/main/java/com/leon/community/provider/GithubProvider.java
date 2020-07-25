package com.leon.community.provider;

import com.alibaba.fastjson.JSON;
import com.leon.community.dto.AccessTokenDTO;
import com.leon.community.dto.UserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @Author chengliang
 * @Date 2020/7/25 13:58
 */

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");//返回类型

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create( JSON.toJSONString(accessTokenDTO),mediaType);//将对象转换为json形式
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String accessToken= response.body().string();
            accessToken=accessToken.split("&")[0].split("=")[1];
//            System.out.println(accessToken);
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserDTO getUser(String accessToken){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            //通过accessToken获得的user信息
            String user= response.body().string();

            //通过fastJson将string转化为对象
            UserDTO userDTO = JSON.parseObject(user, UserDTO.class);
            return userDTO;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}