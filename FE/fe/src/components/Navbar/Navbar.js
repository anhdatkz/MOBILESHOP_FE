import './Navbar.css'
import { useEffect, useState } from 'react'
import apiConfigs from "../../api/apiConfigs"
import { FaAngleDown } from "react-icons/fa"
import { Link } from 'react-router-dom'
function Navbar() {
    const [hangs, setHangs] = useState([])

    useEffect(() => {
        fetch(`${apiConfigs.baseUrl}/hang`)
            .then((res) => res.json())
            .then((data) => {
                setHangs(data)
                console.log(data)
            })
    }, [])

    return (
        <>
            <nav id='nav'>
                <ul className='nav__list'>
                    <li className='nav-item'><Link to="/">Trang chủ</Link></li>
                    <li className='nav-item'>
                        <a>Hãng <FaAngleDown></FaAngleDown></a>
                        <ul className='subnav'>
                            <li className='subnav-item'>
                                <Link to="/brand/all">Tất cả sản phẩm</Link>
                            </li>
                            {hangs.map((hang, index) => (
                                <li className='subnav-item' key={index}>
                                    <Link to={`/brand/${hang.mahang}`}>{hang.tenhang}</Link>
                                </li>
                            ))}
                        </ul>
                    </li>
                    <li className='nav-item'><a>Liên hệ</a></li>
                    <li className='nav-item'><a>Giới thiệu</a></li>
                    <li className='nav-item'>
                        <a>Khác <FaAngleDown></FaAngleDown></a>
                        <ul className='subnav'>
                            <li className='subnav-item'><Link to={"/"}>Hướng dẫn thanh toán</Link></li>
                            <li className='subnav-item'><Link to={"/"}>Chính sách bảo hành</Link></li>
                            <li className='subnav-item'><Link to={"/"}>Chính sách vận chuyển</Link></li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </>
    )
}

export default Navbar