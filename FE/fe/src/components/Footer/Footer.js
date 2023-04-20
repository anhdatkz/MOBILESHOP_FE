import './Footer.css'
import slider from '../../assets/images/mountains-1412683.png'
import { FaPhoneAlt, FaEnvelope, FaMap } from "react-icons/fa"

const Address = () => {
    return (
        <div className='address'>
            <div className='address__item'>
                <div className='map'>
                    <img src={slider}></img>
                </div>
            </div>

            <div className='address__item'>
                <div className='info'>
                    <div className='info__item'>
                        <FaMap></FaMap>
                        <div>123 Road Man Thiện Street</div>
                        <div>Hồ Chí Minh City</div>
                    </div>
                    <div className='info__item'>
                        <FaPhoneAlt></FaPhoneAlt>
                        <div>(+84) 961075932</div>
                        <div>(+84) 123456789</div>
                    </div>
                    <div className='info__item'>
                        <FaEnvelope></FaEnvelope>
                        <div>thietbididong@gmail.com</div>
                        <div>didongtietkiem@gmail.com</div>
                    </div>
                </div>
            </div>
        </div>
    )
}

const GetInTouch = () => {
    return (
        <div className='get-in-touch'>

        </div>
    )
}


const Footer = () => {
    return (
        <div id='footer'>
            <Address />
            <GetInTouch />
        </div>
    )
}

export default Footer