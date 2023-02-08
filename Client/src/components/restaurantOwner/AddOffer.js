import axios from 'axios'
import React, { useEffect, useState } from 'react'
import FormControl from '@mui/material/FormControl';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import { Button, Container, Divider, TextField, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';

function AddOffer() {
    const [offers, setOffers] = useState([])
    const [offerPrice, setOfferPrice] = useState(-1)
    const [offer, setOffer] = useState(0)
    const [error, setError] = useState('');
    const [errorResponse, setErrorResponse] = useState('');
    const navigate = useNavigate()

    useEffect(() => {
        fetchAllFoodWithoutOffer()
    }, [])

    const fetchAllFoodWithoutOffer = () => {
        axios.get("http://localhost:8080/food-menu/getFood")
            .then(response => {
                setOffers(response.data)
                setOfferPrice(response.data[0].foodId)
            })
    }

    const addFoodOfferPrice = () => {
        
            const data = new FormData();
            data.append("foodId", offerPrice)
            data.append("offer", offer)

            axios.post("http://localhost:8080/food-menu/addOffer", data).then(reponse => {
                console.log(reponse.data)
                navigate('/offer')
            }).catch(error => {
                setErrorResponse(error.response.data)
                alert(errorResponse)
            })
        
    }

    const toOfferPage = () => {
        navigate('/offer')
    }

    return (
        <div>
            <Container className="float-start" maxWidth='sm' sx={{ pb: 4 }}>
           
                <Typography varient="h1" sx={{ m: 3, fontSize: 35 }} color={"green"}><u>Add Offer</u></Typography>
                <Divider></Divider>
                <Box sx={{ minWidth: 120 }}>
                    <FormControl fullWidth>
                        <InputLabel id="demo-simple-select-label">Food</InputLabel>
                        <Select
                            labelId="demo-simple-select-label"
                            id="demo-simple-select"
                            label="Restaurant"
                            onChange={(event) => {
                                setOfferPrice(event.target.value)
                            }}
                            variant="filled"
                        >
                            {offers.map((offer) => {
                                return (
                                    <MenuItem value={offer.foodId}> {offer.foodName}</MenuItem>
                                )
                            })
                            }
                        </Select>
                    </FormControl>
                </Box>

                <TextField  variant="filled" label='offered price' name='Offer Price' fullWidth required margin='normal' onChange={(event) => {
                    setOffer(event.target.value)
                }}></TextField>

                <Button id="succesBtn" variant="outlined" onClick={() => addFoodOfferPrice()} color="warning"><span className='h3'> Add</span></Button>

            </Container>
        </div>
    )
}

export default AddOffer