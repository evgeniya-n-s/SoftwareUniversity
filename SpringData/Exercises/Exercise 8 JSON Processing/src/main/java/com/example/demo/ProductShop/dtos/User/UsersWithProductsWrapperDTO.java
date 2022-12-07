package com.example.demo.ProductShop.dtos.User;

import java.util.List;

public class UsersWithProductsWrapperDTO {
    private Integer usersCount;
    private List<UserWithProductDTO> users;

    public UsersWithProductsWrapperDTO() {
    }

    public UsersWithProductsWrapperDTO(List<UserWithProductDTO> users) {
        this.users = users;
        this.usersCount = users.size();
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserWithProductDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithProductDTO> users) {
        this.users = users;
    }
}
