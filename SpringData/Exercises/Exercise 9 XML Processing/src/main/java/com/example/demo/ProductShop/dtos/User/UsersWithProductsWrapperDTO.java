package com.example.demo.ProductShop.dtos.User;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithProductsWrapperDTO {
    @XmlAttribute(name = "count")
    private Integer usersCount;
    @XmlElement(name = "user")
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
