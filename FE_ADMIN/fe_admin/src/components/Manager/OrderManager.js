import style from "./Manager.module.css"
import { FaEdit, FaTrashAlt } from "react-icons/fa"
import { Fragment, useEffect, useState } from "react"
import ModalUserOrderDetail from "../Modal/ModalUserOrderDetail"
import apiConfig from "../../api/apiConfigs"
import ModalEmployeeDelivery from "../Modal/ModalEmployeeDelivery"
import { VND } from "../../ultils/Format"
import { toast } from "react-toastify"

function OrderManager() {
    const [orderInfo, setOrderInfo] = useState([])
    const [modal, setModal] = useState(false)
    const [madh, setMaDH] = useState("")
    const [type, setType] = useState("")
    const [action, setAction] = useState("")

    const handleChangeStatus = (matrangthai) => {
        setType(matrangthai)
    }

    const showModal = (madh, action) => {
        setMaDH(madh)
        action === "" ? setAction("") : setAction("edit")
        console.log("madh" + madh)
        return setModal(true)
    }

    const closeModal = () => {
        setModal(false)
        console.log(modal)
    }

    const handleDelivered = (madh) => {
        setAction("delete")
        fetch(`${apiConfig.baseUrl}/donhang`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: madh,
        })
            .then((response) => response.json())
            .then((data) => {
                toast.success(data.message, {
                    position: "top-center"
                })
                console.log('Success:', data);
                setAction("")
            })
            .catch((error) => {
                toast.error("Duyệt đơn thất bại!", {
                    position: "top-center"
                })
                console.error('Error:', error);
            });
    }

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/donhang/${type === "" ? "" : ("trangthai/" + type)}`)
            .then((res) => res.json())
            .then((data) => {
                setOrderInfo(data)
                console.log(data)
            })
    }, [type, modal, action])

    return (
        <>
            <div className={style["manager"]}>
                <div className="manager-order">
                    <div className={style["order-header"]}>
                        <h2 className={style["title"]}>Danh sách đơn hàng</h2>
                        <div className={style["action-status"]}>
                            <div className={type === "" ? `${style["action-status__item"]} ${style["active"]}` : style["action-status__item"]} onClick={() => handleChangeStatus("")}>Tất cả</div>
                            <div className={type === "1" ? `${style["action-status__item"]} ${style["active"]}` : style["action-status__item"]} onClick={() => handleChangeStatus("1")}>Chờ duyệt</div>
                            <div className={type === "2" ? `${style["action-status__item"]} ${style["active"]}` : style["action-status__item"]} onClick={() => handleChangeStatus("2")}>Đang giao</div>
                            <div className={type === "3" ? `${style["action-status__item"]} ${style["active"]}` : style["action-status__item"]} onClick={() => handleChangeStatus("3")}>Đã giao</div>
                        </div>
                    </div>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Mã ĐH</th>
                                <th>Người nhận</th>
                                <th className={style['address']}>Địa chỉ</th>
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
                                    <td className={style['address']}>{orders.diachinhan}</td>
                                    <td>{orders.sdtnguoinhan}</td>
                                    <td>{orders.ngaylap}</td>
                                    <td className="order-total">{VND.format(orders.tongtien)} </td>
                                    <td className={orders.trangThai.matrangthai === 1
                                        ? `${style["to-ship"]}`
                                        : (orders.trangThai.matrangthai === 2 ? `${style["to-receive"]}` : style["completed"])}>{orders.trangThai.trangthai}</td>
                                    <td className={style["order-action"]}>
                                        <button className="btn btn-primary btn-order" onClick={() => showModal(orders.madh, "")}>Chi tiết</button>
                                    </td>
                                    <td>
                                        {(orders.trangThai.matrangthai === 1)
                                            ? (<div className={style["order-edit"]} onClick={() => showModal(orders.madh, "edit")}><FaEdit /></div>)
                                            : ((orders.trangThai.matrangthai === 2)
                                                ? (<div className={style["order-edit"]} onClick={() => handleDelivered(orders.madh)}><FaEdit /></div>)
                                                : <Fragment></Fragment>)
                                        }
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
            {modal === true ? (action === "" ? <ModalUserOrderDetail hide={closeModal} madh={madh} /> : <ModalEmployeeDelivery hide={closeModal} madh={madh} />) : <Fragment />}
        </>
    )
}

export default OrderManager