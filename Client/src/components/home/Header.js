import React from "react";
import { Link } from "react-router-dom";
import "../../css/Header.css";
import { useNavigate, useLocation } from "react-router-dom";
import logo from "../../images/food-delivery.jpg";
import { useSelector } from "react-redux";
import { Badge, IconButton } from "@mui/material";
import ShoppingCartCheckoutIcon from '@mui/icons-material/ShoppingCartCheckout';
import FavoriteIcon from '@mui/icons-material/Favorite';
import Button from "@mui/material/Button";


const Header = () => {
  const cart = useSelector((state) => state);
  const navigate = useNavigate();

  let userLoggedIn;
  sessionStorage.getItem("userEmail") !== null
    ? (userLoggedIn = (
      <>
        <Link className="nav-link" to={"/user-profile"}>
          My Profile
        </Link>
        
         <Link className="nav-link" to={"/my-current-orders"}>
          my Orders
        </Link>
        <Link className="nav-link " to={"/cart"}>
          <Badge badgeContent={cart.length} color="secondary" sx={{ mr: 1 }}>
            <ShoppingCartCheckoutIcon />
          </Badge>
          Cart
          </Link>
        <Link className="nav-link" to={"/logout"}>
          Logout
          </Link>
      </>
    ))
    : (userLoggedIn = (
      <div className="dropdown">
        <button
          className="btn btn-primary dropdown-toggle"
          id="dropdownMenuButton1"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          Login
        </button>
        <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
          <li>
            {/* <Link to={"/user-login"}> */}
            <a className="dropdown-item" href="user-login">
              User
            </a>
            {/* </Link> */}
          </li>
          <li>
            <a className="dropdown-item" href="restaurant-login">
            Restaurant
            </a>
          </li>
          <li>
            <a className="dropdown-item" href="admin-login">
            Admin 
            </a>
          </li>
        </ul>
      </div>

    ));

    const goToSearch=()=>{
      navigate("/filter")
    }
  
  return (
    <div className="header container-fluid">
      <div className="float-left">
        
        <span className="h1">
          <Link className="heading m-3 mt-4" to={"/"}>
            4R Food App
          </Link>
        </span>
        
        <nav className="navbar nav nav-pills flex-wrap">
          <Link data-toggle="pill" className="nav-link active-tab" to={"/"}>
            Home
          </Link>
          <Link className="nav-link " to={"/contactus"}>
            Contact Us
          </Link>
          <Link className="nav-link active-tab" to={"/aboutus"}>
            About Us
          </Link>
          
          {userLoggedIn}
          <Button variant="contained" className="search"  onClick={()=>goToSearch()}>Search</Button>
        </nav>
        <br />
        <br />
      </div>
    </div>
  );
};

export default Header;
