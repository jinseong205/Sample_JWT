import React from 'react';
import { Link } from "react-router-dom";

const Home = () => {
    return (
        <div>
            <Link to="/" style={{ textDecoration: "none" }}><h1>JWT Sample</h1></Link>
            <p>Please login or signup to continue</p>
            <Link to="/login" style={{ textDecoration: "none" }}>로그인</Link>
            &nbsp;
            <Link to="/signup" style={{ textDecoration: "none" }}>회원가입</Link>
            
        </div>
    );
};

export default Home;