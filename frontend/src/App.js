import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/auth/LoginForm';
import Signup from './components/auth/SignupForm';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/login" element= {<Login/>} />
        <Route path="/signup" element= {<Signup/>} />
      </Routes>
    </>
  );
}

export default App;
