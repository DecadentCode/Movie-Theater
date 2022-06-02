import "./About.css";

const About = () => {
  return (
    <div className="About">
      <h1>About This Project</h1>
      <p>
        This is a simple app for a drive-in theatre. The BackEnd uses Spring
        Boot and MySQL. The FrontEnd uses React, TypeScript, and CSS.
      </p>
      <h2>Andrew Cyburt</h2>
      <img src="../assets/andrew.jpg" />
      <p>Andrew</p>

      <h2>Andrea Godoshian</h2>
      <img src="../assets/andrea.jpg" />
      <p>
        Andrea is a software developer with a passion for creating web
        applications.
      </p>

      <h2>Duncan Clotfelter</h2>
      <p>
        Duncan is a software developer with a passion for creating web
        applications.
      </p>
    </div>
  );
};

export default About;
