package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao
{
    ShoppingCart getByUserId(int userId);
    // add additional method signatures here
    void addProductToCart(int userId);
    void updateCart(int cartId, int productId, int quantity);
    void clearCart(int sartId);
}
