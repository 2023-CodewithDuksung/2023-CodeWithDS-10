import menu from '.';
import React, { useState } from 'react';
import {Link} from "react-router-dom";
import "./AllMenu.css";


  function AllMenu() {
    return (
      <div className='all_list'>
        <ul className='all_wrap'>
          {menu.map((m,index) => (
            <li className='menuData'>
              <React.Fragment key={m.id}>
                <Link to={`/menu/${m.id}`}>
                  <dl>
                    <dt>
                        <img src={m.img} alt={m.category+index} width='350px' height='300px'></img>
                    </dt>
                    <dd>{m.title}</dd>
                  </dl>
                </Link>
              </React.Fragment>
            </li>
          ))}
        </ul>

      </div>
    );
  };

export default AllMenu;