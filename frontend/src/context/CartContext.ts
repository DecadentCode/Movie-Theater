import { createContext } from "react";
import CartItem from "../models/CartItem";

export interface CartContext {
  cart: CartItem[];
  cartTotal: number;
  cartTotalItems: number;
  isLoggedIn: boolean;
  addToCart: (item: CartItem) => void;
  removeFromCart: (item: CartItem) => void;
  clearCart: () => void;
  setIsLoggedIn: (isLoggedIn: boolean) => void;
}

const defaultValue: CartContext = {
  cart: [],
  cartTotal: 0,
  cartTotalItems: 0,
  isLoggedIn: false,
  addToCart: () => {},
  removeFromCart: () => {},
  clearCart: () => {},
  setIsLoggedIn: () => {},
};

const cartContext = createContext(defaultValue);
export default cartContext;
