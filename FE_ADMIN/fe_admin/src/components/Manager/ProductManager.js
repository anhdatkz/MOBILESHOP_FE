import style from "./Manager.module.css"
import { FaEdit, FaTrashAlt, FaList, FaSearch } from "react-icons/fa"
import { useState, useEffect, Fragment, useRef } from 'react'
import apiConfig from '../../api/apiConfigs'
import ModalProduct from "../Modal/ModalProduct"
import { caculate, formatTien, VND } from "../../ultils/Format"
import { toast } from "react-toastify"
import ModalEditProduct from "../Modal/ModalEditProduct"

function ProductManager() {
    const [lsp, setlsp] = useState([])
    const [modal, setModal] = useState(false)
    const [action, setAction] = useState("")
    const [maloai, setMaLoai] = useState("")

    const searchRef = useRef()

    const showModalEdit = (maloai) => {
        setAction("edit")
        setMaLoai(maloai)
        setModal(true)
    }

    const showModalAdd = () => {
        setAction("add")
        setModal(true)
    }

    const showModalDetail = (maloai) => {
        setAction("detail")
        setMaLoai(maloai)
        setModal(true)
    }

    const closeModal = () => {
        setModal(false)
        console.log(modal)
    }

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

    const handleDelete = (malsp) => {
        fetch(`${apiConfig.baseUrl}/loaisanpham/${malsp}`, {
            method: 'DELETE',
            headers: {
                'Authorization': localStorage.getItem("token")
            }
        })
            .then(res => res.json())
            .then((data) => {
                console.log("DELETE: " + data)
                if (data === true) {
                    setAction("delete")
                    toast.success("Xóa sản phẩm thành công!", {
                        position: "top-center"
                    })
                }
                else {
                    toast.warn("Sản phẩm vẫn còn hàng! Không thể xóa!", {
                        position: "top-center"
                    })
                }
                // handleGetHang()
            })
    }

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/lsp`)
            .then((res) => res.json())
            .then((data) => {
                setlsp(data)
                console.log(data)
            })
    }, [action, modal])

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
                                <th></th>
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
                                        ? VND.format(caculate(lsp))
                                        : VND.format(lsp.gia)} </td>
                                    <td className="">
                                        <div className={style["edit"]} onClick={() => showModalEdit(lsp.maloai)}><FaEdit /></div>
                                        {/* <div className={style["delete"]} onClick={() => showModalAdd()}><FaTrashAlt /></div>
                                        <div className={style["detail-list"]} onClick={() => showModalAdd()}><FaList /></div> */}
                                    </td>
                                    <td><div className={style["delete"]} onClick={() => handleDelete(lsp.maloai)}><FaTrashAlt /></div></td>
                                    <td><div className={style["detail-list"]} onClick={() => showModalDetail(lsp.maloai)}><FaList /></div></td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
            {modal === true ? (action === "add" ? <ModalProduct hide={closeModal} maloai={maloai} action={action} /> : <ModalEditProduct hide={closeModal} maloai={maloai} action={action}></ModalEditProduct>) : <Fragment />}
        </>
    )
}

export default ProductManager