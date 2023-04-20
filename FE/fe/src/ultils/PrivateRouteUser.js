import { Navigate, Outlet } from "react-router-dom"


const  PrivateRouteUser = ()=>{
    let isLogin = localStorage.getItem("isLogin")
    let role = localStorage.getItem("role")
    console.log("isLogin : " + isLogin)
    return(
        (isLogin ==="true" && role === "ROLE_USER") ? <Outlet/> : <Navigate to="/login"/>
    )
}

export default PrivateRouteUser