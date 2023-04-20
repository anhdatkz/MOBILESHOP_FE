
import {Link} from 'react-router-dom'
import Cart from '../../components/Cart/Cart'
import {useState} from 'react'

function CartDetails(){

    const [cartItems, setCartItems] = useState([])

    const onAdd = (lsp) =>{
        const exist = cartItems.find((x) => x.maloai === lsp.maloai);
        if(exist){
            setCartItems(
                cartItems.map((x) =>
                x.maloai === lsp.maloai ? {...exist, qty: exist.qty+ 1} : x
                )
            )
        }
        else{
            setCartItems([...cartItems, {...lsp, qty: 1}])
        }
    }

    return(
        <>
        <Cart onAdd={onAdd} cartItems={cartItems}></Cart>
        </>
    )
}

export default CartDetails