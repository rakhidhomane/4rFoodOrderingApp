import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useNavigate } from "react-router-dom";

const AdminLogin = () => {
  let navigate = useNavigate();
  const [user, setUser] = useState({});
  const [errorMessage, setErrorMessage] = useState('')

  const [loginObject, setLoginObject] = useState({
    userEmail: "",
    userPassword: "",
  });


  const adminLogin = () => {
    axios
      .post("http://localhost:8080/login", loginObject)
      .then((response) => {
        setLoginObject(response.data);
        setErrorMessage('')
        // setAlertModalOpen(true)
        getUserByEmail()
        sessionStorage.setItem("admin",JSON.stringify(response.data))
        console.log(response.data)
        navigate("/restaurant");
        window.location.reload()
      })
      .catch((error) => {
        console.log(error.response.data)
        setErrorMessage(error.response.data)
      });
  };

  const getUserByEmail = () => {
    axios
      .get(
        "http://localhost:8080/food-delivery/getUserByEmail/" +
        loginObject.userEmail
      )
      .then((response) => {
        setUser(response.data);
      });
      sessionStorage.setItem("userEmail", loginObject.userEmail);
  }

 

  return (

    <div className="container register">
      <div className="row">
        <center>
        <div className="col-md-3 register-left">
         
        
        </div>
        <div className="col-md-9 register-right">


          <div className="tab-content row login-background" id="myTabContent">
          <div>

          <div className="tab-content row" id="myTabContent">
            <div

              className="tab-pane fade"
              id="profile"
              role="tabpanel"
              aria-labelledby="profile-tab"
            >
              <h3 className="register-heading">Login as Admin</h3>
              <div className="row register-form">
                <div className="col-md-8">
                  <div className="form-group">
                    <input
                      type="email"
                      className="form-control"
                      placeholder="Your Email *"
                    />
                  </div>
                  <div className="form-group">
                    <input
                      type="password"
                      className="form-control"
                      placeholder="Password *"
                    />
                  </div>
                </div>
                <div className="col-md-6">
                  <button type="submit" className="btnRegister" >
                    Login
                  </button>
                </div>
              </div>
            </div>
            <div
              className="tab-pane fade active show"
              id="profile"
              role="tabpanel"
              aria-labelledby="profile-tab"
            >
              <h3 className="register-heading">Login as Admin</h3>
              <div className="row register-form">
                <div className="col-md-8">
                  <div className="form-group">
                    <input
                      type="email"
                      className="form-control"
                      placeholder="Your Email *"
                      onChange={(event) =>
                        setLoginObject({
                          ...loginObject,
                          userEmail: event.target.value,
                        })
                      }
                    />
                  </div>
                  <br/>
                  <div className="form-group">
                    <input
                      type="password"
                      className="form-control"
                      placeholder="Password *"
                      onChange={(event) =>
                        setLoginObject({
                          ...loginObject,
                          userPassword: event.target.value,
                        })
                      }
                    />
                  </div>
                </div>
                <div className="col-md-6">
                  <button type="submit" className="btnRegister" onClick={() => adminLogin()}>
                    Login
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    </center>
    </div>
    </div>
  )
}

export default AdminLogin
