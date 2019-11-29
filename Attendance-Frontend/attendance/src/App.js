import React from 'react';
import './App.css';
import Body from "./modules/navigation/Body";
import Navigation from "./modules/navigation/Navigation";
import { BrowserRouter, } from 'react-router-dom';

function App() {
  return (
    <React.Fragment>
      <BrowserRouter>
        <Navigation />
        <Body />
      </BrowserRouter>
    </React.Fragment>
  );
}

export default App;
