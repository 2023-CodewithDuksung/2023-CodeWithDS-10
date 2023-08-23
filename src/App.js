import { Routes, Route } from "react-router-dom";
import { useUserState, useUserDispatch } from "./UserContext";

import React from "react";
import "./App.css";

import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Home from "./pages/Home";
import Mypage from "./pages/Mypage";
import Modify from "./pages/Modify";
import ModifyName from "./pages/ModifyName";
import Header from "./pages/Header";
import useInput from "./pages/useInput";
import Diary from "./reviews/Diary";

const App = () => {
  const { user } = useUserState();

  return (
    <div>
      <Routes>
        <Route exact path="/" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/home" element={<Home />} />
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/modify" element={<Modify />} />
        <Route path="/modifyname" element={<ModifyName />} />
        <Route path="/diary" element={<Diary />} />
      </Routes>
    </div>
  );
};

export default App;
