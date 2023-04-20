import { useEffect, useRef, useState } from "react"
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
    const [rams, setRams] = useState([])
    const [roms, setRoms] = useState([])
    const [lsp, setLSP] = useState([])
    const [employee, setEmployee] = useState({})

    let maloaiRef = useRef("")
    let anhRef = useRef("")
    let tenloaiRef = useRef("")
    let motaRef = useRef("")
    let chipRef = useRef("")
    let mahdhRef = useRef("")
    let pinRef = useRef("")
    let manhinhRef = useRef("")
    let soluongtonRef = useRef("")
    let thoigianbhRef = useRef("")
    let giaRef = useRef("")
    let ramRef = useRef("")
    let romRef = useRef("")
    let mahangRef = useRef("")
    let ramatRef = useRef("")

    let modalStyle = {
        display: 'block',
        backgroundColor: 'rgba(0, 0, 0, 0.8)'
    }

    // let today = new Date();
    // let dd = String(today.getDate()).padStart(2, '0');
    // let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    // let yyyy = today.getFullYear();
    // today = yyyy + '-' + mm + '-' + dd;


    // lấy thông tin hãng
    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/hang`)
            .then((res) => res.json())
            .then((data) => {
                setHangs(data)
                console.log(data)
            })

        fetch(`${apiConfig.baseUrl}/ram`)
            .then((res) => res.json())
            .then((data) => {
                setRams(data)
                console.log(data)
            })

        fetch(`${apiConfig.baseUrl}/rom`)
            .then((res) => res.json())
            .then((data) => {
                setRoms(data)
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

    useEffect(() => {
        if (action === "edit" || action === "detail") {
            fetch(`${apiConfig.baseUrl}/loaisanpham/${maloai}`)
                .then((res) => res.json())
                .then((data) => {
                    setLSP(data)
                    console.log(data)
                })
        }
    }, [])


    //Chỉnh sửa thông tin sản phấm
    const handleEditProduct = (data, maloai) => {
        fetch(`${apiConfig.baseUrl}/loaisanpham/${maloai}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem("token")
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
            ram: 0,
            rom: 0,
            mahdh: "",
            pin: 0,
            manhinh: "",
            soluongton: 0,
            thoigianbh: 0,
            mahang: "",
            gia: 0,
            ramat: ""
        },
        validationSchema: Yup.object({
            maloai: Yup.string()
                .required("Mã loại không được rỗng!"),
            // tenloai: Yup.string()
            //     .required("Tên loại không được rỗng!"),
            // chip: Yup.string()
            //     .required("Chip không được rỗng!"),
            // anh: Yup.string()
            //     .required("Ảnh không được rỗng!"),
            // mahdh: Yup.string()
            //     .required("Hệ điều hành không được rỗng!"),
            // pin: Yup.string()
            //     .required("pin không được rỗng!"),
            // manhinh: Yup.string()
            //     .required("Màn hình không được rỗng!"),
            // gia: Yup.string()
            //     .required("Giá không được rỗng!"),
            // soluong: Yup.string()
            //     .required("Số lượng không được rỗng!"),
            // thoigianbh: Yup.string()
            //     .required("Số lượng không được rỗng!"),
        }),
        onSubmit: (values) => {
            console.log("SUBMIT")
            const formData = {
                maloai: values.maloai.trim(),
                tenloai: values.tenloai.trim(),
                anh: values.anh.trim(),
                mota: values.mota.trim(),
                chip: values.chip.trim(),
                ram: parseInt(values.ram),
                rom: parseInt(values.rom),
                mahdh: values.mahdh.trim(),
                pin: parseInt(values.pin),
                manhinh: values.manhinh.trim(),
                soluongton: parseInt(values.soluongton),
                thoigianbh: parseInt(values.thoigianbh),
                mahang: values.mahang.trim(),
                gia: parseInt(values.gia),
                ramat: values.ramat
            }

            scrollTop()
        }
    })
    const handleAddProduct = () => {
        const formData = {
            maloai: maloaiRef.current.value.trim(),
            tenloai: tenloaiRef.current.value.trim(),
            anh: anhRef.current.value.trim(),
            mota: motaRef.current.value.trim(),
            chip: chipRef.current.value.trim(),
            ram: parseInt(ramRef.current.value),
            rom: parseInt(romRef.current.value),
            hedieuhanh: mahdhRef.current.value.trim(),
            pin: parseInt(pinRef.current.value),
            manhinh: manhinhRef.current.value.trim(),
            soluongton: parseInt(soluongtonRef.current.value),
            thoigianbh: parseInt(thoigianbhRef.current.value),
            mahang: mahangRef.current.value.trim(),
            gia: parseInt(giaRef.current.value),
            ramat: ramatRef.current.value
        }

        fetch(`${apiConfig.baseUrl}/loaisanpham`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem("token")
            },
            body: JSON.stringify(formData),
        })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                if (data.success === true) {
                    toast.success(data.message, {
                        position: "top-center"
                    })
                }
                else {
                    toast.warn(data.message, {
                        position: "top-center"
                    })
                }
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

    console.log("========================")
    console.log(formik.values)

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
                        <form action="" className="modal-body">
                            <div className={style["form-product"]}>
                                <div className={style["modal-body-item"]}>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Mã loại</label>
                                        <input name="maloai" type="text" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={maloaiRef} />
                                        {formik.touched.maloai && formik.errors.maloai ? (
                                            <div className={style["validate"]}>{formik.errors.maloai}</div>
                                        ) : null}
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Tên loại</label>
                                        <input name="tenloai" type="text" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={tenloaiRef} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Ảnh</label>
                                        <input name="anh" type="text" className="form-control" ref={anhRef} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Hãng</label>
                                        <select name="hang" id="hang" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={mahangRef} >
                                            {hangs.map((hang, index) => (
                                                <option value={hang.mahang} key={index}>{hang.tenhang}</option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Pin</label>
                                        <input name="pin" type="text" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={pinRef} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Giá</label>
                                        <input name="gia" type="number" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={giaRef} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Mô tả</label>
                                        <textarea name="mota" type="text" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={motaRef} />
                                    </div>
                                </div>
                                <div className={style["modal-body-item"]}>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Chip</label>
                                        <input name="chip" type="text" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={chipRef} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Ram</label>
                                        <select name="ram" id="ram" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={ramRef}>
                                            {rams.map((ram) => (
                                                <option value={ram.maram} key={ram.maram}>{ram.dungluong.trim()}</option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Rom</label>
                                        <select name="rom" id="rom" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={romRef}>
                                            {roms.map((rom) => (
                                                <option value={rom.marom} key={rom.marom}>{rom.dungluong.trim()}</option>
                                            ))}
                                        </select>
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Hệ điều hành</label>
                                        <input name="mahdh" type="text" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={mahdhRef} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Màn hình</label>
                                        <input name="manhinh" type="text" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={manhinhRef} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Số lượng</label>
                                        <input name="soluongton" type="number" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={soluongtonRef} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Thời gian bảo hành</label>
                                        <input name="thoigianbh" type="number" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={thoigianbhRef} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Ra mắt</label>
                                        <input name="ramat" type="date" className="form-control" defaultValue="2017-01-01" onChange={formik.handleChange} onBlur={formik.handleBlur}
                                            ref={ramatRef} />
                                    </div>
                                </div>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-primary" onClick={handleAddProduct} >Lưu</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ModalProduct