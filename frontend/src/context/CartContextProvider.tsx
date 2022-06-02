import { ReactNode, useEffect, useState } from "react";
import CartItem from "../models/CartItem";
import { getProfile } from "../services/AccountService";
import CartContext from "./CartContext";

const CartContextProvider = ({ children }: { children: ReactNode }) => {
  const [cart, setCart] = useState<CartItem[]>([]);
  const [oldCart, setOldCart] = useState<CartItem[]>([]);
  const [cartTotal, setCartTotal] = useState(0);
  const [cartTotalItems, setCartTotalItems] = useState(0);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [purchaseHistory, setPurchaseHistory] = useState<string[]>([]);

  const addToCart = (cartItem: CartItem, qty: number) => {
    for (let i = 0; i < qty; i++) {
      setCart((prevCart) => [...prevCart, cartItem]);
      setCartTotal((prevTotal) => prevTotal + cartItem.price);
      setCartTotalItems((prevTotal) => prevTotal + 1);
    }
  };

  const removeFromCart = (cartItem: CartItem) => {
    const newCart = cart.filter((item) => item.id !== cartItem.id);
    setCart(newCart);
    setCartTotal(newCart.reduce((acc, curr) => acc * curr.price, 0));
    setCartTotalItems(newCart.length);
  };

  const clearCart = () => {
    setOldCart(cart);
    setCart([]);
    setCartTotal(0);
    setCartTotalItems(0);
  };

  useEffect(() => {
    console.log("new item added to cart");
    console.log(cart);
    getProfile().then((profile) => {
      setIsLoggedIn(profile !== null);
    });
  }, [cart]);

  return (
    <CartContext.Provider
      value={{
        cart,
        oldCart,
        cartTotal,
        cartTotalItems,
        isLoggedIn,
        purchaseHistory,
        addToCart,
        removeFromCart,
        clearCart,
        setIsLoggedIn,
        setPurchaseHistory,
      }}
    >
      {children}
    </CartContext.Provider>
  );
};

export default CartContextProvider;
