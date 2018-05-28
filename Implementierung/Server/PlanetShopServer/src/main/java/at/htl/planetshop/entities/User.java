package at.htl.planetshop.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "USER_DATA")
@NamedQueries({
        @NamedQuery(name = "User.deleteById", query = "delete from User u where u.id = :id")
})
public class User implements Serializable {
    @Id
    private Long id;

    //region Constructors

    public User(Long id){
        this.id = id;
    }

    public User(){
        this.id = null;
    }

    //endregion

    //region Getter Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //endregion
}
