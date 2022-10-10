import React, { useEffect } from 'react';
import { Link, useParams, useNavigate, useLocation } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { Row, Col, ListGroup, Image, Form, Button, Card } from 'react-bootstrap';
import Message from '../components/Message';
import CartItem from '../components/CartItem';
import { addToCartAction, getCartDetailsActio, tempProductsAction } from '../actions/cartActions';
import FullPageLoader from '../components/FullPageLoader';
import { LinkContainer } from 'react-router-bootstrap';

const CartScreen = (props) => {
  let navigateLog = useNavigate()
  let location = useLocation()

  const { id } = useParams();
  const productId = Number(id);
  const qty = location.search ? Number(location.search.split('=')[1]) : 1;
  const dispatch = useDispatch();
  const userLogin = useSelector((state) => state.userLogin);
  const cartState = useSelector((state) => state.cart);
  const { cart } = cartState; 
  const { userInfo } = userLogin;
  const redirect = location.pathname + location.search;

  const productList = useSelector((state) => state.productList);
const { loading, error, products, pageResponse } = productList;


useEffect(() => {    
  dispatch(    
    addToCartAction({
      productId: Number(id),
      quantity: qty
    })
  );
}, []);

  return (
    <div>
      {products &&
        products.map((item, i) => (
          <div key={i}>
            {/* <p>{item.title}</p> */}
            <p>{item.productId} : {item.productName}</p>
          </div>
        ))}
    </div>
  );
};

export default CartScreen;
