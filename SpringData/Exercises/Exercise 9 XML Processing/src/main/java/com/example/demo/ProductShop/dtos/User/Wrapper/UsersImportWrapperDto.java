package com.example.demo.ProductShop.dtos.User.Wrapper;

import com.example.demo.ProductShop.dtos.User.UserImportDTO;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportWrapperDto {

    @XmlElement(name = "user")
    private List<UserImportDTO> users;

    public UsersImportWrapperDto() {
    }

    public List<UserImportDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserImportDTO> users) {
        this.users = users;
    }
}