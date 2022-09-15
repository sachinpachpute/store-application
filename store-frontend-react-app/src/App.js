import { Container } from 'react-bootstrap';
import "./App.css";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import LoginScreen from './screens/LoginScreen';
import Profile from './pages/Profile';
import Header from './components/Header';
import Footer from './components/Footer';
import HomeScreen from './screens/HomeScreen';
import ProductScreen from './screens/ProductScreen';
import ProductListScreenTemp from './screens/ProductListScreenTemp';
import ProductListScreen from './screens/ProductListScreen';
import ProductCreateScreen from './screens/ProductCreateScreen';
import { createBrowserHistory } from 'history';

export const history = createBrowserHistory();

function App() {
    return (
      <BrowserRouter history={history}>
      <Header></Header>
        <main className='py-3'>
          <Container>
          <Routes>
              <Route path="/login" element={<LoginScreen/>} />
              /*<Route path="/profile" element={<Profile/>} />*/
              <Route path="/productlisttemp" element={<ProductListScreenTemp/>} />
              <Route path="/product/:id" element={<ProductScreen/>} />
              <Route path="/admin/product/create" element={<ProductCreateScreen/>} />
              <Route path="/" element={<HomeScreen/>} />
             </Routes>
          </Container>
        </main>
        <Footer> </Footer>
      </BrowserRouter>
    );
}

export default App;
