import { Fragment, useEffect, useState } from "react"
import apiConfig from "../../api/apiConfigs"
import { VND } from "../../ultils/Format"
import ModalUserOrderDetail from "../Modal/ModalUserOrderDetail"
import "./User.css"
import { toast } from "react-toastify"

function UserOrder() {

    const [orderInfo, setOrderInfo] = useState([])
    const [modal, setModal] = useState(false)
    const [madh, setMaDH] = useState("")
    const [type, setType] = useState("")
    const [orderFilter, setOrderFilter] = useState([])
    const [reload, setReload] = useState(false)


    // const username = localStorage.getItem('username')

    const handleChangeStatus = (matrangthai) => {
        setType(matrangthai)
        let arr = []
        if (matrangthai === "") {
            setOrderFilter(orderInfo)
        } else {
            orderInfo.forEach((item) => {
                if (item.trangThai.matrangthai == matrangthai) {
                    arr = [...arr, item]
                }
            })
            setOrderFilter(arr)
        }
    }

    // useEffect(() => {
    //     fetch(`${apiConfig.baseUrl}/donhang/kh/${username}`, {
    //         headers: {
    //             'Authorization': localStorage.getItem("token")
    //         },
    //     })
    //         .then((res) => res.json())
    //         .then((data) => {
    //             setOrderInfo(data)
    //             setOrderFilter(data)
    //             console.log(data)
    //         })
    // }, [reload])
    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/donhang/kh`, {
            headers: {
                'Authorization': localStorage.getItem("token")
            },
        })
            .then((res) => res.json())
            .then((data) => {
                setOrderInfo(data)
                setOrderFilter(data)
                console.log(data)
            })
    }, [reload])

    const showModal = (madh) => {
        setMaDH(madh)
        console.log("idGioHang" + madh)
        return setModal(true)
    }

    const closeModal = () => {
        setModal(false)
        console.log(modal)
    }

    const showConfirm = (madh) => {
        let conf = window.confirm("Xác nhận hủy đơn hàng!")
        if (conf === true) {
            setReload(true)
            const data = {
                madh: madh,
                matrangthai: 4
            }
            fetch(`${apiConfig.baseUrl}/donhang`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(data)
            })
                .then((response) => response.json())
                .then((data) => {
                    setReload(true)
                    toast.success(data.message, {
                        position: "top-center"
                    })
                    console.log('Success:', data);
                })
                .catch((error) => {
                    toast.error("Duyệt đơn thất bại!", {
                        position: "top-center"
                    })
                    console.error('Error:', error);
                });
        }
        setReload(false)
        handleChangeStatus("")
    }

    console.log(orderInfo)
    return (
        <>
            <div className="order">
                <div className="order-header">
                    <h2>Đơn hàng</h2>
                    <div className="action-status">
                        <div className={type === "" ? "action-status__item active" : "action-status__item"} onClick={() => handleChangeStatus("")}>Tất cả</div>
                        <div className={type === "1" ? "action-status__item active" : "action-status__item"} onClick={() => handleChangeStatus("1")}>Chờ duyệt</div>
                        <div className={type === "6" ? "action-status__item active" : "action-status__item"} onClick={() => handleChangeStatus("6")}>Đã duyệt</div>
                        <div className={type === "2" ? "action-status__item active" : "action-status__item"} onClick={() => handleChangeStatus("2")}>Đang giao</div>
                        <div className={type === "3" ? "action-status__item active" : "action-status__item"} onClick={() => handleChangeStatus("3")}>Đã giao</div>
                        <div className={type === "4" ? "action-status__item active" : "action-status__item"} onClick={() => handleChangeStatus("4")}>Đã hủy</div>
                        <div className={type === "5" ? "action-status__item active" : "action-status__item"} onClick={() => handleChangeStatus("5")}>Giao thất bại</div>
                    </div>
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
                        {
                            orderFilter.map((orders) => (
                                <tr key={orders.madh}>
                                    <td>{orders.madh}</td>
                                    <td>{orders.tennguoinhan}</td>
                                    <td>{orders.diachinhan}</td>
                                    <td>{orders.sdtnguoinhan}</td>
                                    <td>{orders.ngaylap}</td>
                                    <td className="order-total">{VND.format(orders.tongtien)}</td>
                                    {/* <td className="order-status">{orders.trangThai.trangthai}</td> */}
                                    <td className={orders.trangThai.matrangthai === 1
                                        ? "pending"
                                        : (orders.trangThai.matrangthai === 2
                                            ? "to-receive"
                                            : (orders.trangThai.matrangthai === 3
                                                ? "completed"
                                                : (orders.trangThai.matrangthai === 4
                                                    ? "cancelled"
                                                    : (orders.trangThai.matrangthai === 5
                                                        ? "failled"
                                                        : "confirmed"))))}>{orders.trangThai.trangthai}
                                    </td>
                                    <td>
                                        <button className="btn btn-primary btn-order" onClick={() => showModal(orders.madh)}>Chi tiết</button>
                                        {orders.trangThai.matrangthai === 1
                                            ? (<button className="btn btn-danger btn-order" onClick={() => showConfirm(orders.madh)}>Hủy</button>)
                                            : <Fragment />}
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
            {modal === true ? <ModalUserOrderDetail hide={closeModal} madh={madh} /> : <Fragment />}
        </>
    )
}

export default UserOrder