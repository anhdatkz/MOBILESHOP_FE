import './ProductDetail.css'
import apiConfig from '../../api/apiConfigs'
import { useNavigate, useParams } from "react-router-dom"
import { FaPlus, FaMinus } from "react-icons/fa"
import { useState, useEffect, Fragment } from "react"
import { useDispatch } from 'react-redux'
import { addToCart } from '../../features/cartSlice'
import { caculate, VND } from '../../ultils/Format'
import axios from 'axios'
import { toast } from 'react-toastify'
import ModalRatingProduct from '../Modal/ModalRatingProduct'
import { Rating } from 'react-simple-star-rating'


function ProductsDetail() {
    const { id } = useParams()
    const [product, setProduct] = useState({})
    const [rating, setRating] = useState([])
    const [percent, setPercent] = useState("")
    const [modal, setModal] = useState(false)

    const isLogin = localStorage.getItem("isLogin")
    const username = localStorage.getItem("username")
    const navigate = useNavigate()
    const dispatch = useDispatch()

    const showModal = (product) => {
        if (isLogin === "false") {
            toast.warn("Vui lòng đăng nhập để đánh giá!", {
                position: "top-center"
            })
            navigate("/login")
        }
        return setModal(true)
    }

    const closeModal = () => {
        setModal(false)
        console.log(modal)
    }
    // 
    const handleAddToCart = async (product) => {
        if (isLogin === "true") {
            dispatch(addToCart(product))
            let cartDetailData = {
                id: {
                    idgiohangctgh: localStorage.getItem("cartId"),
                    maloaictgh: product.maloai
                },
                soluong: 1,
                tong: product.gia - product.gia * percent / 100
            }

            fetch(`${apiConfig.baseUrl}/ctgh`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(cartDetailData),
            })
                .then((response) => {
                    if (response) {
                        return response.json()
                    }
                })
                .then((data) => {
                    console.log('CTGH:', data);
                    // toast.success("Sản phẩm đã được thêm vào giỏ hàng!", {
                    //     position: "top-center"
                    // })
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        } else {
            toast.warn("Vui lòng đăng nhập!", {
                position: "top-center"
            })
            navigate("/login")
        }
    }

    async function getProduct() {
        let response = await axios.get(`${apiConfig.baseUrl}/loaisanpham/${id}`);
        let user = response.data;// Don't need await here
        setProduct(user);
        console.log(product);
    }

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/loaisanpham/${id}`)
            .then((res) => res.json())
            .then((data) => {
                setProduct(data)
                console.log(data)
                setPercent(data.giamgia[0]
                    ? data.giamgia[0].phantram
                    : 0)
            })
    }, [])

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/danhgia/${id}`)
            .then((res) => res.json())
            .then((data) => {
                setRating(data)
                console.log(data)
            })
    }, [modal])

    return (
        <>
            <div>
                <div className="content">
                    <div className="section-item">
                        <div className="section-image__main">
                            <img src={product.anh} alt="" />
                        </div>
                    </div>
                    <div className="section-item">
                        <div className="section-detail__type">
                            AD Store
                        </div>

                        <div className="section-detail__name">
                            {product.tenloai}
                        </div>

                        <div className="section-detail__description">
                            <p>{product.mota}</p>
                        </div>

                        <div className="section-detail__price">
                            {(percent === 0)
                                ? (<></>)
                                : <>
                                    <div className="old-price">{VND.format(product.gia)}</div>
                                    <div className="discount">{percent} %</div>
                                </>}
                        </div>
                        <div className="section-detail__price-discount">
                            {VND.format(product.gia - product.gia * percent / 100)}
                        </div>
                        <div className="section-detail__add-cart">
                            {/* <div className="quantity">
                            <button className="btn btn-primary des"><FaMinus /></button>
                            <input type="text" className="quan" defaultValue={1} />
                            <button className="btn btn-primary ins"><FaPlus /></button>
                        </div> */}
                            <button className="btn btn-primary" onClick={() => handleAddToCart(product)}>
                                <img src="./asset/images/icon-cart.svg" alt="" />
                                Thêm vào giỏ hàng
                            </button>
                        </div>
                    </div>
                </div>

                <div className="product-detail mt-5 ms-5">
                    <h4 className="detail-tille">Cấu hình Điện thoại {product.tenloai}</h4>
                    <ul className="list-group w-50 mb-5">
                        <li className="list-group-item d-flex">
                            <div className='w-25'>Màn hình </div>
                            <div>{product.manhinh}</div>
                        </li>
                        <li className="list-group-item d-flex">
                            <div className='w-25'>Hệ điều hành </div>
                            <div>{product.hedieuhanh}</div>
                        </li>
                        <li className="list-group-item d-flex">
                            <div className='w-25'>Chip </div>
                            <div>{product.chip}</div>
                        </li>
                        <li className="list-group-item d-flex">
                            <div className='w-25'>RAM </div>
                            <div>{product.ram}</div>
                        </li>
                        <li className="list-group-item d-flex">
                            <div className='w-25'>Bộ nhớ trong </div>
                            <div>{product.rom}</div>
                        </li>
                        <li className="list-group-item d-flex">
                            <div className='w-25'>Dung lượng Pin</div>
                            <div>{product.pin}</div>
                        </li>
                        <li className="list-group-item d-flex">
                            <div className='w-25'>Số lượng </div>
                            <div>{product.soluongton}</div>
                        </li>
                        <li className="list-group-item d-flex">
                            <div className='w-25'>Bảo hành</div>
                            <div>{product.thoigianbh} tháng</div>
                        </li>
                        <li className="list-group-item d-flex">
                            <div className='w-25'>Ra mắt</div>
                            <div>{product.ramat}</div>
                        </li>
                    </ul>
                </div>

                <div className='product-rating mt-5 ms-5'>
                    <h4 className="detail-tille">Đánh giá Điện thoại {product.tenloai}</h4>
                    <button className='btn btn-danger' onClick={() => showModal(product)}>Đánh giá ngay</button>
                    {rating.length === 0
                        ? (<h6>Chưa có đánh giá nào</h6>)
                        : (<div className='rating-list'>
                            {rating.map((rate) => (
                                <div className='rating-item'>
                                    <div className='user-rate'>{rate.tenkh}</div>
                                    <Rating size="16" initialValue={rate.diem} readonly fillColor='#ff7300'></Rating>
                                    <div>{rate.mota}</div>
                                    <div className='date-rate'>{rate.ngaybinhluan}</div>
                                </div>
                            ))}
                        </div>)
                    }
                </div>
            </div>
            {modal === true ? <ModalRatingProduct hide={closeModal} product={product} username={username} /> : <Fragment />}
        </>
    )
}

export default ProductsDetail