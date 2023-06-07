import { useEffect, useState } from "react"
import { toast } from "react-toastify"
import apiConfig from "../../api/apiConfigs"
import style from "./Modal.module.css"
import { VND } from "../../ultils/Format"


export default function ModalOrderByEmployee(props) {

    const { hide, manvgiao } = props

    const [orders, setOrders] = useState([])


    let modalStyle = {
        display: 'block',
        backgroundColor: 'rgba(0, 0, 0, 0.8)'
    }

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/donhang/nv/${manvgiao}`)
            .then((res) => res.json())
            .then((data) => {
                setOrders(data)
            })
    }, [])
    return (
        <>
            <div className="modal" style={modalStyle}>
                <div className=".modal-dialog-scrollable modal-xg p-5">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Chọn nhân viên giao</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={hide}></button>
                        </div>
                        <div className="modal-body">
                            <table className="table" >
                                <thead>
                                    <tr>
                                        <th>Mã đơn hàng</th>
                                        <th>Người nhận</th>
                                        <th>Địa chỉ</th>
                                        <th>SĐT</th>
                                        <th>Tổng tiền</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {orders.map((order, index) => (
                                        <tr key={index}>
                                            <td>{order.madh}</td>
                                            <td>{order.tennguoinhan}</td>
                                            <td>{order.diachinhan}</td>
                                            <td>{order.sdtnguoinhan}</td>
                                            <td>{VND.format(order.tongtien)}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                            <h5>Tổng đơn: {orders.length}</h5>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
};
