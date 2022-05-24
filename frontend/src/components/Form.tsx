import "./Form.css";

const Form = () => {
  return (
    <form className='Form'>
        <div className="form-group">
            <label htmlFor="firstName">First Name</label>
            <input className="form-control" required type="text" id="firstName"/><br />
        </div><div className="form-group">
            <label htmlFor="firstName">Last Name</label>
            <input className="form-control" required type="text" id="lastName"/><br />
        </div><div className="form-group">
            <label htmlFor="occupation">Occupation</label>
            <input className="form-control" required type="text" id="occupation"/><br />
        </div><div className="form-group">
            <label htmlFor="birth">Date of Birth</label>
            <input className="form-control" required type="date" id="birth"/><br />
        </div>
        <button type="submit" className="btn btn-primary">Enter into our Charity Raffle</button>
    </form>
  )
}

export default Form