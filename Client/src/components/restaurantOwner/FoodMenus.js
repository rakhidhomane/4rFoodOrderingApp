import React, { useEffect, useState } from 'react'
import axios from 'axios';
import '../../css/Image.css'
import { useNavigate } from 'react-router-dom';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { Avatar, Button, Typography } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import { styled } from '@mui/material/styles';

const FoodMenus = () => {
    const [foodMenus, setFoodMenus] = useState([])
    const navigate = useNavigate()

    const fetchAllFoodMenus = () => {
        axios.get("http://localhost:8080/food-menu/allFoodMenus")
            .then(response => {
                setFoodMenus(response.data)
            }).catch(error => {
                console.log(console.log(error.data))
            })
    }

    useEffect(() => {
        fetchAllFoodMenus();
    }, [])

    const StyledTableCell = styled(TableCell)(({ theme }) => ({
        [`&.${tableCellClasses.head}`]: {
            backgroundColor: theme.palette.common.green,
            color: theme.palette.common.black,
        },
        [`&.${tableCellClasses.body}`]: {
            fontSize: 14,
        },
    }));

    const StyledTableRow = styled(TableRow)(({ theme }) => ({
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
        // hide last border
        '&:last-child td, &:last-child th': {
            border: 0,
        },
    }));


    const deleteFoodItem = foodId => {
        axios.delete('http://localhost:8080/food-menu/delete/' + foodId).then((response) => {
            fetchAllFoodMenus()
        })
    }

    const editFoodItem = foodMenu => {
        navigate('/edit-foodMenu', { state: foodMenu })
    }

    const toAddFoodMenu = () => {
        navigate("/add-foodmenu")
    }

    return (
        <div className='container w-75'>
            <Typography variant='h4' color={"voilet"}><b> Food Menu</b></Typography>

            <div className='links row'>
                <Button sx={{ m: 2 }} color='secondary' variant="text" onClick={() => toAddFoodMenu()}><span className='h3'><u>Add Food Menu</u></span></Button>
            </div>

            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 700 }} aria-label="customized table">
                    <TableHead>
                        <TableRow>
                        <StyledTableCell>sr. no</StyledTableCell>
                            <StyledTableCell>Food Name</StyledTableCell>
                            <StyledTableCell >Price</StyledTableCell>
                            <StyledTableCell align="right">Food Category</StyledTableCell>
                            <StyledTableCell align="center">Food Images</StyledTableCell>
                            <StyledTableCell align="center">Actions</StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {foodMenus.map((foodMenu, index) => (
                            <StyledTableRow key={foodMenu.foodId}>
                                <StyledTableCell component="th" scope="row">
                                    {index + 1}
                                </StyledTableCell>
                                <StyledTableCell> {foodMenu.foodName}</StyledTableCell>
                                <StyledTableCell align="left">{foodMenu.price}</StyledTableCell>
                                <StyledTableCell align="center">{foodMenu.foodCategory}</StyledTableCell>
                                <StyledTableCell align="center"><Avatar alt="Restaurant" variant='square' sx={{ ml: 13, borderRadius: 3, width: 150, height: 90 }} src={"http://localhost:8080/" + foodMenu.thumbnail} /></StyledTableCell>
                                <StyledTableCell align='right'><Button color='error' size="small" variant="contained"  onClick={() => deleteFoodItem(foodMenu.foodId)}>Delete</Button>
                                    <Button color='info' size="small" variant="contained" sx={{ margin: 5 }} onClick={() => editFoodItem(foodMenu)}> Update</Button>
                                </StyledTableCell>
                            </StyledTableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div >
    )
}

export default FoodMenus