import Login from "./layouts/components/Login/Login"
import Register from "./layouts/components/Register/Register"
import Home from "./pages/Home/Home"




const publicRoutes = [
    { path: "/", component: Home },
    // { path: "/brand/:id", component: Brand },
    // { path: "/search=:query", component: Search },
    // { path: "/detail-product/:id", component: ProductDetail },
    // { path: "/detail-cart", component: CartDetails },
    // { path: "/checkout", component: PayPal },
    { path: "/login", component: Login },
    { path: "/register", component: Register },
]

const privateRoutesUser = [
    // { path: "/user/profile", component: UserProfile },
    // { path: "/user/notifications", component: UserNotifi },
    // { path: "/user/orders", component: UserOrders },
    // { path: "/user/orders/:id", component: UserOrders },
    // { path: "/user/orders-history", component: UserOrderHistory },
    // { path: "/user/returns", component: UserReturn },
]

export { publicRoutes, privateRoutesUser }