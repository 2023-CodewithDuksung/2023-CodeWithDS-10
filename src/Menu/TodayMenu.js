import {Link} from "react-router-dom";
import "./AllMenu.css";

const menu = [{
    id: 0,
    title: "천원의 아침밥",
    category: "천원의 아침밥",
    price: 1000,
    img: "/menu_img/item-1.jpg",
    desc:`carry jianbing normcore freegan. Viral single-origin coffee live-edge, pork belly cloud bread iceland put a bird `,
    },
    {
        id: 1,
        title: "오늘의메뉴",
        category: "오늘의메뉴",
        price: 6500,
        img: "/menu_img/item-2.jpg",
        desc:' ',
       desc: `carry jianbing normcore freegan. Viral single-origin coffee live-edge, pork belly cloud bread iceland put a bird `,
    }
];


function TodayMenu() {
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

export default TodayMenu;