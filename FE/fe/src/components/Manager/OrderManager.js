import style from "./Manager.module.css"
import {FaEdit, FaTrashAlt} from "react-icons/fa"
import { Fragment, useEffect, useState } from "react"
import ModalUserOrderDetail from "../Modal/ModalUserOrderDetail"
import apiConfig from "../../api/apiConfigs"
import ModalEmployeeDelivery from "../Modal/ModalEmployeeDelivery"

function OrderManager(){
    const [orderInfo, setOrderInfo] = useState([])
    const [modal, setModal] = useState(false)
    const [idGioHang, setIdGioHang] = useState("")
    const [type, setType] = useState("")
    const [action, setAction] = useState("")

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/giohang/${type === "" ? "" : ("trangthai/" + type)}`)
            .then((res) => res.json())
            .then((data) => {
                setOrderInfo(data)
                console.log(data)
            })
    }, [type])

    const handleChangeStatus = (matrangthai) => {
        setType(matrangthai)
    }

    const showModal = (idgiohang, action) => {
        setIdGioHang(idgiohang)
        action === "" ? setAction("") : setAction("edit")
        console.log("idGioHang" + idGioHang)
        return setModal(true)
    }

    const closeModal = () => {
        setModal(false)
        console.log(modal)
    }

    return(
        <>
            <div className={style["manager"]}>
                <div className="manager-order">
                    <div className={style["order-header"]}>
                        <h2 className={style["title"]}>Danh sách đơn hàng</h2>
                        <div className={style["action-status"]}>
                           <div className={type === "" ? `${style["action-status__item"]} ${style["active"]}` : style["action-status__item"]} onClick={() => handleChangeStatus("")}>Tất cả</div>
                           <div className={type === "1" ? `${style["action-status__item"]} ${style["active"]}` : style["action-status__item"]} onClick={() => handleChangeStatus("1")}>Đang chờ</div>
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
                            <tr key={orders.idgiohang}>
                                <td>{orders.idgiohang}</td>
                                <td>{orders.tennguoinhan}</td>
                                <td className={style['address']}>{orders.diachinhan}</td>
                                <td>{orders.sdtnguoinhan}</td>
                                <td>{orders.ngay}</td>
                                <td className="order-total">{orders.tongtien} $</td>
                                <td className={orders.trangThai.matrangthai === 1 
                                    ? `${style["to-ship"]}` 
                                    : (orders.trangThai.matrangthai === 2 ? `${style["to-receive"]}` : style["completed"])}>{orders.trangThai.trangthai}</td>
                                <td className={style["order-action"]}>
                                    <button className="btn btn-primary btn-order" onClick={() => showModal(orders.idgiohang,"")}>Chi tiết</button>
                                    {orders.trangThai.matrangthai === 1 
                                    ? (<div className={style["order-edit"]} onClick={() => showModal(orders.idgiohang, "edit")}><FaEdit /></div>) 
                                    : <Fragment></Fragment>}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                    </table>
                </div>
            </div>
            {modal === true ? (action === "" ? <ModalUserOrderDetail hide={closeModal} idgiohang={idGioHang}/> : <ModalEmployeeDelivery hide={closeModal} idgiohang={idGioHang}/>) : <Fragment/>}
        </>
    )
}

export default OrderManager