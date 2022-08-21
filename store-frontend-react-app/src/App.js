import "./App.css";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './pages/Login';
import Profile from './pages/Profile';
import HomeScreen from './screens/HomeScreen';
import ProductScreen from './screens/ProductScreen';
import ProductListScreenTemp from './screens/ProductListScreenTemp';
import ProductListScreen from './screens/ProductListScreen';

function App() {
    return (
      <BrowserRouter>
          <Routes>
              /*<Route path="/login" element={<Login/>} />
              <Route path="/profile" element={<Profile/>} />*/
              <Route path="/productlisttemp" element={<ProductListScreenTemp/>} />
              <Route path="/product/:id" element={<ProductScreen/>} />
              <Route path="/" element={<HomeScreen/>} />
             </Routes>
      </BrowserRouter>
    );
}

export default App;
