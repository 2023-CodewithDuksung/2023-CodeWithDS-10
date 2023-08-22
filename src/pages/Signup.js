import React, { useState, useCallback, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useInput from "./useInput";
import { useUserDispatch } from "../UserContext";

import "./Signup.css";

import so_img from "../img/siren_orduk.jpg";
import name from "../img/name.jpg";
import mail from "../img/mail.jpg";

//백연결
import axios from "axios";

const SignUp = () => {
  const navigate = useNavigate();
  const [id, onChangeId, setId] = useInput("");
  const [pwd, onChangePwd, setPwd] = useInput("");
  const [confirmPwd, onChangeConfirmPwd, setConfirmPwd] = useInput("");
  const [name, onChangeName, setName] = useInput("");
  const [userType, setUserType] = useState("user");

  const dispatch = useUserDispatch();

  const [errorMessage, setErrorMessage] = useState({
    idError: "",
    pwdError: "",
    confirmPwdError: "",
  });
  const { idError, pwdError, confirmPwdError } = errorMessage;

  // 아이디, 비밀번호 형식 검증 정규 표현식
  const inputRegexs = {
    idReg: /^[A-Za-z0-9]{5,15}@duksung\.ac\.kr$/,
    pwdReg: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/g,
  };

  const validationCheck = useCallback((input, regex) => {
    if (input === "") {
      return true;
    } else {
      return regex.test(input);
    }
  }, []);

  const onReset = useCallback(() => {
    setId("");
    setPwd("");
    setConfirmPwd("");
  }, [setId, setPwd, setConfirmPwd]);

  useEffect(() => {
    if (validationCheck(id, inputRegexs.idReg) || id === "") {
      setErrorMessage((prevErrors) => ({
        ...prevErrors,
        idError: "",
      }));
    } else {
      setErrorMessage((prevErrors) => ({
        ...prevErrors,
        idError: "메일주소가 올바르지 않습니다.",
      }));
    }
  }, [id]);

  useEffect(() => {
    if (validationCheck(pwd, inputRegexs.pwdReg) || pwd === "") {
      setErrorMessage((prevErrors) => ({
        ...prevErrors,
        pwdError: "",
      }));
    } else {
      setErrorMessage((prevErrors) => ({
        ...prevErrors,
        pwdError:
          "비밀번호는 최소 하나의 문자 및 하나의 숫자로 8자 이상이어야 합니다.",
      }));
    }
  }, [pwd]);

  useEffect(() => {
    if (pwd === confirmPwd || confirmPwd === "") {
      setErrorMessage((prevErrors) => ({
        ...prevErrors,
        confirmPwdError: "",
      }));
    } else {
      setErrorMessage((prevErrors) => ({
        ...prevErrors,
        confirmPwdError: "비밀번호 확인이 일치하지 않습니다.",
      }));
    }
  }, [confirmPwd]);

  const onSignUp = () => {
    alert("회원 가입 완료");
    navigate("/");
    onReset();

    dispatch({
      type: "CREATE_USER",
      user: {
        id,
        pwd,
        name,
      },
    });
  };

  return (
    <div className="signup-container">
      <img className="logo_img" src={so_img}></img>
      <input
        type="text"
        placeholder="메일 입력(덕성여자대학교 이메일)"
        value={id}
        onChange={onChangeId}
        className="signup_id"
        required
      />
      {idError ? <div style={{ color: "red" }}>{idError}</div> : ""}

      <br />
      <button>중복 확인</button>
      <br />

      <input
        type="password"
        placeholder="비밀번호"
        value={pwd}
        onChange={onChangePwd}
        className="signup_pwd"
        required
      />
      {pwdError ? <div style={{ color: "red" }}>{pwdError}</div> : ""}

      <br />

      <input
        type="password"
        placeholder="비밀번호 확인"
        value={confirmPwd}
        onChange={onChangeConfirmPwd}
        className="signup_confirmPwd"
        required
      />
      {confirmPwdError ? (
        <div style={{ color: "red" }}>{confirmPwdError}</div>
      ) : (
        ""
      )}

      <br />

      <input
        type="name"
        placeholder="닉네임 입력"
        value={name}
        onChange={onChangeName}
        className="signup_name"
        required
      />

      <br />
      <br />

      <button type="button" onClick={onSignUp} className="signup_button_2">
        가입
      </button>
      <br />
      <br />
    </div>
  );
};

export default SignUp;
