import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

const Complaint = () => {
    const param=useParams()
    const [complaints,setComplaints]=useState([])
    const [isReminderSent, setIsReminderSent] = useState(false)
    const [show,setShow]=useState(false);
    const [wait,setWait]=useState(false);
    const [isUserLogged,setIsUserLogged]=useState(false)
    
    const hideModal=()=>{
      setShow(false)
      setIsReminderSent(false)
      setWait(false)
    }

    const getComplaints=()=>{
        axios.get('http://localhost:8080/complaint/getAllComplaintsOfUser/'+param.id)
        .then(response=>{
            setComplaints(response.data)
        })
      }
    
    const sendReminder=(complaint)=>{
      
      axios.post('http://localhost:8080/complaint/send-reminder',complaint)
      .then(response=>{
        console.log(response.data)
        if(response.data){
         alert('wait for 2 hours once complaint is registered')
        }else{
          alert('reminder sent')
        }
      })
    }
    
    useEffect(() => {
      getComplaints();
    }, []);

  return (
    <div>
    
    <table className="table table-dark">
      <thead>
        <tr>
          <th scope="col">Order Id</th>
          <th scope="col">Order Time</th>
          <th scope="col">Complaint Date</th>
          <th scope="col">Complaint Status</th>
          <th scope="col">Complaint</th>
        </tr>
      </thead>
      <tbody>
      {complaints.map((complaint)=>{
        let orderDateTime = complaint.userOrder.orderTime.toString().split(/[T . +]/);
        let complaintDateTime = complaint.complaintDate.toString().split(/[T . +]/);
        if(param.id == complaint.userOrder.user.userId ){
        return (
          <tr>
            <td>{complaint.userOrder.orderId}</td>
            <td>{orderDateTime[0]+" "+orderDateTime[1]}</td>
            <td>{complaintDateTime[0]+" "+complaintDateTime[1]}</td>
            <td><button onClick={()=>{sendReminder(complaint)}} className='btn btn-warning'>{complaint.complaintStatus}</button></td>
            <td>{complaint.complaintMessage}</td>
          </tr>
        )
        }
      })}
        
        
      </tbody>
        
   </table>
    
       
       </div>
  )
}

export default Complaint