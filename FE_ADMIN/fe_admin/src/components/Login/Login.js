import style from './Login.module.css'
import { Link, useNavigate } from 'react-router-dom'
import { useState, useEffect } from 'react';
import { scrollTop } from '../../App'
import { toast } from "react-toastify"
import apiConfigs from '../../api/apiConfigs'
import { useFormik } from 'formik';
import * as Yup from "yup"
import { FaEye, FaEyeSlash } from 'react-icons/fa';

export let manvLogin = ""

function Login() {
    const [username, setUserName] = useState("")
    const [password, setPassWord] = useState("")
    const [accounts, setAccounts] = useState([])
    const [showPassword, setShowPassword] = useState(false);

    const usernameRegex = /^[a-zA-Z0-9]+$/

    const toggleShowPassword = () => {
        setShowPassword(!showPassword);
    };

    let navigate = useNavigate()

    const formik = useFormik({
        initialValues: {
            username: "",
            password: ""
        },
        validationSchema: Yup.object({
            username: Yup.string()
                .max(20, "Tên đăng nhập phải có ít hơn 20 ký tự")
                .required("Tên đăng nhập không được rỗng!").matches(usernameRegex, "Username viết liền không dấu"),
            password: Yup.string()
                .max(20, "Mật khẩu phải có ít hơn 20 ký tự")
                .required("Mật khẩu không được rỗng!").matches(usernameRegex, "Mật khẩu viết liền không dấu"),
        }),
        onSubmit: (values) => {
            localStorage.setItem('isLogin', false)
            const formData = {
                matk: values.username.trim(),
                password: values.password.trim()
            }

            fetch(`${apiConfigs.baseUrl}/auth`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            })
                .then((response) => {
                    if (response.ok) {
                        return response.json()
                    }
                    throw Error(response.status)
                })
                .then((data) => {

                    localStorage.setItem('isLogin', true)
                    // localStorage.setItem('username', data.name)
                    // localStorage.setItem('role', data.authorities[0])
                    localStorage.setItem('token', data.token)
                    setAccounts(data)

                    if (data.authorities[0] === "ROLE_ADMIN") {
                        fetch(`${apiConfigs.baseUrl}/nhanvien/profile`, {
                            headers: {
                                'Authorization': data.token
                            },
                        })
                            .then((response) => {
                                if (response.ok) {
                                    return response.json()
                                }
                                throw Error(response.status)
                            })
                            .then((data) => {
                                console.log(data.manv.trim())
                            })

                        navigate("/manager/brand")
                        toast.success("Đăng nhập thành công", {
                            position: "top-center"
                        })
                    } else {

                        // navigate("/")
                        toast.error("Tên đăng nhập hoặc mật khẩu không đúng", {
                            position: "top-center"
                        })
                    }

                    console.log('Success:', data);
                })
                .catch((error) => {
                    toast.error("Tên đăng nhập hoặc mật khẩu không đúng", {
                        position: "top-center"
                    })
                    // loadData()
                    console.error('Error:', error);
                });

            scrollTop()
        }
    })

    console.log(formik)


    return (
        <>
            <div className={style["container"]}>
                <form className={`${style["login-form"]}`} onSubmit={formik.handleSubmit} autoComplete="off">
                    <h3>Đăng nhập</h3>
                    <div className="form-outline mb-4">
                        <label className="form-label">Tên đăng nhập</label>
                        <input
                            type="text"
                            id='username'
                            name='username'
                            className="form-control"
                            placeholder="Tên đăng nhập"
                            value={formik.values.username}
                            // onChange={e => setUserName(e.target.value.trim())}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                        />
                        {formik.touched.username && formik.errors.username ? (
                            <div className={style["validate"]}>{formik.errors.username}</div>
                        ) : null}
                    </div>
                    <div className="form-outline mb-4">
                        <label className="form-label">Mật khẩu</label>
                        <div className={style["input-password"]}>
                            <input
                                type={showPassword ? 'text' : 'password'}
                                id='password'
                                name='password'
                                className="form-control"
                                placeholder="Mật khẩu"
                                value={formik.values.password}
                                // onChange={e => setPassWord(e.target.value.trim())}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                            />
                            {showPassword ? (<FaEyeSlash onClick={toggleShowPassword}></FaEyeSlash>) : (<FaEye onClick={toggleShowPassword}></FaEye>)}
                        </div>
                        {formik.touched.password && formik.errors.password ? (
                            <div className={style["validate"]}>{formik.errors.password}</div>
                        ) : null}
                    </div>
                    <div className="row mb-4">
                        <div className="col d-flex justify-content-center">
                            <div className="form-check">
                                <input className="form-check-input" type="checkbox" value="" />
                                <label className="form-check-label" > Remember me </label>
                            </div>
                        </div>

                        <div className="col">
                            <a href="#!">Forgot password?</a>
                        </div>
                    </div>

                    <div className="d-flex justify-content-between">
                        <button type="submit" className={`${style["btn-login"]} btn btn-primary`}>
                            Đăng nhập
                        </button>
                    </div>
                    {/* <div className="text-center">
                        <p>Not a member? <a href="#!">Register</a></p>
                    </div> */}
                    {/* <p className="forgot-password text-right">
                        Quên <a href="#">mật khẩu?</a>
                    </p> */}
                </form>
            </div>
        </>
    )
}

export default Login;