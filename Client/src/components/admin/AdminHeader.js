import React from 'react'
import { Link } from 'react-router-dom'
import '../../css/AdminHeader.css'

function AdminHeader() {
    return (
        <div className="topnav" id="myTopnav">
            <Link className="nav-link" to='/home-page'> 4R Food App</Link>
            <Link  className="nav-link" to="/admin-home">Home</Link>
           
            <Link className="nav-link" to={"/restaurant"}>
                Restaurant
            </Link>
            <Link className="nav-link" to={"/user-details"}>
                Users
            </Link>
            <div className="topnav-right">
                <Link className="nav-link " to={"/logout"}>
                    LogOut
                </Link>
            </div>
        </div>
    )
}

export default AdminHeader
