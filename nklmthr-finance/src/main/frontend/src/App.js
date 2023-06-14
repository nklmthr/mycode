import React from 'react';
import ReactDOM from 'react-dom/client';
import logo from './logo.svg';
import './App.css';
import AccountType from './AccountType';

class App extends React.Component {
    constructor(){
        super();
    }

    render() {
        return <AccountType/>
    }
}


export default App;
