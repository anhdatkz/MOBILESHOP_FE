import Header from "../../layouts/components/Header/Header";
import { Outlet } from "react-router-dom";
import Navbar from "../../layouts/components/Navbar/Navbar";

export default function Home(params) {
    return (
        <>
            <Header></Header>
            <Navbar></Navbar>
            <Outlet></Outlet>
        </>
    )
};
