import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import apiConfigs from '../../../api/apiConfigs'
import style from './Register.module.css'
import { useFormik } from 'formik';
import * as Yup from 'yup'

function Register() {
    const [accountData, setAccountData] = useState({})
    const [registerData, setRegisterData] = useState({})
    let navigate = useNavigate()
    const phoneRegExp = /^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*$/

    const handleChangeAccount = (e) => {
        setAccountData({
            ...accountData,
            [e.target.name]: e.target.value.trim(),
            quyen: {
                maquyen: "KH"
            }
        })
        console.log(accountData);
    }

    const handleChange = (e) => {
        setRegisterData({
            ...registerData,
            [e.target.name]: e.target.value.trim(),
            taiKhoanKH: {
                ...accountData
            }
        })
        console.log(registerData);
    }

    const handleSubmit = (e) => {
        e.preventDefault()
        console.log(registerData);

        fetch(`${apiConfigs.baseUrl}/khachhang`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(registerData),
        })
            .then((response) => {
                response.json()
            })
            .then((data) => {
                toast.success("Tạo tài khoản thành công!", {
                    position: "top-center"
                })
                console.log('Success:', data);
                navigate("/login")
            })
            .catch((error) => {
                toast.error("Đăng ký thất bại!", {
                    position: "top-center"
                })
                console.error('Error:', error);
            });
    }

    const formik = useFormik({
        initialValues: {
            matk: "",
            password: "",
            cmnd: "",
            tenkh: "",
            diachi: "",
            sdt: "",
            email: "",
            ngaysinh: ""
        },
        validationSchema: Yup.object({
            matk: Yup.string()
                .max(20, "Tên đăng nhập không quá 20 ký tự")
                .required("Tên đăng nhập không được rỗng!"),
            password: Yup.string()
                .max(20, "Mật khẩu phải có ít hơn 20 ký tự")
                .required("Mật khẩu không được rỗng!"),
            cmnd: Yup.string()
                .max(10, "CMND phải có ít hơn 10 ký tự")
                .required("CMND không được rỗng!"),
            tenkh: Yup.string()
                .required("Họ tên không được rỗng!"),
            diachi: Yup.string()
                .required("Địa chỉ không được rỗng!"),
            sdt: Yup.string()
                .required("Số điện thoại không được rỗng!").matches(phoneRegExp, "Số điện thoại không đúng"),
            email: Yup.string().email("Email phải đúng định dạng")
                .required("Email không được rỗng!"),
        }),
        onSubmit: (values) => {
            fetch(`${apiConfigs.baseUrl}/khachhang`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(registerData),
            })
                .then((response) => {
                    response.json()
                })
                .then((data) => {
                    toast.success("Tạo tài khoản thành công!", {
                        position: "top-center"
                    })
                    console.log('Success:', data);
                    navigate("/login")
                })
                .catch((error) => {
                    toast.error("Đăng ký thất bại!", {
                        position: "top-center"
                    })
                    console.error('Error:', error);
                });
        }
    })

    return (
        <>
            <form className={`${style["container"]} p-3`} onSubmit={formik.handleSubmit} autoComplete="off">
                <h3>Đăng ký</h3>

                <div className={style["register-info"]}>
                    <div className={style["register-info-item"]}>
                        <div className={style["info-item"]}>
                            <label>Tên đăng nhập</label>
                            <input type="text" className="form-control" name='matk'
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                placeholder="Tên đăng nhập" />
                            {formik.touched.matk && formik.errors.matk ? (
                                <div className={style["validate"]}>{formik.errors.matk}</div>
                            ) : null}
                        </div>
                        <div className={style["info-item"]}>
                            <label>CMND</label>
                            <input
                                type="text" className="form-control" name='cmnd' onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                placeholder="ID"
                            />
                            {formik.touched.cmnd && formik.errors.cmnd ? (
                                <div className={style["validate"]}>{formik.errors.cmnd}</div>
                            ) : null}
                        </div>
                        <div className={style["info-item"]}>
                            <label>Địa chỉ</label>
                            <input type="text" className="form-control" placeholder="Địa chỉ" name='diachi' onChange={formik.handleChange}
                                onBlur={formik.handleBlur} />
                            {formik.touched.diachi && formik.errors.diachi ? (
                                <div className={style["validate"]}>{formik.errors.diachi}</div>
                            ) : null}
                        </div>

                        <div className={style["info-item"]}>
                            <label>Email</label>
                            <input
                                type="email" className="form-control" name='email' onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                placeholder="Email"
                            />
                            {formik.touched.email && formik.errors.email ? (
                                <div className={style["validate"]}>{formik.errors.email}</div>
                            ) : null}
                        </div>

                    </div>

                    <div className={style["register-info-item"]}>
                        <div className={style["info-item"]}>
                            <label>Mật khẩu</label>
                            <input
                                type="password" className="form-control" name='password' onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                placeholder="Mật khẩu"
                            />
                            {formik.touched.password && formik.errors.password ? (
                                <div className={style["validate"]}>{formik.errors.password}</div>
                            ) : null}
                        </div>
                        <div className={style["info-item"]}>
                            <label>Họ tên</label>
                            <input type="text" className="form-control" placeholder="Họ tên" name='tenkh' onChange={formik.handleChange}
                                onBlur={formik.handleBlur} />
                            {formik.touched.tenkh && formik.errors.tenkh ? (
                                <div className={style["validate"]}>{formik.errors.tenkh}</div>
                            ) : null}
                        </div>
                        <div className={style["info-item"]}>
                            <label>SĐT</label>
                            <input
                                type="text" className="form-control" name='sdt' onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                placeholder="Số điện thoại"
                            />
                            {formik.touched.sdt && formik.errors.sdt ? (
                                <div className={style["validate"]}>{formik.errors.sdt}</div>
                            ) : null}
                        </div>

                        <div className={style["info-item"]}>
                            <label>Ngày sinh</label>
                            <input
                                type="date" className="form-control" name='ngaysinh' onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                placeholder="Mật khẩu"
                            />
                        </div>
                    </div>
                </div>

                <div className={style["register-action"]}>
                    <button type='submit' className="btn btn-primary">
                        Đăng ký
                    </button>
                    <p className={`${style["forgot-password"]} ${style["text-right"]}`}>
                        Đã có tài khoản <Link to="/login">Đăng nhập?</Link>
                    </p>
                </div>
            </form>
        </>
    )
}

export default Register;