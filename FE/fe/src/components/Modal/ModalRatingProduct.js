import { useEffect, useRef, useState } from 'react'
import { Rating } from 'react-simple-star-rating'
import style from './Modal.module.css'
import apiConfigs from '../../api/apiConfigs'
import { toast } from 'react-toastify'

function ModalRatingProduct(props) {
    const { hide, product, username } = props

    const [rating, setRating] = useState(0)
    const [userInfo, setUserInfo] = useState({})

    const motaRef = useRef()

    let modalStyle = {
        display: 'block',
        backgroundColor: 'rgba(0, 0, 0, 0.8)'
    }

    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;

    // Catch Rating value
    const handleRating = (rate) => {
        setRating(rate)
    }

    const handleSend = () => {
        let rateData = {
            cmnd: userInfo.cmnd,
            maloai: product.maloai,
            diem: rating,
            mota: motaRef.current.value.trim(),
            ngaybinhluan: today
        }
        console.log(rateData)
        fetch(`${apiConfigs.baseUrl}/danhgia`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(rateData),
        })
            .then((res) => res.json())
            .then((data) => {
                if (data.success === true) {
                    toast.success("Cảm ơn bạn đã đánh giá sản phẩm!", {
                        position: "top-center"
                    })
                } else {
                    toast.warn("Không thể đánh giá! " + data.message, {
                        position: "top-center",
                        autoClose: 3000
                    })
                }
            })
    }

    useEffect(() => {
        fetch(`${apiConfigs.baseUrl}/khachhang/profile`, {
            headers: {
                'Authorization': localStorage.getItem("token")
            },
        })
            .then((res) => res.json())
            .then((data) => {
                setUserInfo(data)
                console.log(data)
            })
    }, [])

    return (
        <>
            <div className="modal show fade" style={modalStyle}>
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Đánh giá sản phẩm</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={hide}></button>
                        </div>
                        <div className="modal-body">
                            <div className={style["product-info-rating"]}>
                                <img src={product.anh} alt="" className={style["product-img-rating"]} />
                                <div className={style["product-name-rating"]}>{product.tenloai}</div>
                            </div>
                            <Rating onClick={handleRating} className={style["rating-star"]} />
                            <div>
                                <textarea ref={motaRef} className={style["product-comment-rating"]} name="" id="" rows="3"
                                    placeholder='Mời bạn chia sẻ cảm nhận về sản phẩm ...'></textarea>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-primary" onClick={handleSend}>Gửi đánh giá</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ModalRatingProduct