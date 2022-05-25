import { FormEvent } from "react";
import "./SignIn.css";

const SignIn = () => {
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    const user = {
      email: formData.get("email") as string,
      password: formData.get("password") as string,
      confirmPassword: formData.get("confirmPassword") as string,
    };
  };

  return (
    <div className="SignIn">
      <h1>Sign In</h1>
      <form onSubmit={submitHandler}>
        <label htmlFor="email">Email</label>
        <input type="email" id="email" required />
        <label htmlFor="password">Password</label>
        <input type="password" id="password" required />
        <button type="submit">Sign In</button>
      </form>

      <h2>Don't have an account?</h2>
      <button
        id="SignUp"
        onClick={() => {
          window.location.href = "/signup";
        }}
      >
        Sign Up
      </button>
    </div>
  );
};

export default SignIn;
