import {
  userLoginRequest,
  userLoginSuccess,
  userLoginFail,
  userLogout,
} from "../reducers/userSlice";

import { getErrorMessage } from '../service/CommonUtils';

import {
  getUserInfoApi,
  postLoginApi,  
} from '../service/RestApiCalls';

export const login = (usernameOrEmail, password) => async (dispatch) => {
  try {
    dispatch(userLoginRequest());

    const loginRequest = {
      grant_type: 'password',
      username:usernameOrEmail,
      password: password
    };

    //Login
    const loginResponse = await postLoginApi(loginRequest);

    const userInfo = {
      token: loginResponse.access_token
    };

    localStorage.setItem('userInfo', JSON.stringify(userInfo));

    //Get UserInfo
    const userInfoResponse = await getUserInfoApi();
    userInfoResponse.token = loginResponse.access_token;
    userInfoResponse.refresh_token = loginResponse.refresh_token;
    
    dispatch(userLoginSuccess(userInfoResponse));
  
  } catch (error) {
    dispatch(userLoginFail(getErrorMessage(error)));
  }  
};

export const logout = () => (dispatch) => {
  localStorage.clear();
  console.log('LOGOUT ACTION!!!');
  dispatch(userLogout());
};