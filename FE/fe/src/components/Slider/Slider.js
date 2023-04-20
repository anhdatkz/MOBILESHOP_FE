import './Slider.css'
import slider from '../../assets/images/Big-Campaign-Desktop-1920x450-1.jpg'
import {FaAngleLeft, FaAngleRight} from "react-icons/fa"

function Slider(){
    return(
        <>
        <section id='slider'>
            <div className='slider__img'>
                <div className='icon prev'><FaAngleLeft/></div>
                <div>
                    <img src={slider} alt=''></img>
                </div>
                <div className='icon next'><FaAngleRight/></div>
                <div className='slider__img-text'>
                    
                </div>
            </div>
        </section>
        </>
    )
}

export default Slider