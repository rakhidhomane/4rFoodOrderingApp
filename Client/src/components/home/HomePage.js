import React, { useState, useEffect } from "react";
import "../../css/HomePage.css";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import IconButton from '@mui/material/IconButton';


const HomePage = () => {
  const [allRestaurants, setAllRestaurants] = useState([]);
  const navigate = useNavigate();

  const user = JSON.parse(sessionStorage.getItem('user'))

  useEffect(() => {
    fetchRestaurants();
  }, []);

  const fetchRestaurants = () => {
    axios
      .get("http://localhost:8080/restaurant/allRestaurant")
      .then((response) => {
        setAllRestaurants(response.data);
      });
  };
 
  const showFoodItems = (restId) => {
    console.log(restId);
    navigate("/food-items", { state: restId });

  };

 
  

  const goToSearch=()=>{
    navigate("/filter")
  }



  return (
    <div className="home">
   <div className="container d-flex flex-wrap">
      {allRestaurants.map((restaurant) => {
        return (
          <div
            key={restaurant.restId}
            className="card m-3"
            style={{ width: "22rem" }}
          >
            <div className="m-2">
              <img
                src={"http://localhost:8080/restaurant/" + restaurant.thumbnail}
                className="card-img-top rounded border border-primary"
                alt={restaurant.restaurantName}
                style={{height:"15rem"}}
              />
            </div>
            <div className="card-body">
              <h5 className="card-title">{restaurant.restaurantName}</h5>
              <button
                onClick={() => {
                  showFoodItems(restaurant.restId);
                }}
                className="btn btn-primary"
              >
                Show Food Item
              </button>
            </div>
          </div>
        );
      })}
    </div>

    </div>
  );
};

export default HomePage;
