package com.leon.community.controller;

import com.leon.community.dto.AccessTokenDTO;
import com.leon.community.dto.UserDTO;
import com.leon.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author chengliang
 * @Date 2020/7/25 13:49
 */

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    /**
     * 注入配置参数
     */
    @Value(value = "${github.client_id}")
    private String clientId;

    @Value(value = "${github.client_secret}")
    private String clientSecret;

    @Value(value = "${github.redirect_uri}")
    private String redirectUri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken=githubProvider.getAccessToken(accessTokenDTO);

        UserDTO user = githubProvider.getUser(accessToken);


        System.out.println(user.getName());
        return "index";
    }
}
