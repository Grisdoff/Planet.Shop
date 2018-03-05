package at.htl.planetshop.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "USER_DATA")
@NamedQueries({
        @NamedQuery(name = "User.getByUsernameAndPassword", query = "select u from User u where u.username = :username and u.password = :password"),
        @NamedQuery(name = "User.deleteByUsername", query = "delete from User u where u.username = :username"),
        @NamedQuery(name = "User.getByUsername", query = "select u from User u where u.username = :username")
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 6, max = 12, message = "Username too long or too short")
    private String username;

    @Min(value = 6)
    @Pattern(regexp = "")
    private String password;
//username zwischen 6 und 12, password muss 1x groß, 1x, 1x Zahl mindestens
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
