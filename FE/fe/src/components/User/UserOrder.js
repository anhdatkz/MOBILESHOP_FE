import { Fragment, useEffect, useState } from "react"
import apiConfig from "../../api/apiConfigs"
import { VND } from "../../ultils/Format"
import ModalUserOrderDetail from "../Modal/ModalUserOrderDetail"
import "./User.css"

function UserOrder() {

    const [orderInfo, setOrderInfo] = useState([])
    const [modal, setModal] = useState(false)
    const [madh, setMaDH] = useState("")


    const username = localStorage.getItem('username')

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/donhang/kh/${username}`)
            .then((res) => res.json())
            .then((data) => {
                setOrderInfo(data)
                console.log(data)
            })
    }, [])

    const showModal = (madh) => {
        setMaDH(madh)
        console.log("idGioHang" + madh)
        return setModal(true)
    }

    const closeModal = () => {
        setModal(false)
        console.log(modal)
    }


    console.log(orderInfo)
    return (
        <>
            <div className="order">
                <div >
                    <h2>Hồ sơ của tôi</h2>
                </div>
                <table className="order-table">
                    <thead>
                        <tr>
                            <th>Mã ĐH</th>
                            <th>Người nhận</th>
                            <th>Địa chỉ</th>
                            <th>SĐT</th>
                            <th>Ngày đặt</th>
                            <th>Tổng tiền</th>
                            <th>Trạng thái</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {orderInfo.map((orders) => (
                            <tr key={orders.madh}>
                                <td>{orders.madh}</td>
                                <td>{orders.tennguoinhan}</td>
                                <td>{orders.diachinhan}</td>
                                <td>{orders.sdtnguoinhan}</td>
                                <td>{orders.ngaylap}</td>
                                <td className="order-total">{VND.format(orders.tongtien)}</td>
                                {/* <td className="order-status">{orders.trangThai.trangthai}</td> */}
                                <td className={orders.trangThai.matrangthai === 1
                                    ? "to-ship"
                                    : (orders.trangThai.matrangthai === 2 ? "to-receive" : "completed")}>{orders.trangThai.trangthai}
                                </td>
                                <td><button className="btn btn-primary btn-order" onClick={() => showModal(orders.madh)}>Chi tiết</button></td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            {modal === true ? <ModalUserOrderDetail hide={closeModal} madh={madh} /> : <Fragment />}
        </>
    )
}

export default UserOrder