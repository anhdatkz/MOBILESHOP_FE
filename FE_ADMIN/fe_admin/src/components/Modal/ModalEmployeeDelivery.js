import { useEffect, useState } from "react"
import { toast } from "react-toastify"
import apiConfig from "../../api/apiConfigs"
import { manvLogin } from "../Login/Login"
import style from "./Modal.module.css"

export default function ModalEmployeeDelivery(props) {
    const { hide, madh, manvduyet } = props

    const [manvgiao, setManvgiao] = useState("")
    const [manvLogin, setManvLogin] = useState("")

    let modalStyle = {
        display: 'block',
        backgroundColor: 'rgba(0, 0, 0, 0.8)'
    }

    const [nhanviens, setNhanViens] = useState([])

    const onOptionChange = (e) => {
        setManvgiao(e.target.value)
        console.log(e.target.value)
    }

    const handleSelectEmployee = (data, madh) => {
        fetch(`${apiConfig.baseUrl}/donhang/${madh}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
            .then((response) => {
                response.json()
            })
            .then((data) => {
                setManvgiao("")
                toast.success("Đơn hàng đã duyệt thành công!", {
                    position: "top-center"
                })
                console.log('Success:', data);
                hide()
            })
            .catch((error) => {
                toast.error("Duyệt đơn thất bại!", {
                    position: "top-center"
                })
                console.error('Error:', error);
                hide()
            });
    }

    const handleDelivery = () => {

        if (manvgiao !== "") {
            let formData = {
                matrangthai: 2,
                manvduyet: manvLogin,
                manvgiao: manvgiao
            }
            handleSelectEmployee(formData, madh)
        } else {
            toast.warn("Vui lòng chọn nhân viên giao hàng!", {
                position: "top-center"
            })
        }
    }


    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/nhanvien`)
            .then((res) => res.json())
            .then((data) => {
                setNhanViens(data)
            })

        fetch(`${apiConfig.baseUrl}/nhanvien/tk/${localStorage.getItem("username")}`)
            .then((response) => {
                if (response.ok) {
                    return response.json()
                }
                throw Error(response.status)
            })
            .then((data) => {
                setManvLogin(data.manv.trim())
                console.log(data.manv.trim())
            })
    }, [])
    console.log(nhanviens)
    console.log("manvLogin : " + manvLogin)

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
                                            <td><input type="radio" name="nhanvien" value={nv.manv} onChange={onOptionChange} /></td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-primary" onClick={handleDelivery}>Xác nhận</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
};
