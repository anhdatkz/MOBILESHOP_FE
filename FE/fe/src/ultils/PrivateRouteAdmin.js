import { Navigate, Outlet } from "react-router-dom"


const  PrivateRouteAdmin = ()=>{
    let isLogin = localStorage.getItem("isLogin")
    let role = localStorage.getItem("role")
    console.log("isLogin : " + isLogin)
    return(
        (isLogin ==="true" && role === "ROLE_ADMIN") ? <Outlet/> : <Navigate to="/login"/>
    )
}

export default PrivateRouteAdmin