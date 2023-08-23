import {Link} from "react-router-dom";
import "./AllMenu.css";

const menu = [
      {
        id: 2,
        title: "4분돼지김치파스타",
        category: "파스타치요",
        price: 7500,
        img: "/menu_img/item-3.jpg",
      },
      {
        id: 3,
        title: "고기리들기름파스타",
        category: "파스타치요",
        price: 6000,
        img: "/menu_img/item-4.jpg",
       },
      {
        id: 4,
        title: "트러플버섯크림파스타",
        category: "파스타치요",
        price: 6500,
        img: "/menu_img/item-5.jpg",
       },
      {
        id: 5,
        title: "대패삼겹크림파스타",
        category: "파스타치요",
        price: 7500,
        img: "/menu_img/item-6.jpg",
     
      },
      {
        id: 6,
        title: "매콤로제파스타",
        category: "파스타치요",
        price: 7500,
        img: "/menu_img/item-7.jpg",
      },
      {
        id: 7,
        title: "우삼겹알리오올리오파스타",
        category: "파스타치요",
        price: 7000,
        img: "/menu_img/item-8.jpg",
      },
      {
        id: 8,
        title: "직화불막창토핑추가", 
        category: "파스타치요",
        price: 2900,
        img: "/menu_img/item-9.jpg",
       },
      {
        id: 9,
        title: "직화불쭈꾸미토핑추가",
        category: "파스타치요",
        price: 2900,
        img: "/menu_img/item-10.jpg",
      }
];

function Pasta() {
  return (
    <div className='all_list'>
      <ul className='all_wrap'>
        {menu.map((item, index) => (
          <li className='menuData'>
            <Link to={`/menu/${item.title}`}>
                <dl>
                  <dt>
                      <img src={process.env.PUBLIC_URL + item.img} alt={item.id} width='350px' height='300px'/>
                  </dt>
                  <dd>{item.title}</dd>
                </dl>
              </Link>
          </li>
        ))}
      </ul>
    </div>
  );
  };

export default Pasta;