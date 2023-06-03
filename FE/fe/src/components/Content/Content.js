import './Content.css'
import ContentItem from "../ContentItem/ContentItem"
import productImg from '../../assets/images/samsung-galaxy-s22-ultra-1-1.jpg'
import { FaStar } from 'react-icons/fa'


function Content(props) {
    const listContent = ["Sản phẩm mới", "Khuyến mãi", "Bán Chạy"]

    return (
        <div id='content'>
            <ContentItem title={listContent[0]} url="lspnew" />
            <ContentItem title={listContent[1]} url="lspkm" />
            <ContentItem title={listContent[2]} url="lspbestseller" />
        </div>
    )
}

export default Content