import style from "./Manager.module.css"
import { FaEdit, FaTrashAlt, FaList, FaSearch } from "react-icons/fa"
import { useState, useEffect, Fragment, useRef } from 'react'
import apiConfig from '../../api/apiConfigs'
import ModalProduct from "../Modal/ModalProduct"
import { caculate, formatTien } from "../../ultils/Format"

function ProductManager() {
    const [lsp, setlsp] = useState([])
    const [modal, setModal] = useState(false)
    const [action, setAction] = useState("")
    const [maLSP, setMaLSP] = useState("")

    const searchRef = useRef()

    const showModalEdit = (mahang) => {
        console.warn(mahang)
        setAction("delete")
        setModal(true)
    }

    const showModalAdd = () => {
        setAction("add")
        setModal(true)
    }

    const closeModal = () => {
        setModal(false)
        console.log(modal)
    }


    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/lsp`)
            .then((res) => res.json())
            .then((data) => {
                setlsp(data)
                console.log(data)
            })
    }, [])

    const handleFilterProduct = (e) => {
        let productsFilter = []
        lsp.forEach((product) => {
            const tenloai = product.tenloai.toLowerCase()
            if (tenloai.includes(searchRef.current.value.toLowerCase())) {
                productsFilter.push(product)
            }
        })

        console.log(productsFilter)
        setlsp(productsFilter)
        searchRef.current.value = ""
    }

    return (
        <>
            <div className={style["manager"]}>
                <div className="product-manager">
                    <div className="product-header d-flex justify-content-between">
                        <h2 className="title">Loại sản phẩm</h2>
                        <div className={style['search']}>
                            <div className={style['search-box']}>
                                <div className={style['search-icon']} onClick={() => handleFilterProduct()}>
                                    <FaSearch></FaSearch>
                                </div>
                                <input className={style['search-input']} ref={searchRef} placeholder='Tìm kiếm sản phẩm...' />
                            </div>
                        </div>
                        <button className="btn btn-primary" onClick={() => showModalAdd()}>Thêm</button>
                    </div>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Mã loại</th>
                                <th>Ảnh</th>
                                <th>Tên loại</th>
                                <th>Số lượng</th>
                                <th>Giá</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {lsp.map((lsp, index) => (
                                <tr key={index}>
                                    <td>{index + 1}</td>
                                    <td>{lsp.maloai}</td>
                                    <td><img className={style["product-img-manager"]} src={lsp.anh} alt="" /></td>
                                    <td>{lsp.tenloai}</td>
                                    <td>{lsp.soluongton}</td>
                                    <td>{lsp.ctGiamGiaLSP[0]
                                        ? formatTien(caculate(lsp), '$')
                                        : formatTien(lsp.gia, '$')} </td>
                                    <td className="d-flex justify-content-between">
                                        <div className={style["edit"]} onClick={() => showModalAdd()}><FaEdit /></div>
                                        <div className={style["delete"]} onClick={() => showModalAdd()}><FaTrashAlt /></div>
                                        <div className={style["detail-list"]} onClick={() => showModalAdd()}><FaList /></div>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
            {modal === true ? <ModalProduct hide={closeModal} maLSP={maLSP} action={action} /> : <Fragment />}
        </>
    )
}

export default ProductManager