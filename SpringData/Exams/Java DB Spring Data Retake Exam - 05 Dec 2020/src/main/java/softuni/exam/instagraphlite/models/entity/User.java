package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name",nullable = false,unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    private Picture picture;

    @OneToMany(targetEntity = Post.class,mappedBy = "user",fetch = FetchType.EAGER)
    private List<Post> posts;


    public User() {
        this.posts=new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return String.format("User: %s\n" +
                "Post count: %d\n" +
                "==Post Details:\n" +
                "----Caption: %s\n" +
                "----Picture Size: \n",
                this.userName,
                this.posts.size(),
                this.posts.stream().map(Post::toString).collect(Collectors.joining("\n")));
    }
}
