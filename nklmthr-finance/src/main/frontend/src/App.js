import React from 'react';
import ReactDOM from 'react-dom/client';
import { Outlet, Link } from "react-router-dom";
import AccountType from './AccountType';
import './App.css';
export default function App() {
  return (
    <>
      <div id="sidebar">
        <h1>My Finance App</h1>
        <Link to='/accountTypes'><button className="addAccountTypeButton">Account Types</button></Link><br/><br/>
        <Link to='/institutions'><button className="addAccountTypeButton">Institutions</button></Link><br/><br/>
        <Link to='/categories'><button className="addAccountTypeButton">Categories</button></Link><br/><br/>
        <Link to='/accounts'><button className="addAccountTypeButton">Accounts</button></Link><br/><br/>
        <Link to='/transactions'><button className="addAccountTypeButton">Transactions</button></Link><br/><br/>
      </div>
      <div id="detail"><Outlet /></div>
    </>
  );
}