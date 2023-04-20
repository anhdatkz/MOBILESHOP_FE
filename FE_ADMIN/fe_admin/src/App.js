import { Route, Routes, Router, Navigate } from 'react-router-dom'
import { privateRoutesAdmin, publicRouteLogin } from './Routes';
import { FaRegArrowAltCircleUp } from "react-icons/fa"
import { Fragment, useState, useEffect } from 'react';
import { ToastContainer } from 'react-toastify'
import "react-toastify/dist/ReactToastify.css"
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import ScrollToTop from './scrollTop/ScrollToTop';
import PrivateRouteAdmin from './ultils/PrivateRouteAdmin';
import Login from './components/Login/Login';


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
  // let isLogin = localStorage.getItem("isLogin")
  // console.log("isLogin : " + isLogin)
  let role = localStorage.getItem("role")

  useEffect(() => {
    const handleScroll = () => {
      setShowGoToTop(window.scrollY >= 500)
    }
    //window.addEventListener("scroll", handleScroll)
  }, [])

  return (
    <>
      <ScrollToTop />
      <Routes>
        <Route path={publicRouteLogin.path} element={<publicRouteLogin.component />} />
        <Route element={<PrivateRouteAdmin />} >
          {
            privateRoutesAdmin.map((route, index) => {
              // const Page = route.component
              return <Route key={index} path={route.path} element={<route.component />} />
            })
          }
        </Route>
      </Routes>
      {
        (showGoToTop) ? (
          <div className="scroll-top" onClick={scrollTop}>
            <FaRegArrowAltCircleUp />
          </div>
        ) : <Fragment />
      }
      <ToastContainer />
    </>
  );
}

export default App;
