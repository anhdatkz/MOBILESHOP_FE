import { createSlice } from "@reduxjs/toolkit";
import { toast } from "react-toastify"

const initialState = {
    cartItems: localStorage.getItem("cartItems")
        ? JSON.parse(localStorage.getItem("cartItems"))
        : [],
    cartTotalQuatity: 0,
    cartTotalAmount: 0
}

const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {
        addToCart(state, action) {
            const itemIndex = state.cartItems.findIndex(
                (item) => item.maloai === action.payload.maloai
            )

            let totalCart = 0;

            if (itemIndex >= 0) {
                state.cartItems[itemIndex].cartQuantity += 1;
                if(state.cartItems[itemIndex].cartQuantity > action.payload.soluongton){
                    state.cartItems[itemIndex].cartQuantity -= 1;
                    toast.error("Không thể đặt quá số lượng sản phẩm hiện có!", {
                        position: "top-center"
                    })

                } else {
                    if(state.cartItems[itemIndex].ctGiamGiaLSP[0]){
                        state.cartItems[itemIndex].total = (state.cartItems[itemIndex].thayDoiGiasLSP[0].giamoi - state.cartItems[itemIndex].thayDoiGiasLSP[0].giamoi * state.cartItems[itemIndex].ctGiamGiaLSP[0].phantram / 100) 
                        * state.cartItems[itemIndex].cartQuantity
                    } else{
                        state.cartItems[itemIndex].total = state.cartItems[itemIndex].thayDoiGiasLSP[0].giamoi * state.cartItems[itemIndex].cartQuantity
                    }
                    // state.cartItems[itemIndex].total = totalCart;
                    toast.info("Tăng số lượng thành công!", {
                        position: "top-center"
                    })
                }
            } else {
                if(action.payload.ctGiamGiaLSP[0]){
                    totalCart = (action.payload.thayDoiGiasLSP[0].giamoi - action.payload.thayDoiGiasLSP[0].giamoi * action.payload.ctGiamGiaLSP[0].phantram / 100) 
                } else{
                    totalCart = action.payload.thayDoiGiasLSP[0].giamoi
                }

                const tempProduct = { ...action.payload, cartQuantity: 1, total: totalCart }
                state.cartItems.push(tempProduct)
                toast.success("Thêm sản phẩm vào giỏ hàng thành công!", {
                    position: "top-center"
                })
            }

            localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        },

        addToCartByQuantity(state, action){
            const itemIndex = state.cartItems.findIndex(
                (item) => item.maloai === action.payload.maloai
            )

            if (itemIndex >= 0) {
                state.cartItems[itemIndex].cartQuantity += 1;
                toast.info("Tăng số lượng thành công!", {
                    position: "top-center"
                })
            } else {
                const tempProduct = { ...action.payload, cartQuantity: 1 }
                state.cartItems.push(tempProduct)
                toast.success("Thêm sản phẩm vào giỏ hàng thành công!", {
                    position: "top-center"
                })
            }
        },

        removeFromCart(state, action){
            const nextCartItem = state.cartItems.filter(
                cartItem => cartItem.maloai !== action.payload.maloai
            )

            state.cartItems = nextCartItem

            toast.error("Xóa sản phẩm khỏi giỏ hàng thành công!", {
                position: "top-center"
            })
            localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        },

        decreaseCartItem(state, action){
            let totalCart = 0;

            const itemIndex = state.cartItems.findIndex(
                cartItem => cartItem.maloai === action.payload.maloai
            )

            if(state.cartItems[itemIndex].cartQuantity > 1){
                state.cartItems[itemIndex].cartQuantity -= 1

                if(state.cartItems[itemIndex].ctGiamGiaLSP[0]){
                    state.cartItems[itemIndex].total = (state.cartItems[itemIndex].thayDoiGiasLSP[0].giamoi - state.cartItems[itemIndex].thayDoiGiasLSP[0].giamoi * state.cartItems[itemIndex].ctGiamGiaLSP[0].phantram / 100) 
                    * state.cartItems[itemIndex].cartQuantity
                } else{
                    state.cartItems[itemIndex].total = state.cartItems[itemIndex].thayDoiGiasLSP[0].giamoi * state.cartItems[itemIndex].cartQuantity
                }

                toast.info("Giảm số lượng thành công!", {
                    position: "top-center"
                })
            } else if (state.cartItems[itemIndex].cartQuantity === 1){
                const nextCartItem = state.cartItems.filter(
                    cartItem => cartItem.maloai !== action.payload.maloai
                )
    
                state.cartItems = nextCartItem
    
                toast.error("Xóa sản phẩm khỏi giỏ hàng thành công!", {
                    position: "top-center"
                })
            }

            localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        },

        getTotals(state, action){
            let {total, quantity} = state.cartItems.reduce(
                (cartTotal, cartItem) => {
                    const { ctGiamGiaLSP, thayDoiGiasLSP, cartQuantity} = cartItem
                    let itemTotal = 0
                    // const itemTotal = thayDoiGiasLSP[0].giamoi * cartQuantity

                    if(ctGiamGiaLSP[0]){
                        itemTotal = (thayDoiGiasLSP[0].giamoi - thayDoiGiasLSP[0].giamoi * ctGiamGiaLSP[0].phantram / 100) * cartQuantity
                    } else{
                        itemTotal = thayDoiGiasLSP[0].giamoi * cartQuantity
                    }

                    cartTotal.total += itemTotal
                    cartTotal.quantity = state.cartItems.length

                    return cartTotal
                },{
                    total: 0,
                    quantity: 0
                }
            )

            state.cartTotalQuatity = quantity
            state.cartTotalAmount = total
            
            localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        },
        clearCart(state, action){
            state.cartItems = []
            localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        }
    }
})

export const {
    addToCart,
    addToCartByQuantity,
    removeFromCart,
    decreaseCartItem,
    getTotals,
    clearCart,
} = cartSlice.actions

export default cartSlice.reducer