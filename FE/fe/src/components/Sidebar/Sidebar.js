import "./Sidebar.css"
import { NavLink, useNavigate } from "react-router-dom"

function Sidebar() {

    const navigate = useNavigate()

    const handleLogOut = () => {
        localStorage.setItem('isLogin', false)
        localStorage.setItem('username', "")
        localStorage.setItem('role', "")
        navigate("/login")
    }

    return (
        <>
            <div className="sidebar">
                <ul className="list-group">
                    <li className="list-group-item"><NavLink to="/manager/brand">Hãng</NavLink></li>
                    <li className="list-group-item"><NavLink to="/manager/product">Loại sản phẩm</NavLink></li>
                    <li className="list-group-item"><NavLink to="/manager/employee">Nhân viên</NavLink></li>
                    <li className="list-group-item"><NavLink to="/manager/order">Đơn đặt hàng</NavLink></li>    
                    <li className="list-group-item"><NavLink to="/manager/report">Thống kê doanh thu</NavLink></li>
                    <li className="list-group-item"><NavLink to="/manager/account">Thông tin của tôi</NavLink></li>
                    <li className="list-group-item" onClick={handleLogOut}><a className="logout">Đăng xuất</a></li>
                </ul>
            </div>
        </>
    )
}

export default Sidebar