import "./Cart.css"
import { Link, useNavigate } from 'react-router-dom'
import { FaRegWindowClose } from "react-icons/fa"
import cartEmpty from '../../assets/images/cartEmpty.png'
import { useEffect, useState } from "react"
import { useSelector, useDispatch } from "react-redux"
import { addToCart, decreaseCartItem, getTotals, removeFromCart } from "../../features/cartSlice"
import { toast } from "react-toastify"
import { caculate, formatTien, VND } from "../../ultils/Format"
import apiConfig from "../../api/apiConfigs"
// import { loginData } from "../../ultils/LoginData"
import { current } from "@reduxjs/toolkit"


function Cart(props) {

    const [ctgh, setCTGH] = useState([])
    const [isEmpty, setIsEmpty] = useState(0)
    const [checkout, setCheckout] = useState(false)
    let isLogin = localStorage.getItem("isLogin")
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const cart = useSelector((state) => state.cart)
    const cartItems = cart.cartItems

    const handleRemoveFromCart = async (item) => {
        let cartDetailData = {
            idgiohangctgh: localStorage.getItem("cartId"),
            maloaictgh: item.maloai
        }

        fetch(`${apiConfig.baseUrl}/ctgh`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cartDetailData),
        })

        toast.success("Sản phẩm đã được xóa khỏi giỏ hàng!", {
            position: "top-center"
        })
        dispatch(removeFromCart(item))
    }

    const handleIncreaseCartItem = (item) => {
        let cartDetailData = {
            id: {
                idgiohangctgh: item.idgiohang,
                maloaictgh: item.maloai
            },
            soluong: item.soluong + 1,
            tong: item.gia * (item.soluong + 1)
        }

        if (item.soluong >= 1) {
            fetch(`${apiConfig.baseUrl}/ctgh`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(cartDetailData),
            })

            // toast.success("Sản phẩm đã được xóa khỏi giỏ hàng!", {
            //     position: "top-center"
            // })
        }

        dispatch(addToCart(item))
    }

    const handleDecreaseCartItem = (item) => {
        let cartDetailData = {
            id: {
                idgiohangctgh: item.idgiohang,
                maloaictgh: item.maloai
            },
            soluong: item.soluong - 1,
            tong: item.gia * item.soluong - item.gia
        }

        if (item.soluong === 1) {
            toast.warn("Không thể giảm số lượng sản phẩm được nữa!", {
                position: "top-center"
            })
        } else if (item.soluong > 1) {
            fetch(`${apiConfig.baseUrl}/ctgh`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(cartDetailData),
            })
            dispatch(decreaseCartItem(item))
            // toast.success("Giảm số lượng thành công!", {
            //     position: "top-center"
            // })
        }
    }

    const onClickCheckout = (totalAmount) => {
        localStorage.setItem("totalAmount", totalAmount)
        if (isLogin == "true") {
            navigate("/checkout")
        }
        else {
            toast.error("Vui lòn đăng nhập", {
                position: "top-center"
            })
            navigate("/login")
        }
        // setCheckout(false)
    }

    const fetchData = async () => {
        const response = await fetch(`${apiConfig.baseUrl}/ctgh/kh/${localStorage.getItem("username")}`)
        if (!response.ok) {
            throw new Error('Data coud not be fetched!')
        } else {
            return response.json()
        }
    }

    useEffect(() => {
        fetchData()
            .then((data) => {
                setCTGH(data)
                setIsEmpty(data.length)
                console.log(data)
            })
            .catch((e) => {
                console.log(e.message)
            })
        dispatch(getTotals())
    }, [cart, dispatch])


    return (
        <>
            <div className="cart">
                {isEmpty === 0 ? (
                    <div className="cart-empty">
                        <img src={cartEmpty} alt="" />
                        <h5>Không có sản phẩm nào trong giỏ hàng của bạn!</h5>
                        <Link to="/">
                            <button className="btn btn-warning">Tiếp tục mua sắm</button>
                        </Link>
                    </div>
                ) : (
                    <div className="col-md-12">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tên</th>
                                    <th>Ảnh</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    ctgh.map((item, index) => (
                                        <tr key={item.maloai}>
                                            <td>{index + 1}</td>
                                            <td>{item.tenloai}</td>
                                            <td><img src={item.anh} alt="" className="cart-item-img" /></td>
                                            <td>{VND.format(item.gia)}</td>
                                            <td>
                                                <span className="btn btn-primary" style={{ margin: '2px' }} onClick={() => handleDecreaseCartItem(item)}>-</span>
                                                <span className="btn btn-info"> {item.soluong} </span>
                                                <span className="btn btn-primary" style={{ margin: '2px' }} onClick={() => handleIncreaseCartItem(item)}>+</span>
                                            </td>
                                            <td>{VND.format(item.tong)}</td>
                                            <td><FaRegWindowClose onClick={() => handleRemoveFromCart(item)} /></td>
                                        </tr>
                                    ))
                                }
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td className="fw-bold">Tổng thanh toán</td>
                                    <td className="fw-bold">{
                                        VND.format(ctgh.reduce((first, current) => {
                                            return first + current.tong;
                                        }, 0))
                                    }</td>
                                </tr>
                            </tbody>
                        </table>
                        {
                            // <Link to={isLogin ? "/checkout" : "/login"}>
                            <button className="btn-checkout btn btn-primary" onClick={() => onClickCheckout(ctgh.reduce((first, current) => {
                                return first + current.tong;
                            }, 0))}>Đặt hàng</button>
                            // </Link>
                        }
                        {/* { isLogin ? (
                        <PayPal/>
                    ) : (
                        <Link to="/checkout">
                            <button className="btn-checkout btn btn-primary" onClick={onClickCheckout}>Đặt hàng</button>
                        </Link>
                    )} */}
                    </div>
                )}
            </div >
        </>
    )
}

export default Cart