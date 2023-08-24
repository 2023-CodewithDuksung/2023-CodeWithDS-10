import Tab from "./Menu/Tab.js";
import {Header} from './Header/Header.js';
import Detail from './MenuDetail/Detail.js';
import MyPage from "./MyPage/MyPage.js";
import Cart from "./Cart/Cart.js";
import Review from "./Review/Review.js";
import { BrowserRouter, Route, Routes} from "react-router-dom";
import { useState } from "react";
import Buy from "./Buy/Buy.js";

function App() {
  //메뉴 리스트
  const [menus, setMenus] = useState([]);
  //console.log(menus[0]);
  //담은 장바구니 목록
  const [cart, setCart]=useState([]);
   //장바구니 내 선택한 메뉴 목록
  const[checkLists, setCheckLists]=useState([]);

  return (
        <BrowserRouter>
          <Header cart={cart}/>
          <Routes>
            <Route path = '/' element={<Tab menus={menus} setMenus={setMenus}/>}/>
            <Route path = '/:title' element={<Detail cart={cart} setCart={setCart}/>}/>
            <Route path = '/mypage' element={<MyPage/>}/>
            <Route path = '/cart' element={<Cart cart={cart} setCart={setCart} checkLists={checkLists} setCheckLists={setCheckLists}/>}/>
            <Route path = '/:title/review' element={<Review menus={menus}/>}/>
            <Route path = '/buy' element={<Buy cart={cart}></Buy>}></Route>
          </Routes>
        </BrowserRouter>
  );
}

export default App;
