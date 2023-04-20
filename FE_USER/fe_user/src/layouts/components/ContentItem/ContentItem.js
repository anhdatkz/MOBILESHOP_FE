import productImg from '../../assets/images/samsung-galaxy-s22-ultra-1-1.jpg'
import apiConfig from '../../api/apiConfigs'
import { useState, useEffect } from 'react'
import { FaStar } from 'react-icons/fa'
import "./ContentItem.css"
import { Link } from 'react-router-dom'
import { scrollTop } from '../../App';
import { useDispatch } from 'react-redux'
import { addToCart } from '../../features/cartSlice'
import { formatTien, caculate } from '../../ultils/Format'

function ContentItem(props) {
    const { title } = props
    const dispatch = useDispatch()

    const [loaiSP, setLoaiSP] = useState([])
    const [cartItems, setCartItems] = useState([])

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/loaisanpham`)
            .then((res) => res.json())
            .then((data) => {
                setLoaiSP(data)
                console.log(data)
            })
    }, [])

    const handleAddToCart = (product) => {
        dispatch(addToCart(product))
    }

    return (
        <>
            <div className='content__item'>
                <div className='content__item-title'>{title}</div>
                <div className='content__item-list'>
                    <ul className='list-product'>
                        {loaiSP.map((loaisp, index) => (
                            <li className='product' key={index}>
                                <Link to={`/detail-product/${loaisp.maloai}`} onClick={scrollTop}>
                                    <div className='product__img'>
                                        <img src={loaisp.anh} />
                                    </div>
                                    <div className='product__name'>{loaisp.tenloai}</div>
                                    <div className='product__old-price'>
                                        <div className="old-price">
                                            {formatTien(loaisp.thayDoiGiasLSP[0].giamoi, '$')}
                                        </div>
                                        <div className="percent">
                                            {loaisp.ctGiamGiaLSP[0] ? `${loaisp.ctGiamGiaLSP[0].phantram} %` : ""}
                                        </div>
                                    </div>
                                    <div className='product__new-price'>{loaisp.ctGiamGiaLSP[0]
                                        ? formatTien(caculate(loaisp),'$')
                                        : formatTien(loaisp.thayDoiGiasLSP[0].giamoi, '$')}
                                    </div>
                                    <ul className='product__star'>
                                        <li><FaStar /></li>
                                        <li><FaStar /></li>
                                        <li><FaStar /></li>
                                        <li><FaStar /></li>
                                        <li><FaStar /></li>
                                    </ul>
                                </Link>
                                {/* <button className='btn-add-cart btn btn-primary' onClick={() => handleAddToCart(loaisp)}>Thêm vào giỏ hàng</button> */}
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </>
    )
}

export default ContentItem