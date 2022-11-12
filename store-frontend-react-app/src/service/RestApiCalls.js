import { APP_CLIENT_ID, APP_CLIENT_SECRET, BACKEND_API_GATEWAY_URL } from '../constants/appConstants';
import axios from 'axios';
import qs from 'qs';

export const postSignupApi = (singupRequestBody) => {
  const axiosConfig = getAxiosConfig();
  const responseData = axios.post(`${BACKEND_API_GATEWAY_URL}/api/account/signup`, singupRequestBody, axiosConfig).then((response) => {
    return response.data;
  });
  return responseData;
};

export const postLoginApi = async (loginRequestBody) => {
  const axiosConfig = {
    headers: {
      'Authorization': 'Basic ' + Buffer.from(APP_CLIENT_ID + ':' + APP_CLIENT_SECRET).toString('base64'),
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  };
  const loginRequestBodyEncoded = qs.stringify(loginRequestBody);
  alert(loginRequestBody);
  const responseData = await axios
    .post(`${BACKEND_API_GATEWAY_URL}/api/account/oauth/token`, loginRequestBodyEncoded, axiosConfig)
    .then((response) => {
      return response.data;
    });
  return responseData;
};

export const getUserInfoApi = async () => {
  const axiosConfig = getAxiosConfig();
  alert('second');
  const responseData = await axios.get(`${BACKEND_API_GATEWAY_URL}/api/account/userInfo`, axiosConfig).then((response) => {
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
