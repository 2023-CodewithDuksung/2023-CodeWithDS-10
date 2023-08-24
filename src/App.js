import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useUserState, useUserDispatch } from "./UserContext";

import React from "react";
import "./App.css";
import { useState } from "react";
import Buy from "./Buy/Buy.js";

import { Headers } from "./Headers/Headers.js";
import Detail from "./MenuDetail/Detail.js";

import Cart from "./Cart/Cart.js";

import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Home from "./pages/Home";
import Tab from "./Menu/Tab.js";
import Mypage from "./pages/Mypage";
import Modify from "./pages/Modify";
import ModifyName from "./pages/ModifyName";
import Header from "./pages/Header";
import useInput from "./pages/useInput";
import Diary from "./reviews/Diary";
import Ticket from "./Ticket/Ticket";

const App = () => {
  const { user } = useUserState();

  //메뉴 리스트
  const [menus, setMenus] = useState([]);
  //console.log(menus[0]);
  //담은 장바구니 목록
  const [cart, setCart] = useState([]);
  //장바구니 내 선택한 메뉴 목록
  const [checkLists, setCheckLists] = useState([]);

  return (
    <Routes>
      <Route path="/menu" element={<Tab menus={menus} setMenus={setMenus} />} />
      <Route
        path="/:title"
        element={<Detail cart={cart} setCart={setCart} />}
      />

      <Route
        path="/cart"
        element={
          <Cart
            cart={cart}
            setCart={setCart}
            checkLists={checkLists}
            setCheckLists={setCheckLists}
          />
        }
      />

      <Route path="/buy" element={<Buy cart={cart}></Buy>} />

      <Route exact path="/" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/home" element={<Home />} />
      <Route path="/mypage" element={<Mypage />} />
      <Route path="/modify" element={<Modify />} />
      <Route path="/modifyname" element={<ModifyName />} />
      <Route path="/diary" element={<Diary />} />
      <Route path="/ticket" element={<Ticket />} />
    </Routes>
  );
};

export default App;
