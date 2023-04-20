import { useEffect, useRef } from "react"
import { useState } from "react"
import { toast } from "react-toastify"
import apiConfig from "../../api/apiConfigs"
import { VND } from "../../ultils/Format"
import style from "./Manager.module.css"

function ReportManager() {
    const [orders, setOrders] = useState([])

    let ngaybdRef = useRef()
    let ngayktRef = useRef()

    const handleGetOrder = () => {
        const ngaybd = ngaybdRef.current.value
        const ngaykt = ngayktRef.current.value

        if (ngaybd <= ngaykt) {
            fetch(`${apiConfig.baseUrl}/donhang/${ngaybd}/${ngaykt}`)
                .then((res) => res.json())
                .then((data) => {
                    setOrders(data)
                    console.log(data)
                })
                .catch(() => {
                    setOrders([])
                })
        } else {
            toast.warn("Ngày bắt đầu phải nhỏ hơn ngày kết thúc", {
                position: "top-center"
            })
        }
    }

    useEffect(() => {

    }, [])


    return (
        <>
            <div className={style["manager"]}>
                <div className="report">
                    <div className="report-header d-flex justify-content-between">
                        <h2 className="title">Thông kê doanh thu</h2>
                    </div>
                    <div className="report-content d-flex justify-content-between align-items-center mt-2">
                        <div>
                            <label className="fw-bold me-3">Từ</label>
                            <input type="date" id="start" min="2018-03" defaultValue="2018-05" ref={ngaybdRef} />
                        </div>
                        <div>
                            <label className="fw-bold me-3">Đến</label>
                            <input type="date" id="end" ref={ngayktRef} />
                        </div>
                        <button className="btn btn-primary me-5" onClick={handleGetOrder}>Bắt đầu</button>
                    </div>
                </div>

                {
                    orders.length === 0
                        ? (
                            <div>
                                <h2> Không có đơn hàng nào</h2>
                            </div>
                        )
                        : (
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
                                    </tr>
                                </thead>
                                <tbody>
                                    {orders.map((orders) => (
                                        <tr key={orders.madh}>
                                            <td>{orders.madh}</td>
                                            <td>{orders.tennguoinhan}</td>
                                            <td className={style['address']}>{orders.diachinhan}</td>
                                            <td>{orders.sdtnguoinhan}</td>
                                            <td>{orders.ngaylap}</td>
                                            <td className="order-total">{VND.format(orders.tongtien)}</td>
                                            {/* <td className="order-status">{orders.trangThai.trangthai}</td> */}
                                            <td className={orders.trangThai.matrangthai === 1
                                                ? `${style["to-ship"]}`
                                                : (orders.trangThai.matrangthai === 2 ? `${style["to-receive"]}` : style["completed"])}>{orders.trangThai.trangthai}
                                            </td>
                                            {/* <td><button className="btn btn-primary btn-order" onClick={() => showModal(orders.madh)}>Chi tiết</button></td> */}
                                        </tr>
                                    ))}
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td><h6>Doanh thu</h6></td>
                                        <td>
                                            <h6>
                                                {VND.format(orders.reduce((acc, cur) => {
                                                    return acc + cur.tongtien
                                                }, 0))}
                                            </h6>
                                        </td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
                        )
                }
            </div>
        </>
    )
}

export default ReportManager