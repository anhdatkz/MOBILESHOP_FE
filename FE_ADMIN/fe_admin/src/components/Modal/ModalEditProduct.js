import { Fragment, useEffect, useRef, useState } from "react"
import { Link } from "react-router-dom"
import { toast } from "react-toastify"
import apiConfig from "../../api/apiConfigs"
import { useFormik } from 'formik';
import * as Yup from "yup"
import style from "./Modal.module.css"
import { scrollTop, username } from "../../App";

function ModalEditProduct(props) {
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
        fetch(`${apiConfig.baseUrl}/nhanvien/profile`, {
            headers: {
                'Authorization': localStorage.getItem("token")
            },
        })
            .then((res) => res.json())
            .then((data) => {
                setEmployee(data)
                console.log(data)
            })
    }, [])

    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/loaisanpham/${maloai}`)
            .then((res) => res.json())
            .then((data) => {
                setLSP(data)
                console.log(data)
            })
    }, [])



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
            scrollTop()
        }
    })


    const handleEditProduct = () => {
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
        console.log(formData)

        fetch(`${apiConfig.baseUrl}/loaisanpham/${maloai}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem("token")
            },
            body: JSON.stringify(formData),
        })
            .then((response) => response.json())
            .then((data) => {
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
                toast.error("Chỉnh sửa sản phẩm thất bại!", {
                    position: "top-center"
                })
                console.error('Error:', error);
                hide()
            });
    }

    console.log("========================")
    console.log(formik.values)

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
                                        <input name="maloai" type="text" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={maloaiRef} defaultValue={lsp.maloai} readOnly />
                                        {formik.touched.maloai && formik.errors.maloai ? (
                                            <div className={style["validate"]}>{formik.errors.maloai}</div>
                                        ) : null}
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Tên loại</label>
                                        <input name="tenloai" type="text" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={tenloaiRef} defaultValue={lsp.tenloai} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Ảnh</label>
                                        <input name="anh" type="text" className="form-control" ref={anhRef} defaultValue={lsp.anh} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Hãng</label>
                                        {action === "edit"
                                            ? (<select name="hang" id="hang" className="form-control" onChange={formik.handleChange}
                                                ref={mahangRef}>
                                                {hangs.map((hang, index) => (
                                                    <option value={hang.mahang} key={index} selected={hang.tenhang === lsp.mahang ? true : false}>{hang.tenhang}</option>
                                                ))}
                                            </select>)
                                            : (<input name="hang" id="hang" className="form-control" onChange={formik.handleChange}
                                                onBlur={formik.handleBlur} ref={mahangRef} defaultValue={lsp.mahang} readOnly />)}

                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Pin</label>
                                        <input name="pin" type="text" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={pinRef} defaultValue={lsp.pin} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Giá</label>
                                        <input name="gia" type="number" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={giaRef} defaultValue={lsp.gia} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Mô tả</label>
                                        <textarea name="mota" type="text" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={motaRef} defaultValue={lsp.mota} />
                                    </div>
                                </div>
                                <div className={style["modal-body-item"]}>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Chip</label>
                                        <input name="chip" type="text" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={chipRef} defaultValue={lsp.chip} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Ram</label>
                                        {action === "edit"
                                            ? (<select name="ram" id="ram" className="form-control" onChange={formik.handleChange}
                                                onBlur={formik.handleBlur} ref={ramRef} defaultValue={lsp.ram}>
                                                {rams.map((ram) => (
                                                    <option value={ram.maram} key={ram.maram} selected={ram.dungluong === lsp.ram ? true : false}>{ram.dungluong.trim()}</option>
                                                ))}
                                            </select>)
                                            : (<input name="ram" id="ram" className="form-control" onChange={formik.handleChange}
                                                onBlur={formik.handleBlur} ref={ramRef} defaultValue={lsp.ram} />)}

                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Rom</label>
                                        {action === "edit"
                                            ? (<select name="rom" id="rom" className="form-control" onChange={formik.handleChange} onBlur={formik.handleBlur} ref={romRef}>
                                                {roms.map((rom) => (
                                                    <option value={rom.marom} key={rom.marom} selected={rom.dungluong === lsp.rom ? true : false} >{rom.dungluong.trim()}</option>
                                                ))}
                                            </select>)
                                            : (<input name="rom" id="rom" className="form-control" onChange={formik.handleChange}
                                                onBlur={formik.handleBlur} ref={romRef} defaultValue={lsp.rom} />)}
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Hệ điều hành</label>
                                        <input name="mahdh" type="text" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={mahdhRef} defaultValue={lsp.hedieuhanh} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Màn hình</label>
                                        <input name="manhinh" type="text" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={manhinhRef} defaultValue={lsp.manhinh} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Số lượng</label>
                                        <input name="soluongton" type="number" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={soluongtonRef} defaultValue={lsp.soluongton} readOnly />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Thời gian bảo hành</label>
                                        <input name="thoigianbh" type="number" className="form-control" onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={thoigianbhRef} defaultValue={lsp.thoigianbh} />
                                    </div>
                                    <div className={style["body-item"]}>
                                        <label htmlFor="">Ra mắt</label>
                                        <input name="ramat" type="date" className="form-control" defaultValue={lsp.ramat} onChange={formik.handleChange}
                                            onBlur={formik.handleBlur} ref={ramatRef} />
                                    </div>
                                </div>
                            </div>
                            <div className="modal-footer">
                                {action === "edit" ? (<button type="button" className="btn btn-primary" onClick={handleEditProduct} >Lưu</button>) : <Fragment />}
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ModalEditProduct