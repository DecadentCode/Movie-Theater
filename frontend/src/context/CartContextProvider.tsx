import { ReactNode, useEffect, useState } from "react";
import CartItem from "../models/CartItem";
import { getAllPurchases, getProfile } from "../services/AccountService";
import CartContext from "./CartContext";

const CartContextProvider = ({ children }: { children: ReactNode }) => {
  const [cart, setCart] = useState<CartItem[]>([]);
  const [oldCart, setOldCart] = useState<CartItem[]>([]);
  const [cartTotal, setCartTotal] = useState(0);
  const [cartTotalItems, setCartTotalItems] = useState(0);
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [purchaseHistory, setPurchaseHistory] = useState<string[]>([]);

  // const addToCart = (cartItem: CartItem) => {
  //   let alreadyInCart: boolean = false;
  //   cart.find((itemInCart, index) => {
  //     if (itemInCart.id === cartItem.id) {
  //       cart[index].qty += cartItem.qty;
  //       cart[index].qty <= 0 && cart.splice(index, 1);
  //       alreadyInCart = true;
  //       return true;
  //     }
  //     console.log("find looped");
  //   });
  //   !alreadyInCart && setCart([...cart, cartItem]);
  //   setCartTotal(cart.reduce((acc, curr) => acc + curr.qty * curr.price, 0));
  //   setCartTotalItems(cart.length);
  // };

  const addToCart = (cartItem: CartItem) => {
    const newCart = [...cart, cartItem];
    setCart(newCart);
    setCartTotal(newCart.reduce((acc, curr) => acc + curr.price, 0));
    setCartTotalItems(newCart.reduce((acc, curr) => acc + curr.qty, 0));
  };

  const removeFromCart = (cartItem: CartItem) => {
    const newCart = cart.filter((item) => item.id !== cartItem.id);
    setCart(newCart);
    setCartTotal(newCart.reduce((acc, curr) => acc + curr.qty * curr.price, 0));
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
    getAllPurchases().then((purchases) => {
      setPurchaseHistory(purchases);
      console.log(purchases);
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
      }}
    >
      {children}
    </CartContext.Provider>
  );
};

export default CartContextProvider;
