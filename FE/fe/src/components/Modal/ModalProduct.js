import { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import { toast } from "react-toastify"
import apiConfig from "../../api/apiConfigs"
import { useFormik } from 'formik';
import * as Yup from "yup"
import style from "./Modal.module.css"
import { scrollTop, username } from "../../App";

function ModalProduct(props) {
    const { hide, action, maloai } = props
    const [hangs, setHangs] = useState([])
    const [employee, setEmployee] = useState({})

    let rams = [2, 4, 6, 8, 12]
    let roms = ["16 GB", "32 GB", "64 GB", "128 GB", "256 GB", "512 GB", "1 TB"]

    let modalStyle = {
        display: 'block',
        backgroundColor: 'rgba(0, 0, 0, 0.8)'
    }

    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    let yyyy = today.getFullYear();
    today = dd + '-' + mm + '-' + yyyy;


    // lấy thông tin hãng
    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/hang`)
            .then((res) => res.json())
            .then((data) => {
                setHangs(data)
                console.log(data)
            })
    }, [])

    //lấy thông tin nhân viên đăng nhập
    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/nhanvien/tk/${username}`)
            .then((res) => res.json())
            .then((data) => {
                setEmployee(data)
                console.log(data)
            })
    }, [])


    // Thêm giá
    const handleAddPrice = (dataPrice) => {
        fetch(`${apiConfig.baseUrl}/tdg`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dataPrice),
        })
            .then((response) => {
                response.json()
            })
            .then((data) => {
                // toast.success("Thay đổi giá thành công!", {
                //     position: "top-center"
                // })
                console.log('Success:', data);
                hide()
            })
            .catch((error) => {
                // toast.error("Thêm sản phẩm thất bại!", {
                //     position: "top-center"
                // })
                console.error('Error:', error);
                hide()
            });
    }


    //Thêm sản phẩm
    const handleAddBrand = (dataProduct, dataPrice) => {
        fetch(`${apiConfig.baseUrl}/loaisanpham`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dataProduct),
        })
            .then((response) => {
                response.json()
                handleAddPrice(dataPrice)
            })
            .then((data) => {
                toast.success("Thêm sản phẩm thành công!", {
                    position: "top-center"
                })
                console.log('Success:', data);
                hide()
            })
            .catch((error) => {
                toast.error("Thêm sản phẩm thất bại!", {
                    position: "top-center"
                })
                console.error('Error:', error);
                hide()
            });
    }

    //Chỉnh sửa thông tin sản phấm
    const handleEditBrand = (data, maloai) => {
        fetch(`${apiConfig.baseUrl}/loaisanpham/${maloai}`, {
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
                toast.success("Chỉnh sửa thông tin thành công!", {
                    position: "top-center"
                })
                console.log('Success:', data);
                hide()
            })
            .catch((error) => {
                toast.error("Chỉnh sửa thất bại!", {
                    position: "top-center"
                })
                console.error('Error:', error);
                hide()
            });
    }


    const formik = useFormik({
        initialValues: {
            maloai: "",
            tenloai: "",
            anh: "",
            mota: "",
            chip: "",
            ram: 4,
            rom: 128,
            hedieuhanh: "",
            pin: "",
            manhinh: "",
            soluongton: 0,
            thoigianbh: 0,
            mahang: "",
            gia: ""
        },
        validationSchema: Yup.object({
            
        }),
        onSubmit: (values) => {
            const formData = {
                maloai: values.maloai,
                tenloai: values.tenloai,
                anh: values.anh,
                mota:  values.mota,
                chip: values.chip,
                ram: values.ram,
                rom: values.rom,
                hedieuhanh: values.hedieuhanh,
                pin: values.pin,
                manhinh: values.manhinh,
                soluongton: values.soluongton,
                thoigianbh: values.thoigianbh,
                mahang: values.mahang
            }

            scrollTop()
        }
    })

    // const [productImg, setProductImg] = useState()

    // useEffect(() => {
    //     return () => {
    //         productImg && URL.revokeObjectURL(productImg.preview)
    //     }
    // }, [productImg])

    // const handlePreviewImg = (e) =>{
    //     const file = e.target.files[0]
    //     file.preview  = URL.createObjectURL(file)
    //     setProductImg(file)
    //     console.log(file)
    // }

    return (
        <>
            <div className="modal" style={modalStyle}>
                <div className="modal-dialog modal-lg">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Loại sản phẩm</h5>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={hide}></button>
                        </div>
                        <div className="modal-body">
                            <form action="" className={style["form-product"]}>
                                <div className={style["modal-body-item"]}>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Mã loại</label>
                                        <input type="text" className="form-control" />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Tên loại</label>
                                        <input type="text" className="form-control" />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Ảnh</label>
                                        <input type="text" className="form-control" />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Hãng</label>
                                        <select name="hang" id="hang" className="form-control">
                                            {hangs.map((hang, index) => (
                                                <option value={hang.mahang} key={index}>{hang.tenhang}</option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Pin</label>
                                        <input type="text" className="form-control" />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Giá</label>
                                        <input type="number" className="form-control" />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Mô tả</label>
                                        <textarea type="text" className="form-control" />
                                    </div>
                                </div>
                                <div className={style["modal-body-item"]}>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Chip</label>
                                        <input type="text" className="form-control" />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Ram</label>
                                        <select name="ram" id="ram" className="form-control">
                                            {rams.map((ram) => (
                                                <option value={ram} key={ram}>{ram} GB</option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Rom</label>
                                        <select name="rom" id="rom" className="form-control">
                                            {roms.map((rom) => (
                                                <option value={rom} key={rom}>{rom}</option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Hệ điều hành</label>
                                        <select name="ram" id="ram" className="form-control">
                                            <option value="volvo">Android</option>
                                            <option value="saab">IOS</option>
                                        </select>
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Màn hình</label>
                                        <input type="text" className="form-control" />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Số lượng</label>
                                        <input type="number" className="form-control" />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Thời gian bảo hành</label>
                                        <input type="number" className="form-control" defaultValue={0} />
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-primary">Lưu</button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ModalProduct