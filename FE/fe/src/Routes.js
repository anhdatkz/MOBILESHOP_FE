import Login from "./components/Login/Login"
import Register from "./components/Register/Register"
import Admin from "./page/Admin/Admin"
import CartDetails from "./page/CartDetails/CartDetails"
import Home from "./page/Home/Home"
import ProductDetail from "./page/ProductDetails/ProductDetails"
import AdminEmployee from "./page/Admin/AdminEmployee"
import AdminOrder from "./page/Admin/AdminOrder"
import AdminProduct from "./page/Admin/AdminProduct"
import AdminReport from "./page/Admin/AdminReport"
import PayPal from "./components/Paypal/Paypal"
import Brand from "./page/SingleBrand/Brand"
import UserNotifi from "./page/User/UserNotifi"
import UserOrders from "./page/User/UserOrders"
import UserOrderHistory from "./page/User/UserOrderHistory"
import UserProfile from "./page/User/UserProfile"
import UserReturn from "./page/User/UserReturn"
import Search from "./page/Search/Search"



const publicRoutes = [
    { path: "/", component: Home },
    { path: "/brand/:id", component: Brand },
    { path: "/search/:query", component: Search },
    { path: "/detail-product/:id", component: ProductDetail },
    { path: "/detail-cart", component: CartDetails },
    { path: "/checkout", component: PayPal },
    { path: "/login", component: Login },
    { path: "/register", component: Register },
]

// const privateRoutesAdmin = [
//     { path: "/manager", component: Admin },
//     { path: "/manager/brand", component: Admin },
//     { path: "/manager/product", component: AdminProduct },
//     { path: "/manager/employee", component: AdminEmployee },
//     { path: "/manager/order", component: AdminOrder },
//     { path: "/manager/report", component: AdminReport },
//     { path: "/manager/account", component: AdminReport },
// ]

const privateRoutesUser = [
    { path: "/user/profile", component: UserProfile },
    { path: "/user/notifications", component: UserNotifi },
    { path: "/user/orders", component: UserOrders },
    { path: "/user/orders/:id", component: UserOrders },
    { path: "/user/orders-history", component: UserOrderHistory },
    { path: "/user/returns", component: UserReturn },
]

export { publicRoutes, privateRoutesUser }