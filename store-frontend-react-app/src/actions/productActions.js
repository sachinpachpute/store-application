import {
  listProductsRequest, 
  listProductsSuccess, 
  listProductsFail,
  productReviewsRequest,
  productReviewsSuccess,
  productReviewsFail,
  productCreateReviewRequest,
  productCreateReviewSuccess,
  productCreateReviewFail,
  productDetailsRequest,
  productDetailsSuccess,
  productDetailsFail,
} from "../reducers/productSlice";

import { getErrorMessage } from '../service/CommonUtils';
import {
  getAllProductsDetailApi,
  getProductReviewsApi,
  createProductReviewApi,
  getProductDetailApi
} from '../service/RestApiCalls';


export const listProductsAction = (pageNumber) => async (dispatch) => {
  try {
    dispatch(listProductsRequest());
    //Get All Products Detail
    const responseData = await getAllProductsDetailApi(pageNumber || 0);
    dispatch(listProductsSuccess(responseData));
  } catch (error) {
    dispatch(listProductsFail(getErrorMessage(error)));
  }  
};

export const listProductReviewsAction = (productId) => async (dispatch) => {
  try {
    dispatch(productReviewsRequest());
    //Get Product Reviews
    const productReviews = await getProductReviewsApi(productId);
    dispatch(productReviewsSuccess(productReviews));
  } catch (error) {
    dispatch(productReviewsFail(getErrorMessage(error)));
  }
};

export const createProductReviewAction = (createProductReviewRequestBody) => async (dispatch) => {
  try {
    dispatch(productCreateReviewRequest());
    //Create Product Review
    await createProductReviewApi(createProductReviewRequestBody);
    dispatch(productCreateReviewSuccess());
    dispatch(listProductDetailsAction(createProductReviewRequestBody.productId));
    dispatch(listProductReviewsAction(createProductReviewRequestBody.productId));
  } catch (error) {
    dispatch(productCreateReviewFail(getErrorMessage(error)));
  }
};

export const listProductDetailsAction = (productId) => async (dispatch) => {
  try {
    dispatch(productDetailsRequest());
    //Get Product Detail
    const productDetail = await getProductDetailApi(productId);
    dispatch(productDetailsSuccess(productDetail));
  } catch (error) {
    dispatch(productDetailsFail(getErrorMessage(error)));
  }
};



