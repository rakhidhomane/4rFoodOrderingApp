import React, { useState } from 'react'
import { Container, Typography, Box, TextField, Button } from '@mui/material'
import { fontSize, height } from '@mui/system';
import Paper from '@mui/material/Paper';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Registration() {

  const [isDisabled, setIsDisabled] = useState(true)
  const [userName, setUserName] = useState('')
  const [userPassword, setUserPassword] = useState('')
  const [userEmail, setUserEmail] = useState('')
  const [userMobileNo, setUserMobileNo] = useState('')
  const [street, setStreet] = useState('');
  const [area, setArea] = useState('');
  const [pincode, setPincode] = useState('');
  const navigate = useNavigate()

  const resgister = () => {
    const body = { userName, userPassword, userEmail, userMobileNo, address: { street, pincode, area } }

    const header = {
      headers: {
        "Content-Type": "application/json",
      },
    };

    axios.post("http://localhost:8080/food-delivery/register", body, header)
      .then(response => {
        console.log(response)
        alert("h")
      })
      .catch(error => console.log(error));
      navigate("/user-login")
  }

  const styles = {
    paperContainer: {
      backgroundImage: `url(https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832__480.jpg)`,
      backgroundRepeat: "no-repeat",
      backgroundSize: "cover",
    },

    Container: {
      backgroundImage: `url(https://www.teahub.io/photos/full/155-1559258_white-light-wallpaper-4k.jpg)`,
      backgroundRepeat: "no-repeat",
      backgroundSize: "cover",
    }
  };

  return (
    <body style={styles.paperContainer}>
      <Box component='form' pt={10}>
        <Container maxWidth='sm' style={styles.Container} sx={{ pb: 4 }}>
          <Typography varient="h1" sx={{ pt: 3, fontSize: 35 }} >User Registration</Typography>

          <TextField label='Full Name' name='name' fullWidth required margin='normal' onChange={(event) => setUserName(event.target.value)}></TextField>

          <TextField label='Email Address' name='email' fullWidth required margin='normal' onChange={(event) => setUserEmail(event.target.value)}></TextField>

          <TextField label='Mobile' name='mobile' fullWidth required margin='normal' onChange={(event) => setUserMobileNo(event.target.value)}></TextField>

          <TextField type={"password"} label='Password' name='password' fullWidth required margin='normal' onChange={(event) => setUserPassword(event.target.value)}></TextField>

          <TextField label='Area' name='Area' fullWidth required margin='normal' onChange={(event) => setArea(event.target.value)}></TextField>

          <TextField label='Street' name='Street' fullWidth required margin='normal' onChange={(event) => setStreet(event.target.value)}></TextField>

          <TextField label='Pincode' name='Pincode' fullWidth required margin='normal' onChange={(event) => setPincode(event.target.value)}></TextField>

          <Button type='submit' variant="contained" color="primary" fullWidth onClick={() => resgister()}>SignUp</Button>

        </Container>
      </Box>
    </body>
  )
}

export default Registration