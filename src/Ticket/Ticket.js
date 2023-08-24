import React from "react";
import "./Ticket.css";

const Ticket = ({ storeName, menuName, orderNumber }) => {
  return (
    <div className="ticket">
      <h2>모바일 티켓</h2>
      <p>
        <strong>가게 이름:</strong> {storeName}
      </p>
      <p>
        <strong>메뉴 이름:</strong> {menuName}
      </p>
      <p>
        <strong>주문 번호:</strong> {orderNumber}
      </p>
    </div>
  );
};

export default Ticket;
