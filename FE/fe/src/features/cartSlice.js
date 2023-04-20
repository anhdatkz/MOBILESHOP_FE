import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import axios from "axios";
import { toast } from "react-toastify"
import apiConfig from "../api/apiConfigs";


async function asyncOperation(value) {
    return new Promise((resolve) => {
        resolve(value);
    });
}

async function getInitData(res) {
    try {
        const result = await res.data.reduce(async (accPromise, currentValue) => {
            const acc = await accPromise;
            const processedValue = await asyncOperation(currentValue);
            const response = await axios.get(`${apiConfig.baseUrl}/loaisanpham/${processedValue.maloai}`)
            const lsp = { ...response.data, cartQuantity: processedValue.soluong, total: processedValue.tong }
            console.log(lsp)
            return [...acc, lsp];
        }, Promise.resolve([]));

        return result
        console.log(result);
    } catch (error) {
        console.error(error);
    }
}

export const fetchCartData = createAsyncThunk(
    'cart/fetchData',
    async () => {
        let username = localStorage.getItem("username")

        const res = await axios.get(`${apiConfig.baseUrl}/ctgh/kh/${username}`)
        // let result = []
        console.log(username)
        console.log(res)
        // const result = res.data.reduce(async (acc, cur) => {
        //     const response = await axios.get(`${apiConfig.baseUrl}/loaisanpham/${cur.maloai}`)
        //     const lsp = { ...response.data, cartQuantity: cur.soluong, total: cur.tong }
        //     console.log(lsp)
        //     acc = [...acc, lsp]
        //     return acc
        // }, [])
        const result = getInitData(res)
        return result
    }
);

export const initializeCart = () => {
    return async (dispatch) => {
        try {
            const resultAction = await dispatch(fetchCartData());
            if (fetchCartData.fulfilled.match(resultAction)) {
                const { payload } = resultAction;
                cartSlice.initialState = payload;
            }
        } catch (error) {
            console.log('Error fetching cart data:', error);
        }
    };
};

const initialState = {
    // cartItems: localStorage.getItem("isLogin") === "true"
    //     ? JSON.parse(localStorage.getItem("cartItems"))
    //     : [],
    cartItems: [],
    cartTotalQuatity: 0,
    cartTotalAmount: 0,
    status: 'idle'
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
                if (state.cartItems[itemIndex].cartQuantity > action.payload.soluongton) {
                    state.cartItems[itemIndex].cartQuantity -= 1;
                    toast.error("Không thể đặt quá số lượng sản phẩm hiện có!", {
                        position: "top-center"
                    })

                } else {
                    if (state.cartItems[itemIndex].giamgia[0]) {
                        state.cartItems[itemIndex].total = (state.cartItems[itemIndex].gia - state.cartItems[itemIndex].gia * state.cartItems[itemIndex].giamgia[0].phantram / 100)
                            * state.cartItems[itemIndex].cartQuantity
                    } else {
                        state.cartItems[itemIndex].total = state.cartItems[itemIndex].gia * state.cartItems[itemIndex].cartQuantity
                    }
                    // state.cartItems[itemIndex].total = totalCart;
                    toast.info("Tăng số lượng thành công!", {
                        position: "top-center"
                    })
                }
            } else {
                if (action.payload.giamgia[0]) {
                    totalCart = (action.payload.gia - action.payload.gia * action.payload.giamgia[0].phantram / 100)
                } else {
                    totalCart = action.payload.gia
                }

                const tempProduct = { ...action.payload, cartQuantity: 1, total: totalCart }
                state.cartItems.push(tempProduct)
                toast.success("Thêm sản phẩm vào giỏ hàng thành công!", {
                    position: "top-center"
                })
            }

            localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        },

        addToCartByQuantity(state, action) {
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

        removeFromCart(state, action) {
            const nextCartItem = state.cartItems.filter(
                cartItem => cartItem.maloai !== action.payload.maloai
            )

            state.cartItems = nextCartItem

            // toast.error("Xóa sản phẩm khỏi giỏ hàng thành công!", {
            //     position: "top-center"
            // })
            localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        },

        decreaseCartItem(state, action) {
            let totalCart = 0;

            const itemIndex = state.cartItems.findIndex(
                cartItem => cartItem.maloai === action.payload.maloai
            )

            if (state.cartItems[itemIndex].cartQuantity > 1) {
                state.cartItems[itemIndex].cartQuantity -= 1

                if (state.cartItems[itemIndex].giamgia[0]) {
                    state.cartItems[itemIndex].total = (state.cartItems[itemIndex].gia - state.cartItems[itemIndex].gia * state.cartItems[itemIndex].giamgia[0].phantram / 100)
                        * state.cartItems[itemIndex].cartQuantity
                } else {
                    state.cartItems[itemIndex].total = state.cartItems[itemIndex].gia * state.cartItems[itemIndex].cartQuantity
                }

                toast.info("Giảm số lượng thành công!", {
                    position: "top-center"
                })
            } else if (state.cartItems[itemIndex].cartQuantity === 1) {
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

        getTotals(state, action) {
            let { total, quantity } = state.cartItems.reduce(
                (cartTotal, cartItem) => {
                    const { total } = cartItem
                    let itemTotal = total

                    cartTotal.total += itemTotal
                    cartTotal.quantity = state.cartItems.length

                    return cartTotal
                }, {
                total: 0,
                quantity: 0
            }
            )

            state.cartTotalQuatity = quantity
            state.cartTotalAmount = total

            localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        },
        clearCart(state, action) {
            state.cartItems = []
            localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        },

        // getCartItem(state, action) {
        //     let isLogin = localStorage.getItem("isLogin")
        //     let username = localStorage.getItem("usename")

        //     if (isLogin === "true") {
        //         fetch(`${apiConfig.baseUrl}/ctgh/kh/${username}`)
        //             .then((res) => res.json())
        //             .then((data) => {
        //                 // state.cartTotalQuatity = data.length
        //                 data.map((item) => {
        //                     fetch(`${apiConfig.baseUrl}/loaisanpham/${item.maloai}`)
        //                         .then((res) => res.json())
        //                         .then((data) => {
        //                             console.log("=================================================================")
        //                             console.log(data)
        //                             const tempProduct = { ...data, cartQuantity: item.soluong, total: item.tong }
        //                             state.cartItems.push(tempProduct)
        //                         })
        //                     state.cartTotalAmount += item.tong
        //                 })
        //             })

        //     } else {
        //         state.cartItems = []
        //         state.cartTotalQuatity = 0
        //         state.cartTotalAmount = 0
        //     }
        //     localStorage.setItem("cartItems", JSON.stringify(state.cartItems))
        // }
    },
    extraReducers: (builder) => {
        builder
            .addCase(fetchCartData.pending, (state) => {
                state.status = 'loading';
            })
            .addCase(fetchCartData.fulfilled, (state, action) => {
                state.status = 'succeeded';
                state.cartItems = action.payload;
                state.cartTotalQuatity = action.payload.length
                state.cartTotalAmount = action.payload.reduce((acc, cur) => {
                    return acc + cur.total
                }, 0)
            })
            .addCase(fetchCartData.rejected, (state, action) => {
                state.status = 'failed';
                state.error = action.error.message;
            });
    },
})

export const {
    addToCart,
    addToCartByQuantity,
    removeFromCart,
    decreaseCartItem,
    getTotals,
    clearCart,
    getCartItem
} = cartSlice.actions

export default cartSlice.reducer