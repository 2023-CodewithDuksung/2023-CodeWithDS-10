import {Link} from "react-router-dom";
import "./AllMenu.css";

const menu = [
  {
    id: 16,
    title: "제주흑돼지돈까스",
    category: "일식/양식",
    price: 6000,
    img: "/menu_img/item-17.jpg",
  },
  {
    id: 17,
    title: "탄탄면",
    category: "일식/양식",
    price: 6500,
    img: "/menu_img/item-18.jpg",
  },
  {
    id: 18,
    title: "냉모밀",
    category: "일식/양식",
    price: 6500,
    img: "/menu_img/item-19.jpg",
  },
  {
    id: 19,
    title: "카레밥",
    category: "일식/양식",
    price: 4500,
    img: "/menu_img/item-20.jpg",
  }
];

function Ramen() {
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

export default Ramen;