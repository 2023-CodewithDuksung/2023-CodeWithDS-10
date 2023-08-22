import React from "react";
import { useUserState } from "../UserContext";
import Header from "./Header";

const Home = () => {
  const { user } = useUserState();

  return (
    <div>
      <Header />
      <h1>{user.userId}님 </h1>
    </div>
  );
};

export default Home;
