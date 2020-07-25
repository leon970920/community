package com.leon.community.controller;

import com.leon.community.dto.AccessTokenDTO;
import com.leon.community.dto.UserDTO;
import com.leon.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state){

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("61aef5a7582348c5beff");
        accessTokenDTO.setClient_secret("e6c7ec2d11dc5a2de310f958eda1183291b487a4");
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);

        String accessToken=githubProvider.getAccessToken(accessTokenDTO);

        UserDTO user = githubProvider.getUser(accessToken);


        System.out.println(user.getName());
        return "index";
    }
}
