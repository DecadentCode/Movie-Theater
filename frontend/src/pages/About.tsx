import "./About.css";

const About = () => {
  return (
    <div className="About">
      <h1>About This Project</h1>
      <p>
        This is a simple app for a drive-in theatre. The BackEnd uses Spring
        Boot and MySQL. The FrontEnd uses React, TypeScript, and CSS.
      </p>

      <div className="AboutPerson">
        <h2>Andrew Cyburt</h2>
        {/* <img src="../assets/andrew.jpg" /> */}
        <p>
          Andrew was the Team Lead. He managed the repo, organized the workflow,
          and handled most of the FrontEnd.
          <br />
          <br />
          Andrew has a passion for coding, technology, and people.
        </p>
      </div>

      <div className="AboutPerson">
        <h2>Andrea Godoshian</h2>
        {/* <img src={`./frontend/src/assets/andrea.jpg`} alt="Team member Andrea" /> */}
        <p>
          Hello, world! In December 2021, I graduated with a 3.5 GPA from
          Eastern Michigan University. During my final semester with EMU, I saw
          a SheCodes advertisement on YouTube... I was hooked! After trying a
          few different languages (ex. JavaScript, Python), I found success with
          Java.
        </p>
      </div>

      <div className="AboutPerson">
        <h2>Duncan Clotfelter</h2>
        <p>
          I'm Duncan Clotfelter, a programmer turned English teacher turned
          programmer. I love languages of all forms!
        </p>
      </div>
    </div>
  );
};

export default About;
