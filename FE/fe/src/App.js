import { Route, Routes, Router, Navigate } from 'react-router-dom'
import { publicRoutes, privateRoutesAdmin, privateRoutesUser } from './Routes';
import { FaRegArrowAltCircleUp } from "react-icons/fa"
import { Fragment, useState, useEffect } from 'react';
import { ToastContainer } from 'react-toastify'
import "react-toastify/dist/ReactToastify.css"
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Header from './components/Header/Header';
import ScrollToTop from './scrollTop/ScrollToTop';
import PrivateRouteAdmin from './ultils/PrivateRouteAdmin';
import PrivateRouteUser from './ultils/PrivateRouteUser';
import apiConfig from './api/apiConfigs';
import { loginData } from './ultils/LoginData';
import { useDispatch } from 'react-redux';
import { getTotals, initializeCart } from './features/cartSlice';
// import Navbar from './components/Navbar/Navbar';
// import Footer from './components/Footer/Footer';


const scrollTop = () => {
  window.scrollTo({
    top: 0,
    behavior: "smooth",
  });
};

const username = localStorage.getItem('username')

export { scrollTop, username }


function App() {
  const [showGoToTop, setShowGoToTop] = useState(false)
  const dispatch = useDispatch();
  let role = localStorage.getItem("role")

  useEffect(() => {
    const handleScroll = () => {
      setShowGoToTop(window.scrollY >= 500)
    }

    dispatch(initializeCart());
    window.addEventListener("scroll", handleScroll)

  }, [dispatch])

  return (
    <>
      <Header />
      <ScrollToTop />
      <Routes>
        {publicRoutes.map((route, index) => {
          // const Page = route.component
          return <Route key={index} path={route.path} element={<route.component />} />
        })}

        {/* <Route element = {<PrivateRouteAdmin/>}>
          {privateRoutesAdmin.map((route, index) => {
            // const Page = route.component
            return <Route key={index} path={route.path} element={<route.component />} />
          })}
        </Route> */}

        <Route element={<PrivateRouteUser />}>
          {privateRoutesUser.map((route, index) => {
            // const Page = route.component
            return <Route key={index} path={route.path} element={<route.component />} />
          })}
        </Route>
      </Routes>
      {(showGoToTop) ? (
        <div className="scroll-top" onClick={scrollTop}>
          <FaRegArrowAltCircleUp />
        </div>
      ) : <Fragment />}
      <ToastContainer />
      {/* <Footer /> */}
    </>
  );
}

export default App;
