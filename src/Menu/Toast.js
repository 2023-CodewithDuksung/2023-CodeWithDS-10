import {Link} from "react-router-dom";
import "./AllMenu.css";

const menu = [
  {
    id: 26,
    title: "햄치즈토스트",
    category: "토스트",
    price: 3900,
    img: "/menu_img/item-27.jpg",
  },
  {
    id: 27,
    title: "에그토스트",
    category: "토스트",
    price: 3900,
    img: "/menu_img/item-28.jpg",
  }
];

function Toast() {
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

export default Toast;