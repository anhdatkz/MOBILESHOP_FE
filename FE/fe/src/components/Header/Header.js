import './Header.css'
import { Link, useNavigate } from 'react-router-dom'
import logo from '../../assets/images/logo.jpg'
import { FaShoppingCart, FaBell, FaUserCog, FaSearch, FaSignInAlt } from "react-icons/fa"
import { scrollTop } from '../../App'
import { useDispatch, useSelector } from 'react-redux'
import { Fragment, useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import apiConfig from '../../api/apiConfigs'
import { getTotals } from '../../features/cartSlice'


function Header() {
    const [query, setQuery] = useState('')
    const [cartQuantity, setCartQuantity] = useState([])

    const cart = useSelector((state) => state.cart)
    let navigate = useNavigate()
    const islogin = localStorage.getItem("isLogin")
    const role = localStorage.getItem("role")
    const username = localStorage.getItem("username")

    const dispatch = useDispatch()

    const handleLogin = () => {

        if (islogin === "true") {
            // role === "ROLE_USER" ? navigate("/user/profile") : toast.error("Tên đăng nhập hoặc mật khẩu không đúng", {
            //     position: "top-center"
            // })
            navigate("/user/profile")
        } else {
            navigate("/login")
        }
    }


    const onSubmitSearch = (e) => {
        e.preventDefault()
        if (query.trim() === "") return;

        navigate(`/search/${query}`)
        setQuery("")
        scrollTop()
    }

    const onChangeQuery = (e) => {
        setQuery(e.target.value)
    }

    useEffect(() => {
        setCartQuantity(localStorage.getItem("cartQuantity"))
        dispatch(getTotals())
    }, [username, cartQuantity])

    return (
        <>
            <header id="header">
                <div className='header__logo'>
                    <img src={logo} />
                </div>
                <div className='header__title'>
                    <Link to="/" onClick={scrollTop}>AD Store</Link>
                </div>
                <div className='header__search'>
                    <form className='header__search-box' onSubmit={onSubmitSearch}>
                        <div className='search-icon'>
                            <FaSearch></FaSearch>
                        </div>
                        <input className='search-input' onChange={onChangeQuery}
                            value={query} placeholder='Tìm kiếm sản phẩm...' />
                    </form>
                </div>
                <div className='header__account'>
                    <div className="header__cart">
                        <div className='item cart'>
                            <Link to={(islogin === "true") ? "/detail-cart" : "/login"} onClick={scrollTop}>
                                <FaShoppingCart></FaShoppingCart>
                                {islogin === "true"
                                    ? (<span className="header__cart-number">{cart.cartItems.length}</span>)
                                    : <></>
                                }
                            </Link>
                            {/* <span className="header__cart-number">3</span> */}
                        </div>
                        <div className="header__cart-list">
                            <div className="header__cart-list--no-cart">
                                <div className="header__cart-list--no-cart-img"></div>
                                <div className="header__cart-list--no-cart-msg">Chưa có sản phẩm</div>
                            </div>

                        </div>
                    </div>
                    <div className='item notify'>
                        <FaBell></FaBell>
                    </div>
                    <div className='item login' onClick={handleLogin}>
                        {islogin === "true" ? (<FaUserCog></FaUserCog>) : (<Fragment><FaSignInAlt></FaSignInAlt> <span>Login</span></Fragment>)}
                    </div>
                </div>
            </header>
        </>
    )
}

export default Header