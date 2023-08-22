import {Link} from "react-router-dom";
import "./AllMenu.css";

const menu = [
    {
        id: 10,
        title: "치킨마요덮밥",
        category: "니나노덮밥",
        price: 7000,
        img: "/menu_img/item-11.jpg",
      },
      {
        id: 11,
        title: "우동돈까스세트",
        category: "니나노덮밥",
        price: 6200,
        img: "/menu_img/item-12.jpg",
      },
      {
        id: 11,
        title: "제육덮밥",
        category: "니나노덮밥",
        price: 6500,
        img: "/menu_img/item-13.jpg",
      },
      {
        id: 12,
        title: "닭갈비덮밥",
        category: "니나노덮밥",
        price: 7000,
        img: "/menu_img/item-14.jpg",
      },
      {
        id: 13,
        title: "오므라이스",
        category: "니나노덮밥",
        price: 7000,
        img: "/menu_img/item-15.jpg",
      },
      {
        id: 14,
        title: "불닭마요덮밥",
        category: "니나노덮밥",
        price: 6500,
        img: "/menu_img/item-16.jpg",
      }    
];

function Rice() {
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

export default Rice;