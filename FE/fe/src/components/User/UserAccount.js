import { useEffect, useState } from 'react'
import apiConfig from '../../api/apiConfigs'
import './User.css'



function UserAccount() {

    const [userInfo, setUserInfo] = useState({})
    // const username = localStorage.getItem('username')

    // useEffect(() => {
    //     fetch(`${apiConfig.baseUrl}/khachhang/tk/${username}`, {
    //         headers: {
    //             'Authorization': localStorage.getItem("token")
    //         },
    //     })
    //         .then((res) => res.json())
    //         .then((data) => {
    //             setUserInfo(data)
    //             console.log(data)
    //         })
    // }, [])
    useEffect(() => {
        fetch(`${apiConfig.baseUrl}/khachhang/profile`, {
            headers: {
                'Authorization': localStorage.getItem("token")
            },
        })
            .then((res) => res.json())
            .then((data) => {
                setUserInfo(data)
                console.log(data)
            })
    }, [])

    const handleChangeName = () => {

    }
    console.log(userInfo)

    return (
        <>
            <div className="user">
                <div className="user-title">
                    <h2>Hồ sơ của tôi</h2>
                </div>
                <div className="user-profile">
                    <form action="">
                        <div className="frofile-item">
                            <div className="fullname">
                                <label htmlFor="">Tên</label>
                                <div>
                                    <input type="text" defaultValue={userInfo.tenkh} onChange={handleChangeName} />
                                </div>
                            </div>
                        </div>
                        <div className="frofile-item">
                            <div className="email">
                                <label htmlFor="">Email</label>
                                <div>{userInfo.email}</div>
                            </div>
                        </div>
                        <div className="frofile-item">
                            <div className="numberphone">
                                <label htmlFor="">Số điện thoại</label>
                                <div>{userInfo.sdt}</div>
                            </div>
                        </div>
                        <div className="frofile-item">
                            <div className="address">
                                <label htmlFor="">Địa chỉ</label>
                                <div>{userInfo.diachi}</div>
                            </div>
                        </div>
                        <div className="frofile-item">
                            <div className="dayofbirth">
                                <label htmlFor="">Ngày sinh</label>
                                <input type="date" value={userInfo.ngaysinh}></input>
                            </div>
                        </div>
                        <div className="frofile-item">
                            <button className='btn btn-primary'>Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </>
    )
}

export default UserAccount