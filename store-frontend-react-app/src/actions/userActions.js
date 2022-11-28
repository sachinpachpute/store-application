import {
  userLoginRequest,
  userLoginSuccess,
  userLoginFail,
  userLogout,
  userRegisterRequest,
  userRegisterSuccess,
  userRegisterFail,
} from "../reducers/userSlice";

import { getErrorMessage } from '../service/CommonUtils';

import {
  getUserInfoApi,
  postLoginApi,  
  postSignupApi,
} from '../service/RestApiCalls';

export const login = (code) => async (dispatch) => {
  try {
    dispatch(userLoginRequest());

    //Login
    const loginResponse = await postLoginApi(code);
    console.log(loginResponse.access_token);    
    alert(loginResponse.access_token);
alert('before userInfo');
    const userInfo = {
      token: loginResponse.access_token
    };

    localStorage.setItem('userInfo', JSON.stringify(userInfo));

    //Get UserInfo
    const userInfoResponse = await getUserInfoApi();
    alert('inside login method in userActions 3')
    userInfoResponse.token = loginResponse.access_token;
    userInfoResponse.refresh_token = loginResponse.refresh_token;
    
    dispatch(userLoginSuccess(userInfoResponse));
  
  } catch (error) {
    dispatch(userLoginFail(getErrorMessage(error)));
  }  
};

// export const login = (usernameOrEmail, password) => async (dispatch) => {
//   try {
//     dispatch(userLoginRequest());

//     const loginRequest = {
//       grant_type: 'password',
//       username:usernameOrEmail,
//       password: password
//     };

//     //Login
//     const loginResponse = await postLoginApi(loginRequest);

//     const userInfo = {
//       token: loginResponse.access_token
//     };

//     localStorage.setItem('userInfo', JSON.stringify(userInfo));
//     alert('inside login method in userActions 2')

//     //Get UserInfo
//     const userInfoResponse = await getUserInfoApi();
//     alert('inside login method in userActions 3')
//     userInfoResponse.token = loginResponse.access_token;
//     userInfoResponse.refresh_token = loginResponse.refresh_token;
    
//     dispatch(userLoginSuccess(userInfoResponse));
  
//   } catch (error) {
//     dispatch(userLoginFail(getErrorMessage(error)));
//   }  
// };

export const logout = () => (dispatch) => {
  localStorage.clear();
  console.log('LOGOUT ACTION!!!');
  dispatch(userLogout());
};

export const register = (userName, firstName, email, password) => async (dispatch) => {
  try {
    dispatch(userRegisterRequest());

    //SignUp
    const signUpRequest = {
      grant_type: 'password',
      userName,
      password,
      firstName,
      email
    };

    //SignUp
    await postSignupApi(signUpRequest);

    //Login
    const loginRequest = {
      grant_type: 'password',
      username: userName,
      password: password
    };
    const loginResponse = await postLoginApi(loginRequest);

    const userInfo = {
      token: loginResponse.access_token
    };
    localStorage.setItem('userInfo', JSON.stringify(userInfo));

    //Get UserInfo
    const userInfoResponse = await getUserInfoApi();
    userInfoResponse.token = loginResponse.access_token;

    dispatch(userRegisterSuccess(userInfoResponse));    
    dispatch(userLoginSuccess(userInfoResponse));
    
    localStorage.setItem('userInfo', JSON.stringify(userInfoResponse));
  } catch (error) {
    dispatch(userRegisterFail(getErrorMessage(error)));    
  }
};