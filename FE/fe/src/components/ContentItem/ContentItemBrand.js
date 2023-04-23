import apiConfig from '../../api/apiConfigs'
import { useState, useEffect, useRef, Fragment } from 'react'
import { FaStar } from 'react-icons/fa'
import "./ContentItem.css"
import { Link } from 'react-router-dom'
import { useParams } from "react-router-dom"
import { caculate, formatTien, VND } from '../../ultils/Format'

function ContentItemBrand(props) {
    const { id } = useParams()

    const [loading, setLoading] = useState(false);
    const [loaiSP, setLoaiSP] = useState([])
    const [cartItems, setCartItems] = useState([])
    const [rams, setRams] = useState([])
    const [roms, setRoms] = useState([])
    const [sort, setSort] = useState("")
    const [filter, setFilter] = useState("")
    const [lspFilter, setLSPFilter] = useState([])
    const gias = [
        {
            tu: 0,
            gia: 3000000,
            mota: "Dưới 3 triệu"
        },
        {
            tu: 3000000,
            gia: 7000000,
            mota: "Từ 3 - 7 triệu"
        },
        {
            tu: 7000000,
            gia: 10000000,
            mota: "Từ 7 - 10 triệu"
        },
        {
            tu: 10000000,
            gia: 15000000,
            mota: "Từ 10 - 15 triệu"
        },
        {
            tu: 15000000,
            gia: 20000000,
            mota: "Từ 15 - 20 triệu"
        },
        {
            tu: 2000000,
            gia: 25000000,
            mota: "Từ 20 - 25 triệu"
        },
        {
            tu: 25000000,
            gia: 30000000,
            mota: "Từ 25 - 30 triệu"
        },
        {
            tu: 30000000,
            gia: 100000000,
            mota: "Từ 30 - 100 triệu"
        },
    ]

    let ramRef = useRef("")
    let romRef = useRef("")
    let giaRef = useRef("")

    const handleFilterHighPrice = (lsp) => {
        setSort("high")
        lsp.sort((a, b) => b.giamoi - a.giamoi)
        setLSPFilter(lsp)
        console.log(lsp)
    }

    const handleFilterLowPrice = (lsp) => {
        setSort("low")
        lsp.sort((a, b) => a.giamoi - b.giamoi)
        setLSPFilter(lsp)
        console.log(lsp)
    }

    const handleChangeRam = (lsp, ram) => {
        setFilter("ram")
        let arr = []
        lsp.forEach((item) => {
            if (item.ram.maram == ram) {
                arr = [...arr, item]
            }
        })
        setLSPFilter(arr)
        console.log(arr)
    }

    const handleChangeRom = (lsp, rom) => {
        setFilter("rom")
        let arr = []
        lsp.forEach((item) => {
            if (item.rom.marom == rom) {
                arr = [...arr, item]
            }
        })
        setLSPFilter(arr)
        console.log(arr)
    }

    const handleChangePrice = (lsp, index) => {
        setFilter("price")
        console.log(index)
        console.log(gias[index])
        let arr = lsp.filter((item) => {
            return (item.giamoi > gias[index].tu && item.giamoi < gias[index].gia)
        })
        // lsp.forEach((item) => {
        //     if (item.ram.maram == ram) {
        //         arr = [...arr, item]
        //     }
        // })
        setLSPFilter(arr)
        console.log(arr)
    }

    const fetchData = async () => {
        const response = await fetch(`${apiConfig.baseUrl}/${id === "all" ? `lsp` : `loaisanpham/hang=${id}`}`)
        if (!response.ok) {
            throw new Error('Data coud not be fetched!')
        } else {
            return response.json()
        }
    }

    useEffect(() => {
        setLoading(true);
        fetchData()
            .then((data) => {
                setFilter("")
                setLoaiSP(data.map((item) => {
                    if (item.ctGiamGiaLSP[0]) {
                        item.giamoi = caculate(item)
                        return item
                    }
                    item.giamoi = item.gia
                    return item
                }))
                setLoading(false);
                console.log(data)
            })
            .catch((e) => {
                console.log(e.message)
                setLoading(false);
            })

        fetch(`${apiConfig.baseUrl}/ram`)
            .then((res) => res.json())
            .then((data) => {
                setRams(data)
                console.log(data)
            })

        fetch(`${apiConfig.baseUrl}/rom`)
            .then((res) => res.json())
            .then((data) => {
                setRoms(data)
                console.log(data)
            })
    }, [id])


    const handleClick = (item) => {
        console.log(item)
        if (cartItems.indexOf(item) !== -1) return;
        setCartItems([...cartItems, item]);
        console.log(cartItems)
    };

    return (
        <>
            {loading
                ? (<div className="content__item">
                    <div className="content__item-title">
                        <div class="spinner-border text-primary"></div>
                    </div>
                </div>)
                : (<>
                    {loaiSP.length === 0 && filter === ""
                        ? (<div className="content__item">
                            <div className="content__item-title">
                                Sản phẩm hãng {id} hiện tại chưa cập nhật!
                            </div>
                        </div>)
                        : (
                            <>
                                <div className='content__item'>
                                    <div className='content__item-title'>{id === "all" ? "Tất cả sản phẩm" : id}</div>
                                    <div className='filter'>
                                        <button className='btn filter-item' onClick={() => handleFilterHighPrice(filter === "" ? loaiSP : lspFilter)}>Giá Cao - Thấp</button>
                                        <button className='btn filter-item' onClick={() => handleFilterLowPrice(filter === "" ? loaiSP : lspFilter)} >Giá Thấp - Cao</button>
                                        <select name="ram" id="ram" className="form-control filter-item" ref={ramRef} onChange={() => handleChangeRam(loaiSP, ramRef.current.value)}>
                                            {rams.map((ram) => (
                                                <option value={ram.maram} key={ram.maram}>{ram.dungluong.trim()}</option>
                                            ))}
                                        </select>
                                        <select name="rom" id="rom" className="form-control filter-item" ref={romRef} onChange={() => handleChangeRom(loaiSP, romRef.current.value)}>
                                            {roms.map((rom) => (
                                                <option value={rom.marom} key={rom.marom}>{rom.dungluong.trim()}</option>
                                            ))}
                                        </select>
                                        <select name="gias" id="gias" className="form-control filter-item" ref={giaRef} onChange={() => handleChangePrice(loaiSP, giaRef.current.value)}>
                                            {gias.map((gia, index) => (
                                                <option value={index} key={gia.gia}>{gia.mota.trim()}</option>
                                            ))}
                                        </select>
                                    </div>
                                    {filter === ""
                                        ? (loaiSP.length === 0
                                            ? (<div className="content__item">
                                                <div className="content__item-title">
                                                    Không tìm thấy sản phẩm
                                                </div>
                                            </div>)
                                            : (<div className='content__item-list'>
                                                <ul className='list-product'>
                                                    {loaiSP.map((loaisp, index) => (
                                                        <li className='product' key={index}>
                                                            <Link to={`/detail-product/${loaisp.maloai}`}>
                                                                <div className='product__img'>
                                                                    <img src={loaisp.anh} />
                                                                </div>
                                                                <div className='product__name'>{loaisp.tenloai}</div>
                                                                <div className='product__old-price'>
                                                                    {loaisp.ctGiamGiaLSP[0]
                                                                        ? (<Fragment>
                                                                            <div className="old-price">
                                                                                {VND.format(loaisp.gia)}
                                                                            </div>
                                                                            <div className="percent">
                                                                                {loaisp.ctGiamGiaLSP[0] ? `${loaisp.ctGiamGiaLSP[0].phantram} %` : ""}
                                                                            </div>
                                                                        </Fragment>)
                                                                        : (<Fragment />)}
                                                                </div>
                                                                <div className='product__new-price'>{VND.format(loaisp.giamoi)}
                                                                </div>
                                                                <ul className='product__star'>
                                                                    <li><FaStar /></li>
                                                                    <li><FaStar /></li>
                                                                    <li><FaStar /></li>
                                                                    <li><FaStar /></li>
                                                                    <li><FaStar /></li>
                                                                </ul>
                                                            </Link>
                                                            {/* <button className='btn-add-cart btn btn-primary' onClick={() => handleClick(loaisp)}>Thêm vào giỏ hàng</button> */}
                                                        </li>
                                                    ))}
                                                </ul>
                                            </div>))
                                        : (lspFilter.length === 0
                                            ? (<div className="content__item">
                                                <div className="content__item-title">
                                                    Không tìm thấy sản phẩm
                                                </div>
                                            </div>)
                                            : (<div className='content__item-list'>
                                                <ul className='list-product'>
                                                    {lspFilter.map((lspFilter, index) => (
                                                        <li className='product' key={index}>
                                                            <Link to={`/detail-product/${lspFilter.maloai}`}>
                                                                <div className='product__img'>
                                                                    <img src={lspFilter.anh} />
                                                                </div>
                                                                <div className='product__name'>{lspFilter.tenloai}</div>
                                                                <div className='product__old-price'>
                                                                    {lspFilter.ctGiamGiaLSP[0]
                                                                        ? (<Fragment>
                                                                            <div className="old-price">
                                                                                {VND.format(lspFilter.gia)}
                                                                            </div>
                                                                            <div className="percent">
                                                                                {lspFilter.ctGiamGiaLSP[0] ? `${lspFilter.ctGiamGiaLSP[0].phantram} %` : ""}
                                                                            </div>
                                                                        </Fragment>)
                                                                        : (<Fragment />)}
                                                                </div>
                                                                <div className='product__new-price'>{VND.format(lspFilter.giamoi)}
                                                                </div>
                                                                <ul className='product__star'>
                                                                    <li><FaStar /></li>
                                                                    <li><FaStar /></li>
                                                                    <li><FaStar /></li>
                                                                    <li><FaStar /></li>
                                                                    <li><FaStar /></li>
                                                                </ul>
                                                            </Link>
                                                            {/* <button className='btn-add-cart btn btn-primary' onClick={() => handleClick(loaisp)}>Thêm vào giỏ hàng</button> */}
                                                        </li>
                                                    ))}
                                                </ul>
                                            </div>
                                            ))}
                                </div>
                            </>
                        )}
                </>)}

        </>
    )
}

export default ContentItemBrand
