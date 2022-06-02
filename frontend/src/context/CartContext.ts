import { createContext } from "react";
import CartItem from "../models/CartItem";

export interface CartContext {
  cart: CartItem[];
  oldCart: CartItem[];
  cartTotal: number;
  cartTotalItems: number;
  isLoggedIn: boolean;
  purchaseHistory: string[];
  addToCart: (item: CartItem) => void;
  removeFromCart: (item: CartItem) => void;
  clearCart: () => void;
  setIsLoggedIn: (isLoggedIn: boolean) => void;
  setPurchaseHistory: (purchaseHistory: string[]) => void;
}

const defaultValue: CartContext = {
  cart: [],
  oldCart: [],
  cartTotal: 0,
  cartTotalItems: 0,
  isLoggedIn: false,
  purchaseHistory: [],
  addToCart: () => {},
  removeFromCart: () => {},
  clearCart: () => {},
  setIsLoggedIn: () => {},
  setPurchaseHistory: () => {},
};

const cartContext = createContext(defaultValue);
export default cartContext;
