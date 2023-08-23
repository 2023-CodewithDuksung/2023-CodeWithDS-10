import './Detail.css';
import { useState } from 'react';
import star from '../img/star.jpg';
import btn_review from '../img/btn_reviewlist.jpg';
import menu from "../Menu/MenuList";
import { Link, useLocation, useParams } from 'react-router-dom';
import CartModal from './Modal.js';

function Detail() {      

    const {title} = useParams();
    const findItem = menu.find((item) => {
        return item.title == title;
    });

    const [modalVisible, setModalVisible] = useState(false);

    const openModal=()=> {
        setModalVisible(true);
    };

    const hideModal = () => {
        setModalVisible(false);
    }

    return (
    <>
    {modalVisible && (
        <CartModal hideModal={hideModal}/>
    )}
    <div className='detail_container'>
        <div className='detail_wrap'>
            <div className='left'>
                <div className='food_img'>
                    <img src={process.env.PUBLIC_URL + findItem.img} width='630px' height='630px'></img>
                </div>
            </div>
            <div className='right'>
                <div className='food_info'>
                    <p className='food_title'>{findItem.category}-{findItem.title}</p>
                    <p className='food_price'>{findItem.price}원</p>
                    <div className='food_ingr'>
                        <p>알레르기 정보</p>
                        <span>- 돼지고기, 쇠고기, 땅콩, 대두, 밀, 닭고기, 우유, 알류 함유</span>
                    </div>
                    <div className='food_origin'>
                        <p>원산지 정보</p>
                        <span>- 돼지고기(국산), 소고기(호주산)</span>
                    </div>
                </div>
                <div className='quantity'>
                    <p>구매 수량</p>
                </div>
                <div className='cart_purchase'>
                    <div className='total'>
                        <p>금액 합계</p>
                        <p>9000 원</p>
                    </div>
                    <div className='btn_container'>
                        <div className='btn_cart' onClick={()=> openModal}>
                            장바구니
                        </div>
                        <div className='btn_purchase'>
                            결제
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div className='review'>
            <p>덕우들의 리뷰</p>
            <div className='review_wrap'>
                <div className='star'>
                    <img src={star} alt='별점' width='30px' height='30px'></img>
                    <img src={star} alt='별점' width='30px' height='30px'></img>
                    <img src={star} alt='별점' width='30px' height='30px'></img>
                    <img src={star} alt='별점' width='30px' height='30px'></img>
                    <img src={star} alt='별점' width='30px' height='30px'></img>
                </div>
                <Link to='/review'>
                    <img src={btn_review} alt='리뷰버튼' width='145px' height='45px'></img>
                </Link>
            </div>
        </div>
    </div>
    </>
)};

export default Detail;