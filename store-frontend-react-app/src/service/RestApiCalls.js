import { BACKEND_API_GATEWAY_URL, CLIENT_ID, REDIRECT_URI, AUTHORIZATION_SERVER_BASE_URL } from '../constants/appConstants';
import axios from 'axios';
import qs from 'qs';

export const postSignupApi = (singupRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.post(`${BACKEND_API_GATEWAY_URL}/api/account/signup`, singupRequestBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const postLoginApi = async (code) => {

  // const PKCEAuthCodeSecondStep = ( code ) => {
  //   let oidcURL = `${process.env.OIDC_IDP_URL}/token`;
  
  //   let params = qs.stringify( {
  //     grant_type: "authorization_code",
  //     redirect_uri: "http://localhost/login_oidc",
  //     client_id: process.env.OIDC_CLIENT_ID,
  //     code_verifier: localStorage.getItem( 'code_verifier' ),
  //     code
  //   } );
  
  //   localStorage.removeItem( 'code_verifier' );
  //   return axios.post( oidcURL, params,
  //     { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } }
  //   ).then( res => {
  //     localStorage.setItem( 'access_token', res.data.access_token );
  //     return res.data
  //   } );
  // }

  //-----------------------------------------------------------
alert('postLoginApi:code verifier before authorize call: ' + sessionStorage.getItem(('codeVerifier')));
alert('postLoginApi: code challenge before authorize call: ' + sessionStorage.getItem(('codeChallenge')));

  const params = {
    grant_type: 'authorization_code',
    redirect_uri: REDIRECT_URI,
    client_id: CLIENT_ID,
    code_verifier: sessionStorage.getItem('codeVerifier'),
    code
  };
  const paramsEncoded = qs.stringify(params);  
  sessionStorage.removeItem('codeVerifier');

  const axiosConfig = {
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  };
    
  const responseData = await axios
    .post(`${AUTHORIZATION_SERVER_BASE_URL}/oauth2/token`, paramsEncoded, axiosConfig)
    .then((response) => {           
      return response.data;
    });
  return responseData;
};

// const headers = new Headers();
// headers.append('Content-type', 'application/x-www-form-urlencoded');
            
//             const verifier = localStorage.getItem('codeVerifier');
            
            
//             const url = `http://auth-server:3001/oauth2/token?client_id=client&redirect_uri=http://127.0.0.1:3000/authorized&grant_type=authorization_code&code=${code}&code_verifier=qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI`;
            

//             //localStorage.removeItem('codeVerifier');

//             console.log(url);
//             alert(url);

//            const responseData = fetch(url, {
//                 method: 'POST',
//                 mode: 'cors',
//                 headers
//             }).then(async (response) => {
//                 //alert(response);
//                 const token = await response.json();
//                 alert(token.id_token);                
//                 //alert(token);
//                 if(token?.id_token) {
//                     localStorage.setItem('id_token', token.id_token);
//                     alert('redirecting to home screen');
//                     //navigate('/');
//                 }
//             }).catch((err) => {
//                 console.log(err);
//             })
//             return responseData;
//             };

export const getUserInfoApi = async () => {
  const axiosConfig = getAxiosConfig();
  alert('second');
  //const responseData = await axios.get(`${BACKEND_API_GATEWAY_URL}/api/account/userInfo`, axiosConfig).then((response) => {
    const responseData = await axios.get(`${AUTHORIZATION_SERVER_BASE_URL}/userInfo`, axiosConfig).then((response) => {
    alert('third');
    return response.data;
  });
  return responseData;
};

export const getAllProductsDetailApi = async (pageNumber) => {
    /*const responseData = axios.get("https://fakestoreapi.com/products").then((response) => {
    return response.data;*/
    //const responseData = axios.get("http://localhost:6001/products").then((response) => {
    const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/catalog/products?page=${pageNumber}&size=8`).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getProductDetailApi = async (productId) => {
  const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/catalog/product/${productId}`).then((response) => {
    return response.data;
  });
  return responseData;
};

export const createProductApi = async (productReqBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = await axios.post(`${BACKEND_API_GATEWAY_URL}/api/catalog/product`, productReqBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const updateProductDetailApi = async (productReqBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = await axios.put(`${BACKEND_API_GATEWAY_URL}/api/catalog/product`, productReqBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getImageApi = async (imageId) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/catalog/image/${imageId}`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getProductReviewsApi = async (productId) => {
  const responseData = axios.get(`${BACKEND_API_GATEWAY_URL}/api/catalog/review?productId=${productId}`).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getProductCategories = async () => {
  const responseData = axios
    .get(`${BACKEND_API_GATEWAY_URL}/api/catalog/productCategories?direction=ASC&orderBy=PRODUCTCATEGORYNAME`)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const createProductReviewApi = async (createProductReviewRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios
    .post(`${BACKEND_API_GATEWAY_URL}/api/catalog/review`, createProductReviewRequestBody, axiosConfig)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const uploadImageApi = async (axiosConfig, formData) => {
  const accessToken = JSON.parse(localStorage.getItem('userInfo'))?.token;

  if (accessToken) {
    axiosConfig.headers.Authorization = `Bearer ${accessToken}`;
  }

  const responseData = await axios.post(`${BACKEND_API_GATEWAY_URL}/api/catalog/image/upload`, formData, axiosConfig).then((response) => {
    console.log('Resp ::', response.data);
    return response.data;
  });
  return responseData;
};

export const addToCartApi = async (addToCartRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios
    .post(`${BACKEND_API_GATEWAY_URL}/api/order/cart`, addToCartRequestBody, axiosConfig)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const removeCartItemApi = async (cartItemId) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.delete(`${BACKEND_API_GATEWAY_URL}/api/order/cart/cartItem/${cartItemId}`, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const getCartDetailsApi = async () => {
  const axiosConfig = getAxiosConfig();
  const cartDetails = await axios.get(`${BACKEND_API_GATEWAY_URL}/api/order/cart`, axiosConfig).then((response) => {
    return response.data;
  });

  let sortedCart = {
    ...cartDetails,
    cartItems: cartDetails.cartItems.sort((a, b) => {
      return a.cartItemId.localeCompare(b.cartItemId);
    })
  };

  return sortedCart;
};

const getAxiosConfig = () => {
  const axiosConfig = {
    headers: {
      'Content-Type': 'application/json'    
    }
  };

  const accessToken = JSON.parse(localStorage.getItem('userInfo'))?.token;

  if (accessToken) {
    axiosConfig.headers.Authorization = `Bearer ${accessToken}`;
  }
  return axiosConfig;
};
