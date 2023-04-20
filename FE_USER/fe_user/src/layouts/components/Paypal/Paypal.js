import "./Paypal.css"
import "../Cart/Cart.css"
import { useEffect, useRef, useState } from "react"
import { useSelector, useDispatch } from "react-redux"
import apiConfig from '../../api/apiConfigs'
import { toast } from "react-toastify"
import { useNavigate } from "react-router-dom"
import { clearCart } from "../../features/cartSlice"
import { caculate } from "../../ultils/Format"

function PayPal({ isCheckout }) {
    const paypal = useRef();
    const cart = useSelector((state) => state.cart)
    const cartItems = cart.cartItems
    const [userInfo, setUserInfo] = useState({})
    const username = localStorage.getItem('username')

    const navigate = useNavigate()

    const dispatch = useDispatch()

    let cartData = {}
    let resultCart = {}
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();
    today = dd + '-' + mm + '-' + yyyy;

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/khachhang/tk/${username}`)
            .then((res) => res.json())
            .then((data) => {
                setUserInfo(data)
                cartData = {
                    ngay: today,
                    tennguoinhan: data.tenkh,
                    diachinhan: data.diachi,
                    sdtnguoinhan: data.sdt,
                    tongtien: cart.cartTotalAmount,
                    trangThai: {
                        matrangthai: 1,
                    },
                    khachHang: {
                        cmnd: data.cmnd
                    }
                }
            })
    }, [])

    const handleSaveCart = async () => {

        let response = await fetch(`${apiConfig.baseUrl}/giohang`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cartData),
        })

        let idGioHang = await response.json()
        console.log('ĐÃ CHẠY TỚI ĐÂY');
        console.log('Giỏ hàng:', idGioHang);
        cartItems.forEach((cartItem) => {
            let cartDetailData = {
                id: {
                    idgiohangctgh: idGioHang,
                    maloaictgh: cartItem.maloai
                },
                soluong: cartItem.cartQuantity,
                tong: cartItem.total
            }

            fetch(`${apiConfig.baseUrl}/ctgh`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(cartDetailData),
            })
                .then((response) => {
                    if (response) {
                        return response.json()
                    }
                })
                .then((data) => {
                    console.log('CTGH:', data);
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        });

    }

    useEffect(() => {
        window.paypal
            .Buttons({
                createOrder: (data, actions, err) => {
                    return actions.order.create({
                        intent: "CAPTURE",
                        purchase_units: [
                            {
                                description: "Cool looking table",
                                amount: {
                                    currency_code: "USD",
                                    value: cart.cartTotalAmount,
                                },
                            },
                        ],
                    });
                },
                onApprove: async (data, actions) => {
                    handleSaveCart()
                    const order = await actions.order.capture();
                    console.log(order);
                    dispatch(clearCart())
                    navigate("/")
                    toast.success("Đặt hàng thành công!", {
                        position: "top-center"
                    })
                },
                onError: (err) => {
                    console.log(err);
                    toast.error("Đặt hàng thất bại!", {
                        position: "top-center"
                    })
                },
            })
            .render(paypal.current);
    }, [paypal, cart, dispatch]);

    return (
        <>
            <div className="checkout">
                <div className="customer">
                    <div className="customer-info">
                        <div className="delivery-address">
                            <h4>Địa chỉ nhận hàng</h4>
                            <h6 className="name">{userInfo.tenkh}</h6>
                            <div className="address">{userInfo.diachi}</div>
                            <div className="phone">{userInfo.sdt}</div>
                            <div className="email">{userInfo.email}</div>
                        </div>
                        <div className="delivery-mode">
                            <h4>Hình thức giao hàng</h4>
                            <div>Giao Tiết Kiệm</div>
                            <div>Miễn phí vận chuyển</div>
                        </div>
                        <div className="delivery-mode">
                            <h4>Hình thức thanh toán</h4>
                            <div>Thanh toán qua Paypal</div>
                        </div>
                    </div>
                </div>
                <div className="cart">
                    <h5>Chi tiết giỏ hàng</h5>
                    <div className="col-md-12">
                        <table className="table">
                            <thead>
                                <tr>
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
                                    cartItems.map((item, index) => (
                                        <tr key={index}>
                                            <td>{item.tenloai}</td>
                                            <td><img src={item.anh} alt="" className="cart-item-img" /></td>
                                            <td>{caculate(item,"$")}</td>
                                            <td>
                                                <span className="btn btn-info"> {item.cartQuantity} </span>
                                            </td>
                                            <td>{item.total} $</td>
                                        </tr>
                                    ))
                                }
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td className="fw-bold">Tổng thanh toán</td>
                                    <td className="fw-bold"> {cart.cartTotalAmount} $</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div >
                <div className="paypal">
                    <div ref={paypal}></div>
                </div>
            </div>
        </>
    )
}

export default PayPal