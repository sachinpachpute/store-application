import { createSlice } from "@reduxjs/toolkit";

export const userLoginSlice = createSlice({
    name:'userLogin',
    initialState: {
      state: {},
    },
    reducers: {
      userLoginRequest: (state, action) => {
        state.loading = true;
      },
      userLoginSuccess: (state, action) => {
        state.loading = false;
        state.userInfo = action.payload;
      },
      userLoginFail: (state, action) => {
        state.loading = false;
        state.error = action.payload;
      },
      userLogout: (state, action) => {     
        state = {};   
      },
    }
});

export const { userLoginRequest, userLoginSuccess, userLoginFail, userLogout } = userLoginSlice.actions;