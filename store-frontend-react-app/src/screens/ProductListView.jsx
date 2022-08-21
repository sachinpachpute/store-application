import React from "react";
import { useSelector, useDispatch } from "react-redux";
import {listRequest, listSuccess} from "../reducers/productSlice";

export const ProductListView = () => {
    const productList = useSelector((state) => state.productList);
    const status = useSelector((state) => state.loading);

    const { loading, error, products, pageResponse } = productList || {};
    const dispatch = useDispatch()
    return (
        <div>
            <h2>Number of Products - {productList}</h2>
            <h2>Status - {status}</h2>
            <button onClick={() => dispatch(listRequest())}>List Products</button>
        </div> 
    )
}