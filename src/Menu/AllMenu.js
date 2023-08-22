import menu from '../MenuList.js';
import React, { useState } from 'react';
import {Link, useNavigate} from "react-router-dom";
import "./AllMenu.css";


  function AllMenu(props) {
    const navigate = useNavigate();

    const onClickMenuItem =  () => {
      navigate(`/menu/${props.title}`, {
        state: props
      })
    }

    return (
      <div className='all_list'>
        <ul className='all_wrap'>
          {menu.results.map((item, index) => (
            <li className='menuData'>
                  <dl>
                    <dt>
                        <img src={item.props.img} alt={item.props.id} width='350px' height='300px' onClick={onClickMenuItem}/>
                    </dt>
                    <dd>{item.props.title}</dd>
                  </dl>
            </li>
          ))}
        </ul>

      </div>
    );
  };

export default AllMenu;