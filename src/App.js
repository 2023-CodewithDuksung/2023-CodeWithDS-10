import Tab from "./MenuList/Tab.js";
import Hook from './Hook/Hook.js';
import Detail from './MenuDetail/Detail.js';
import MyPage from "./MyPage/MyPage.js";
import Cart from "./Cart/Cart.js";
import Review from "./Review/Review.js";
import menu from "./MenuList.js";
import { BrowserRouter, Route, Routes} from "react-router-dom";
import { useState } from "react";

function App() {
  let [menus, setMenus] = useState(menu);

  return (
        <BrowserRouter>
          <Hook/>
          <Routes>
            <Route path = '/menu' element={<Tab/>}/>
            <Route path = {'/menu/:id'} element={<Detail menus={menus}/>}/>
            <Route path = '/mypage' element={<MyPage/>}/>
            <Route path = '/cart' element={<Cart/>}/>
            <Route path = '/review' element={<Review/>}/>
          </Routes>
        </BrowserRouter>
  );
}

export default App;
