import { Link } from "react-router-dom";
import "./SignIn.css";

const SignIn = () => {
  // const { setIsLoggedIn } = useContext(CartContext);
  // const navigate = useNavigate();
  // const submitHandler = (e: FormEvent) => {
  //   e.preventDefault();
  //   const formData = new FormData(e.target as HTMLFormElement);
  //   const user = {
  //     email: formData.get("email") as string,
  //     password: formData.get("password") as string,
  //   };
  //   console.log(user);
  // };

  // const loginHandler = () => {
  //   setIsLoggedIn(true);
  //   navigate("/");
  // };

  return (
    <div className="SignIn">
      <a href="/oauth2/authorization/github">
        <img
          id="LoginImage"
          src="https://cloud.githubusercontent.com/assets/194400/11214293/4e309bf2-8d38-11e5-8d46-b347b2bd242e.png"
        ></img>
      </a>
      {/* <button onClick={loginHandler} id="signIn">
        <img
          id="LoginImage"
          src="https://cloud.githubusercontent.com/assets/194400/11214293/4e309bf2-8d38-11e5-8d46-b347b2bd242e.png"
        ></img>
      </button> */}
      {/* <form onSubmit={submitHandler}>
        <h1>Sign In</h1>
        <label htmlFor="email">Email</label>
        <input type="email" id="email" required />
        <label htmlFor="password">Password</label>
        <input type="password" id="password" required />
        <button type="submit">Sign In</button>
      </form> */}

      <h2>Don't have an account?</h2>
      <Link to="/signup" id="SignUp">
        Sign Up
      </Link>
    </div>
  );
};

export default SignIn;
