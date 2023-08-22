import menu from '../MenuList.js';
import {Link} from "react-router-dom";
import "./AllMenu.css";


function AllMenu() {
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

export default AllMenu;