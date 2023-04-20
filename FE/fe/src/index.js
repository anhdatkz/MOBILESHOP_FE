import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom'
import App from './App';
import { Provider } from 'react-redux';
import store from './stores'
import { getCartItem, getTotals } from './features/cartSlice';

store.dispatch(getTotals())
// if (localStorage.getItem("isLogin") === "true") {
//   store.dispatch(getTotals())
// }
ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <Router>
        <App />
      </Router>
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);



