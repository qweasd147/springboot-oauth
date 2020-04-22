import React from 'react';
import logo from './logo.svg';
import './App.css';
import Login from './pages/login';
import Approval from './pages/approval';
//import { Router, Router.Route, Switch } from 'react-router';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/login" component={Login} />
        <Route path="/approval" component={Approval} />
        <Route path="/oauth/confirm_access" component={Approval} />
        <Route path="/oauth/authorize" component={Approval} />
      </Switch>
    </Router>
    
  );
}

export default App;
