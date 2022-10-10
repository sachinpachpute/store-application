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

export const userRegisterSlice = createSlice({
  name:'userRegister',
  initialState: {
    state: {},
  },
  reducers: {
    userRegisterRequest: (state, action) => {
      state.loading = true;
      state.error = null;
    },
    userRegisterSuccess: (state, action) => {
      state.loading = false;
      state.userInfo = action.payload;
    },
    userRegisterFail: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },
    userRegisterReset: (state, action) => {
      state.loading = false;
      state.error = null;
    },
    userRegisterLogout: (state, action) => {   
      window.location.href = '/login';        
    },
  }
});

export const { userLoginRequest, userLoginSuccess, userLoginFail, userLogout } = userLoginSlice.actions;
export const { userRegisterRequest, userRegisterSuccess, userRegisterFail, userRegisterLogout } = userRegisterSlice.actions;