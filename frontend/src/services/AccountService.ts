import axios from "axios";

export const getProfile = () => {
  return axios.get(`http://localhost:8080/api/user`).then((response) => {
    console.log(response.data);
    return response.data;
  });
};
