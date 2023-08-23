import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { TopNavigationBar } from "./components/header/topNavigationBar/topNavigationBar";
import Home from "./pages/home";
import Product from "./pages/product";
import Basket from "./pages/basket";
import {useState} from "react";

function App() {
  const [products, setProducts] = useState([]);
  const [cart, setCart]=useState([]);

   //장바구니 목록 선택
   const[checkLists, setCheckLists]=useState([]); //최상위 폴더에 넣어야 체크한 것이 다른 웹페이지 갔다와도 사라지지 않음


  const convertPrice = (price)=>{
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }
  return (
    <BrowserRouter>
      <TopNavigationBar cart={cart}/>
      <Routes>
        <Route path="/" element={
        <Home 
        products={products} 
        setProducts={setProducts}
        convertPrice={convertPrice}/>} />
        
        <Route 
        path="/product/:id" 
        element={
        <Product 
        convertPrice={convertPrice} 
        cart={cart} 
        setCart={setCart}/>} />

        <Route path="/cart" 
        element={<Basket 
        cart={cart} 
        setCart={setCart} 
        convertPrice={convertPrice} 
        checkLists={checkLists}
        setCheckLists={setCheckLists}
        
        />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
 