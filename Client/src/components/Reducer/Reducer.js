const Reducer=(cart=[], action)=>{
    switch(action.type){
    case'ADD_TO_CART':
        let alternateCart=cart.filter((element)=>(element.foodId===action.payload.foodId || element.restaurant.restId!==action.payload.restaurant.restId))
        if(alternateCart<1){
            return [...cart,action.payload]
        }else{
            return cart;
        }
    break;

    case 'REMOVE_FROM_CART':
        return cart.filter((element)=>element.foodId!==action.payload.foodId);
    break;

    case 'INCREASE_QUANTITY':
        let increase=cart.map((element)=>{
            if(element.foodId===action.payload.foodId){
                return {...element,quantity:element.quantity+1}
            }
            return element;
        })
        return increase;
    break;

    case 'DECREASE_QUANTITY':
        let decrease=cart.map((element)=>{
            if(element.foodId===action.payload.foodId){
                return {...element,quantity:element.quantity-1}
            }
            return element;
        })
        return decrease;
    break;

    case 'EMPTY':
        return cart=[];
    break;
    default:
        return cart;
}
    
}

  
export default Reducer;