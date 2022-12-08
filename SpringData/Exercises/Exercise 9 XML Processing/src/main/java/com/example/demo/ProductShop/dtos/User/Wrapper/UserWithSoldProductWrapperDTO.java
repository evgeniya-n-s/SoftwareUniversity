package com.example.demo.ProductShop.dtos.User.Wrapper;

import com.example.demo.ProductShop.dtos.User.UserWithSoldProductXMLDTO;
import com.example.demo.ProductShop.dtos.User.UsersWithSoldProductDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductWrapperDTO {

    @XmlElement(name = "user")
   private List<UserWithSoldProductXMLDTO> users;

    public UserWithSoldProductWrapperDTO() {
    }

    public UserWithSoldProductWrapperDTO(List<UserWithSoldProductXMLDTO> users) {
        this.users = users;
    }

    public List<UserWithSoldProductXMLDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProductXMLDTO> users) {
        this.users = users;
    }

    public UserWithSoldProductWrapperDTO ofListOfUsersWithSoldProductsDto(List<UsersWithSoldProductDTO> input) {
        users = UsersWithSoldProductDTO.toUsersWithSoldProductsDto(input);
        return this;
    }
}
