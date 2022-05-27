import { ReactNode, useEffect, useState } from "react";
import CartItem from "../models/CartItem";
import CartContext from "./CartContext";

const CartContextProvider = ({ children }: { children: ReactNode }) => {
  const [cart, setCart] = useState<CartItem[]>([]);
  const [cartTotal, setCartTotal] = useState(0);
  const [cartTotalItems, setCartTotalItems] = useState(0);

  const addToCart = (cartItem: CartItem) => {
    let alreadyInCart: boolean = false;
    cart.find((itemInCart, index) => {
      if (itemInCart.id === cartItem.id) {
        cart[index].qty += cartItem.qty;
        cart[index].qty <= 0 && cart.splice(index, 1);
        alreadyInCart = true;
        return true;
      }
      console.log("find looped");
    });
    !alreadyInCart && setCart([...cart, cartItem]);
    setCartTotal(cart.reduce((acc, curr) => acc + curr.qty * curr.price, 0));
    setCartTotalItems(cart.length);
  };

  const removeFromCart = (cartItem: CartItem) => {
    const newCart = cart.filter((item) => item.id !== cartItem.id);
    setCart(newCart);
    setCartTotal(newCart.reduce((acc, curr) => acc + curr.qty * curr.price, 0));
    setCartTotalItems(newCart.length);
  };

  const clearCart = () => {
    setCart([]);
    setCartTotal(0);
    setCartTotalItems(0);
  };

  useEffect(() => {
    console.log("new item added to cart");
    console.log(cart);
  }, [cart]);

  return (
    <CartContext.Provider
      value={{
        cart,
        cartTotal,
        cartTotalItems,
        addToCart,
        removeFromCart,
        clearCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};

export default CartContextProvider;
