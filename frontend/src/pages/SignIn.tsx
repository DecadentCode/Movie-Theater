import "./SignIn.css";

const SignIn = () => {
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
      <h1>Sign In</h1>

      <a href="/oauth2/authorization/github">
        <img
          id="LoginImage"
          alt="GitHub login button"
          src="https://cloud.githubusercontent.com/assets/194400/11214293/4e309bf2-8d38-11e5-8d46-b347b2bd242e.png"
        ></img>
      </a>
      {/* <button onClick={loginHandler} id="signIn">
        <img
          id="LoginImage"
          src="https://cloud.githubusercontent.com/assets/194400/11214293/4e309bf2-8d38-11e5-8d46-b347b2bd242e.png"
        ></img>
      </button> */}
    </div>
  );
};

export default SignIn;
