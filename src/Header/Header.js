import so_img from '../img/siren_orduk.jpg';
import member_img from '../img/member.jpg';
import cart_img from '../img/cart_img.jpg';
import './Header.css'
import { Link } from 'react-router-dom';

export const Header = ({cart}) => {
    return(
        <div className='Container'>
            <div className='HeaderArea'>
                <div className='TopArea'>
                    <div className='TopWrap'>
                        <div className='Title'>
                            <Link to='/'>
                                <img src={so_img} alt='사이렌 오덕' width='310px' height='79px'></img>
                            </Link>
                        </div>
                        <div className='App_list'>
                            <div className='AppWrap'>
                                <div className='LogArea'>
                                    <img src={member_img} alt='회원' width='45.95px' height='45.95px'></img>
                                    <a className='nick' href='#!'>덕밥이</a>
                                    <span>님</span>
                                    <Link to={`/`} className='btn_login'>로그아웃</Link>
                                </div>
                                <div className='MyPage'>
                                    <Link to={`/mypage`}>마이페이지</Link>
                                </div>
                                <div className='Cart'>
                                        {cart.length >=1 ?(
                                            <div className='new_shopping_cart'>
                                                <p>{cart.length}</p>
                                             </div>
                                        ):""}
                                    <Link to={`/cart`}>
                                        <img src={cart_img} alt='장바구니' width='130px' height='38px'></img>
                                    </Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};