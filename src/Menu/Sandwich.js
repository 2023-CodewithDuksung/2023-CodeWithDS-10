import {Link} from "react-router-dom";
import "./AllMenu.css";

const menu = [
  {
    id: 20,
    title: "아메리카노",
    category: "샌드위치카페",
    price: 2000,
    img: "/menu_img/item-21.jpg",
  },
  {
    id: 21,
    title: "카페라떼",
    category: "샌드위치카페",
    price: 2900,
    img: "/menu_img/item-22.jpg",
  },
  {
    id: 22,
    title: "카페모카",
    category: "샌드위치카페",
    price: 3200,
    img: "/menu_img/item-23.jpg",
  },
  {
    id: 23,
    title: "돌체라떼",
    category: "샌드위치카페",
    price: 3200,
    img: "/menu_img/item-24.jpg",
  },
  {
    id: 24,
    title: "복숭아아이스티",
    category: "샌드위치카페",
    price: 2300,
    img: "/menu_img/item-25.jpg",
  },
  {
    id: 25,
    title: "아샷추",
    category: "샌드위치카페",
    price: 2800,
    img: "/menu_img/item-26.jpg",
  }
];

function Sandwich() {
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

export default Sandwich;