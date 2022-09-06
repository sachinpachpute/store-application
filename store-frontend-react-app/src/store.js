import {configureStore } from '@reduxjs/toolkit';
import logger from 'redux-logger'
import { 
  productListSlice,
  productDetailsSlice,
  productReviewsSlice,
  productReviewCreateSlice,
  productCreateSlice,
} from './reducers/productSlice';
import ToastMiddleware from './middlewares/ToastMiddleware';

const store = configureStore({ 
  reducer:{
    productList: productListSlice.reducer,
    productDetails: productDetailsSlice.reducer,
    productReviews: productReviewsSlice.reducer,
    productReviewCreate: productReviewCreateSlice.reducer,
    productCreate: productCreateSlice.reducer,
    //userReducer: userSlice,
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(logger, ToastMiddleware),
})

export default store;
