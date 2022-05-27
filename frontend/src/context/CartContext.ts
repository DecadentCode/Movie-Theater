import { createContext } from "react";
import CartItem from "../models/CartItem";

export interface CartContext {
  cart: CartItem[];
  cartTotal: number;
  cartTotalItems: number;
  addToCart: (item: CartItem) => void;
  removeFromCart: (item: CartItem) => void;
  clearCart: () => void;
}

const defaultValue: CartContext = {
  cart: [],
  cartTotal: 0,
  cartTotalItems: 0,
  addToCart: () => {},
  removeFromCart: () => {},
  clearCart: () => {},
};

const cartContext = createContext(defaultValue);
export default cartContext;
