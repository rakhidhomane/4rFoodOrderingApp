import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import axios from "axios";  
import { useNavigate } from "react-router-dom";

const Cart = () => {
  const navigate=useNavigate()
  const cart = useSelector((state) => state);
  const add = (previousValue, currentValue) => {
    return previousValue + currentValue.price * currentValue.quantity;
  };
  
  const cartTotal = cart.reduce(add, 0);
  const [orderContent, setOrderContent] = useState({
    foodMenus: cart,
    user: {},
    total: cartTotal,
  });
  const [modalOpen, setModalOpen] = useState(false);
  const [show, setShow] = useState(false)
  const hideModal=()=>{
    setShow(false)
  }
  const dispatch = useDispatch();
  
  const emptyCart=<img  className="w-75" style={{height:"30rem"}} src="https://mir-s3-cdn-cf.behance.net/projects/404/95974e121862329.Y3JvcCw5MjIsNzIxLDAsMTM5.png" alt="" />

  useEffect(() => {
    fetchUser();
  }, []);

  useEffect(() => {
    setOrderContent((prevState)=>{return  {...prevState, foodMenus: cart, total:cartTotal }});
  }, [cart]);

  const removeFoodItem = (food) => {
    if (food.quantity > 1) {
      dispatch({ type: "DECREASE_QUANTITY", payload: food });
    } else {
      dispatch({ type: "REMOVE_FROM_CART", payload: food });
    }
  };

  const fetchUser = () => {
    axios
      .get(
        "http://localhost:8080/food-delivery/getUserByEmail/" +
          sessionStorage.getItem("userEmail").toString()
      )
      .then((response) => {
        setOrderContent({ ...orderContent, user: response.data });
      });
  };
  
  const goToHomePage=()=>{
    navigate('/')
    window.location.reload()
  }

  const placeOrder = () => { 
    dispatch({type:"EMPTY",payload:[]})
    axios
      .post("http://localhost:8080/order/place-order", orderContent)
      .then((response) => {
        console.log(response);
        
       alert('congrats... your order is getting prepared')
        // navigate('/')
        
      });
      
  };
  return (
    <div className="my-5 container">
      <div className="row">{cart.length===0 && emptyCart}</div>
      <div className="container d-flex text-center">
        
        {cart.map((food) => {
          return (
            <div
              key={food.foodId}
              className="card m-3"
              style={{ width: "22rem" }}
            >
              <div className="m-2">
                <img
                  src={"http://localhost:8080/restaurant/" + food.thumbnail}
                  className="card-img-top rounded border border-primary"
                  alt={food.foodName}
                  style={{ height: "15rem" }}
                />
              </div>
              <div className="card-body">
                <h5 className="card-title">{food.foodName}</h5>
              </div>
              <div>
                <button
                  onClick={() => dispatch({ type: "INCREASE_QUANTITY", payload: food })}
                  className="btn btn-dark"
                >
                  +
                </button>
                <span className="mx-4">{food.quantity}</span>
                <button
                  onClick={() => {
                    removeFoodItem(food);
                  }}
                  className="btn btn-dark"
                >
                  -
                </button>
              </div>
              <div className="h3 my-2">
                Total Price: ₹ {food.price * food.quantity}
              </div>
              <button
                onClick={() => dispatch({ type: "REMOVE_FROM_CART", payload: food })}
                className="btn btn-info"
              >
                Remove
              </button>
            </div>
            
          );
        })}
      </div>
      <div className="mx-5 container">
        <span className="h3 col text-black text fw-bold row">
          Total payable Bill: ₹ {cartTotal} /-
        </span>
        <div className="row w-25">
        <button data-bs-toggle="modal" data-bs-target="#exampleModal" onClick={placeOrder} className="btn btn-success float-end">
          Place Order
        </button>
        </div>
      </div>
    </div>
  );
};

export default Cart;