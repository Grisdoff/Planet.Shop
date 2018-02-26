package at.htl.planetshop.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_DATA")
@NamedQueries({
        @NamedQuery(name = "User.getWithUsernameAndPassword", query = "select u from User u where u.username = :username and u.password = :password"),
        @NamedQuery(name = "User.deleteById", query = "delete from User u where u.id = :id")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    //region Constructors
    public User(){

    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    //endregion

    //region Getter Setter
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}
