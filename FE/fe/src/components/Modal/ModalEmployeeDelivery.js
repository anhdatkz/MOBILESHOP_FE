import { useEffect, useState } from "react"
import apiConfig from "../../api/apiConfigs"
import style from "./Modal.module.css"

export default function ModalEmployeeDelivery(props) {
    const { hide, action, maloai } = props
    let modalStyle = {
        display: 'block',
        backgroundColor: 'rgba(0, 0, 0, 0.8)'
    }

    const [nhanviens, setNhanViens] = useState([])

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/nhanvien`)
            .then((res) => res.json())
            .then((data) => {
                setNhanViens(data)
            })
    }, [])
    console.log(nhanviens)

    return (
        <>
            <div className="modal" style={modalStyle}>
                <div className="modal-dialog modal-lg">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Chọn nhân viên giao</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={hide}></button>
                        </div>
                        <div className="modal-body">
                            <table className="table" >
                                <thead>
                                    <tr>
                                        <th>Mã nhân viên</th>
                                        <th>Họ tên</th>
                                        <th>SĐT</th>
                                        <th>Email</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {nhanviens.map((nv, index) => (
                                        <tr key={index}>
                                            <td>{nv.manv}</td>
                                            <td>{nv.hoten}</td>
                                            <td>{nv.sdt}</td>
                                            <td>{nv.email}</td>
                                            <td><input type="checkbox"/></td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-primary">Xác nhận</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
};
