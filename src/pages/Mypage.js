import React from "react";
import { Link } from "react-router-dom";
import { useUserState } from "../UserContext";
import Headers from "../Headers/Headers";

const MyPage = () => {
  const { user } = useUserState(); // 사용자 정보 가져오기

  const maskedPassword = "*".repeat(user.userPwd.length);

  return (
    <div>
      <Headers />
      <div className="id">아이디(이메일)</div>
      <div className="id_value">{user.userId}</div>
      <div>닉네임 {user.name}</div>
      <div className="name_value">{user.name}</div>

      <div>비밀번호</div>
      <div>{maskedPassword}</div>

      <Link to="/modify">수정</Link>
      <div>주문내역: </div>
    </div>
  );
};

export default MyPage;
