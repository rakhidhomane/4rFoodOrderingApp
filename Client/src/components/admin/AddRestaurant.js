import React, { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'
import { Typography, TextField, Button, Container, Divider } from '@mui/material';

const AddRestaurant = () => {
    const [area, setArea] = useState('')
    const [street, setStreet] = useState('')
    const [pincode, setAPincode] = useState('')
    const [restaurantName, setRestaurantName] = useState('')
    const [restaurantUserName, setRestaurantUserName] = useState('')
    const [restaurantPassword, setRestaurantPassword] = useState('')
    const [restaurantEmail, setRestaurantEmail] = useState('')
    const [thumbnail, setThumbnail] = useState(undefined)
    const [error, setError] = useState('');
    const [errorResponse, setErrorResponse] = useState('');
    const navigate = useNavigate()

    const onFileSelect = (event) => {
        setThumbnail(event.target.files[0])
    }

    const toRestaurant = () => {
        navigate("/restaurant")
    }

    const addRestaurant = () => {

        
            const data = new FormData()
            data.append('area', area)
            data.append('street', street)
            data.append('thumbnail', thumbnail)
            data.append('pincode', pincode)
            data.append('restaurantName', restaurantName)
            data.append('restaurantUserName', restaurantUserName)
            data.append('restaurantPassword', restaurantPassword)
            data.append('restaurantEmail', restaurantEmail)

            axios.post('http://localhost:8080/restaurant/addRestaurant', data)
                .then((response) => {
                    setErrorResponse(" ")
                    navigate("/restaurant")
                })
                .catch((error) => {
                    setErrorResponse(error.data)
                    alert(errorResponse)
                })
        

    }
    return (
        <Container className='"d-flex justify-content-start'  maxWidth='sm' sx={{ pb: 4 }}>
            <span className="h3 text-danger">{error}</span>
            <Typography varient="h1" sx={{ pt: 3, fontSize: 35 }} color={"rebeccapurple"} > New Restaurant</Typography>
            <Divider />
            <TextField label='Restaurant Name' name='name' fullWidth required margin='normal' onChange={(event) => {
                setRestaurantName(event.target.value)
            }}></TextField>

            <TextField label='User Name' name='email' fullWidth required margin='normal' onChange={(event) => {
                setRestaurantUserName(event.target.value)
            }}></TextField>

            <TextField label='Email' name='Email' fullWidth required margin='normal' onChange={(event) => {
                setRestaurantEmail(event.target.value)
            }}></TextField>

            <TextField label='Password' name='Password' fullWidth required margin='normal' onChange={(event) => {
                setRestaurantPassword(event.target.value)
            }}></TextField>

            <TextField label='Place' name='Area' fullWidth required margin='normal' onChange={(event) => {
                setArea(event.target.value)
            }}></TextField>

            <TextField label='Pincode' name='Pincode' fullWidth required margin='normal' onChange={(event) => {
                setAPincode(event.target.value)
            }}></TextField>

            <TextField label='landmark' name='Street' fullWidth required margin='normal' onChange={(event) => {
                setStreet(event.target.value)
            }}></TextField>

            <TextField name='Thumbnail' accept="image/*" type={"file"} fullWidth required margin='normal' onChange={onFileSelect}></TextField>

            <Button id="succesBtn" variant="contained" onClick={() => addRestaurant()} color="primary"> Add</Button>
        </Container>
    )
}

export default AddRestaurant

