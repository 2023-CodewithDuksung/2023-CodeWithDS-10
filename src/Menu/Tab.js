import { useState } from 'react';
import AllMenu from './AllMenu.js';
import SchoolMenu from './SchoolMenu.js';
import Soldout from './Soldout.js';
import styled from 'styled-components';

// Styled-Component 라이브러리를 활용해 TabMenu 와 Desc 컴포넌트의 CSS를 구현.

const TabMenu = styled.div`
  background-color: #dcdcdc;
  color: black;
  font-weight: bold;
  display: flex;
  flex-direction: row;
  align-items: center;
  list-style: none;

  .submenu {
    display: flex;
    width: calc(100% /3);
    height: 50px;
    padding: 10px;
    padding-top: 10px;
    font-size: 15px;
    transition: 0.5s;
    border-radius: 10px 10px 0px 0px;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }

  .focused {
    background-color: #873856;
    color: #ffffff;
  }
`;


export default function Tab(){
  // Tab Menu 중 현재 어떤 Tab이 선택되어 있는지 확인하기 위한 currentTab 상태와 currentTab을 갱신하는 함수가 존재해야 하고, 초기값은 0.
  const [currentTab, clickTab] = useState(0);

  const menuArr = [
    { name: '전체' , content: <AllMenu/>},
    { name: '오늘의 메뉴', content: <SchoolMenu/>},
    { name: '파스타치요', content: <SchoolMenu/>},
    { name: '니나노덮밥', content: <SchoolMenu/>},
    { name: '일식/양식', content: <SchoolMenu/>},
    { name: '샌드위치카페', content: <SchoolMenu/>},
    { name: '토스트', content: <SchoolMenu/>},
    { name: '품절', content: <Soldout/>}
  ];

  const selectMenuHandler = (index) => {
    // parameter로 현재 선택한 인덱스 값을 전달해야 하며, 이벤트 객체(event)는 쓰지 않는다
    // 해당 함수가 실행되면 현재 선택된 Tab Menu 가 갱신.
    clickTab(index);
  };
return (
    <>
      <TabMenu> 
          {menuArr.map((el,index) => (
              <div className={index === currentTab ? "submenu focused" : "submenu" }
              onClick={() => selectMenuHandler(index)}>{el.name}
              </div>
            ))}
      </TabMenu>
      {menuArr[currentTab].content}
    </>
  );
};