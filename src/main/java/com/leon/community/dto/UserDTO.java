package com.leon.community.dto;

/**
 * @Author chengliang
 * @Date 2020/7/25 14:40
 */
public class UserDTO {
    private String name;
    private String bio;
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
