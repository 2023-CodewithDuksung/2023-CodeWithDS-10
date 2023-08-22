import Tab from "./Menu/Tab.js";
import Header from './Header/Header.js';
import Detail from './MenuDetail/Detail.js';
import MyPage from "./MyPage/MyPage.js";
import Cart from "./Cart/Cart.js";
import Review from "./Review/Review.js";
import MenuDetail from "./MenuDetail/MenuDetail.jsx";
import menu from "./MenuList.js";
import { BrowserRouter, Route, Routes} from "react-router-dom";
import { useState } from "react";

function App() {
  return (
        <BrowserRouter>
          <Header/>
          <Routes>
            <Route path = '/menu' element={<Tab/>}/>
            <Route path = {'/menu/:title'} element={<MenuDetail/>}/>
            <Route path = '/mypage' element={<MyPage/>}/>
            <Route path = '/cart' element={<Cart/>}/>
            <Route path = '/review' element={<Review/>}/>
          </Routes>
        </BrowserRouter>
  );
}

export default App;
