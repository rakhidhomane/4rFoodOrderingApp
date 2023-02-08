import React, { useState, useEffect } from 'react'
import { useLocation } from 'react-router-dom'
import axios from 'axios'
import { useDispatch, useSelector } from 'react-redux'
import { Button } from '@mui/material';
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Typography from "@mui/material/Typography";
import CartIcon from "@mui/icons-material/ShoppingCart";
import ShareIcon from "@mui/icons-material/Share";

const ShowFoodItems = () => {
  const location = useLocation()
  const restaurantId = location.state
  const [foodItems, setFoodItems] = useState([])
  const cart = useSelector((state) => state);
  const [show, setShow]=useState(false)
  const [isModalOpen,setIsModalOpen]=useState(false)
  const dispatch = useDispatch();
  const hideModal=()=>{
    setShow(false);
    setIsModalOpen(false)
  }

  useEffect(() => {
    getFoodByRestaurant()
  }, [])

  const getFoodByRestaurant = () => {
    axios.get('http://localhost:8080/food-menu/getFoodByRestaurant/' + restaurantId)
      .then(response => {
        setFoodItems(response.data)
      })
  }
  
  const addToCart = (food) => {
  
    dispatch({ type: 'ADD_TO_CART', payload: food })
    
  }

  return (
    <div className="d-flex container">
      <div className="container d-flex">
      {foodItems.map((food) => {
        food.quantity=1
        return (
          <div
            key={food.foodId}
            className="card m-3"
            style={{ width: "22rem" }}
          >
            <div className="m-2" style={{height:"15rem"}}>
              <img
                src={"http://localhost:8080/" + food.thumbnail}
                className="card-img-top rounded border border-primary"
                alt={food.foodName}
              />
            </div>
            <div className="card-body">
              <h5 className="card-title">{food.foodName}</h5>
              <button
                onClick={() => {
                  dispatch({type:'ADD_TO_CART',payload:food})
                }}
                className="btn btn-primary"
              >
                Add to Cart 
              </button>
            </div>
          </div>
        );
      })}
    </div>

      </div>

  )
}


export default ShowFoodItems