package at.htl.planetshop.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SHOPPING_CART")
@NamedQueries({
        @NamedQuery(name = "ShoppingCart.DeleteByIds", query = "delete from ShoppingCart c where c.userId = :usId and c.productId = :prodId"),
        @NamedQuery(name = "ShoppingCart.GetByIds", query = "select s from ShoppingCart s where s.productId = :prodId and s.userId = :usId"),
        @NamedQuery(name = "ShoppingCart.deleteProductsFromUser", query = "delete from ShoppingCart s where s.userId = :id")
})
public class ShoppingCart implements Serializable {
    @Id
    private Long productId;
    @Id
    private Long userId;

    private int amount = 1;

    //region Constructor
    public ShoppingCart(){

    }

    public ShoppingCart(Long productId, Long userId){
        this.productId = productId;
        this.userId = userId;
    }
    //endregion

    //region Getter Setter

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    //endregion
}
