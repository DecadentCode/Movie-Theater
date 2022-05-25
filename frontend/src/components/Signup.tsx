import { FormEvent } from "react";
import "./SignUp.css";

const SignUp = () => {
  const submitHandler = (e: FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    if (formData.get("password") !== formData.get("confirmPassword")) {
      return;
    } else {
      const user = {
        email: formData.get("email") as string,
        password: formData.get("password") as string,
        confirmPassword: formData.get("confirmPassword") as string,
      };
    }
  };

  return (
    <div className="SignUp">
      <h1>Sign Up</h1>
      <form onSubmit={submitHandler}>
        <label htmlFor="email">Email</label>
        <input type="email" id="email" required />
        <label htmlFor="password">Password</label>
        <input type="password" id="password" required />
        <label htmlFor="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" required />
        <button type="submit">Sign Up</button>
      </form>
    </div>
  );
};

export default SignUp;
