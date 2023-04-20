import './App.css';
import { Routes, Route } from 'react-router-dom'
import Header from './layouts/components/Header/Header';
import { Fragment, useEffect, useState } from 'react';
import Navbar from './layouts/components/Navbar/Navbar';
import { privateRoutesAdmin, privateRoutesUser, publicRoutes } from './Routes';
import { FaRegArrowAltCircleUp } from "react-icons/fa"


const scrollTop = () => {
  window.scrollTo({
    top: 0,
    behavior: "smooth",
  });
};

export { scrollTop }

function App() {
  const [showGoToTop, setShowGoToTop] = useState(false)

  useEffect(() => {
    const handleScroll = () => {
      setShowGoToTop(window.scrollY >= 500)
    }
    //window.addEventListener("scroll", handleScroll)
  }, [])
  return (
    <>
      <Routes>
        {publicRoutes.map((route, index) => {
          // const Page = route.component
          return <Route key={index} path={route.path} element={<route.component />} />
        })}
      </Routes>
      {(showGoToTop) ? (
        <div className="scroll-top" onClick={scrollTop}>
          <FaRegArrowAltCircleUp />
        </div>
      ) : <Fragment />}
    </>
  );
}

export default App;
