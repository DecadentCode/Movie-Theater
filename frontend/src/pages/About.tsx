import "./About.css";
import andrew from "../assets/andrew.jpg";

const About = () => {
  return (
    <div className="About">
      <h1>About</h1>
      <p>This is a simple app for a drive-in theatre.</p>
      <h2>Andrew Cyburt</h2>
      <img src={andrew} />
      <p>
        Andrew is a software developer with a passion for creating web
        applications.
      </p>
    </div>
  );
};

export default About;
