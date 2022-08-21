import React from 'react';
import { useDispatch, useSelector } from 'react-redux';


const Header = (props) => {
  const userLogin = useSelector((state) => state.userLogin);
  const { userInfo } = userLogin;

  const dispatch = useDispatch();



return (
    <header>
    </header>
 );
};
export default Header;



