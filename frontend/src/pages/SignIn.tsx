import { FormEvent } from "react";
import { Link } from "react-router-dom";
import "./SignIn.css";

const SignIn = () => {
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    const user = {
      email: formData.get("email") as string,
      password: formData.get("password") as string,
    };
    console.log(user);
  };

  return (
    <div className="SignIn">
      <form onSubmit={submitHandler}>
        <h1>Sign In</h1>
        <label htmlFor="email">Email</label>
        <input type="email" id="email" required />
        <label htmlFor="password">Password</label>
        <input type="password" id="password" required />
        <button type="submit">Sign In</button>
      </form>

      <h2>Don't have an account?</h2>
      <Link to="/signup" id="SignUp">
        Sign Up
      </Link>
    </div>
  );
};

export default SignIn;
