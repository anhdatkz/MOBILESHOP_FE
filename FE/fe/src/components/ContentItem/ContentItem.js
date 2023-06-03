import productImg from '../../assets/images/samsung-galaxy-s22-ultra-1-1.jpg'
import apiConfig from '../../api/apiConfigs'
import { useState, useEffect } from 'react'
import { FaStar } from 'react-icons/fa'
import "./ContentItem.css"
import { Link } from 'react-router-dom'
import { scrollTop } from '../../App';
import { useDispatch } from 'react-redux'
import { addToCart } from '../../features/cartSlice'
import { formatTien, caculate, VND } from '../../ultils/Format'

function ContentItem(props) {
    const { title, url } = props
    // const dispatch = useDispatch()

    const [loading, setLoading] = useState(false);
    const [loaiSP, setLoaiSP] = useState([])
    const [cartItems, setCartItems] = useState([])
    // const url = (title === "Sản phẩm mới" ? "lspnew" : "loaisanpham")

    useEffect(() => {
        setLoading(true)
        fetch(`${apiConfig.baseUrl}/${url}`)
            .then((res) => res.json())
            .then((data) => {
                setLoaiSP(data)
                setLoading(false)
                console.log(data)
            })
    }, [])

    // const handleAddToCart = (product) => {
    //     dispatch(addToCart(product))
    // }

    return (
        <>{loading
            ? (<div className="content__item">
                <div className="content__item-title">
                    <div class="spinner-border text-primary"></div>
                </div>
            </div>)
            : (<>
                <div className='content__item'>
                    <div className='content__item-title'>{title}</div>
                    <div className='content__item-list'>
                        <ul className='list-product'>
                            {loaiSP.map((loaisp, index) => (
                                <li className='product' key={index}>
                                    {title === "Sản phẩm mới"
                                        ? (<span className="product-new">New</span>)
                                        : (title === "Khuyến mãi"
                                            ? (<span className="product-sale">Sale</span>)
                                            : <span className="product-bestseller">Best Seller</span>)
                                    }
                                    <Link to={`/detail-product/${loaisp.maloai}`} onClick={scrollTop}>
                                        <div className='product__img'>
                                            <img src={loaisp.anh} />
                                        </div>
                                        <div className='product__name'>{loaisp.tenloai}</div>
                                        {/* <div className='ram-rom'>
                                        <span>{loaisp.ram.dungluong}</span>
                                        <span> - </span>
                                        <span>{loaisp.rom.dungluong}</span>
                                    </div> */}
                                        <div className='product__old-price'>
                                            <div className="old-price">
                                                {loaisp.ctGiamGiaLSP[0] ? VND.format(loaisp.gia) : ""}
                                            </div>
                                            <div className="percent">
                                                {loaisp.ctGiamGiaLSP[0] ? `${loaisp.ctGiamGiaLSP[0].phantram} %` : ""}
                                            </div>
                                        </div>
                                        <div className='product__new-price'>
                                            {loaisp.ctGiamGiaLSP[0]
                                                ? VND.format(caculate(loaisp))
                                                : VND.format(loaisp.gia)}
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
            </>)
        }
        </>
    )
}

export default ContentItem