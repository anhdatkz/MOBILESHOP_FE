import { Fragment, useEffect, useState } from "react"
import apiConfig from "../../api/apiConfigs"
import { VND } from "../../ultils/Format"
import ModalUserOrderDetail from "../Modal/ModalUserOrderDetail"
import "./User.css"
import { toast } from "react-toastify"

export default function UserOrderHistory(params) {
    const [orderInfo, setOrderInfo] = useState([])
    const [modal, setModal] = useState(false)
    const [madh, setMaDH] = useState("")
    const [type, setType] = useState("")
    const [orderFilter, setOrderFilter] = useState([])
    const [reload, setReload] = useState(false)


    const username = localStorage.getItem('username')

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/donhang/kh/${username}`)
            .then((res) => res.json())
            .then((data) => {
                setOrderInfo(data)
                setOrderFilter(data)
                console.log(data)
            })
    }, [reload])

    const showModal = (madh) => {
        setMaDH(madh)
        console.log("idGioHang" + madh)
        return setModal(true)
    }

    const closeModal = () => {
        setModal(false)
        console.log(modal)
    }

    return (
        <>
        </>
    )
};
