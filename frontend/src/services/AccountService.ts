import axios from "axios";
import Profile from "../models/Profile";

const baseURL: string = process.env.REACT_APP_API_URL || "";

export const getProfile = (): Promise<Profile> => {
  return axios.get(`${baseURL}/api/user`).then((response) => {
    console.log(response.data);
    return response.data;
  });
};

export const getAllPurchases = () => {
  return axios.get(`${baseURL}/api/myfilms`).then((response) => {
    console.log(response.data);
    return response.data;
  });
};
