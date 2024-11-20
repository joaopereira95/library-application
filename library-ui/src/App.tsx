
import { Route, Routes } from 'react-router-dom';
import NavigationBar from './template/NavigationBar';

import 'bootstrap/dist/css/bootstrap.min.css';

import LivrosPage from './pages/Livros.page';
import UsuariosPage from './pages/Usuarios.page';
import EmprestimosPage from './pages/Emprestimos.page';
import LivrosOnlinePage from './pages/LivrosOnline.page';
import RecomendacoesLivrosPage from './pages/RecomendacoesLivros.page';

function App() {
  

  return (        
    <>    <NavigationBar></NavigationBar>    
          <Routes>                
             <Route path='/livros' Component={LivrosPage}/>
             <Route path='/livros/recomendacoes' Component={RecomendacoesLivrosPage}/>
             <Route path='/livros/google-books' Component={LivrosOnlinePage}/>
             <Route path='/usuarios' Component={UsuariosPage}/>
             <Route path='/emprestimos' Component={EmprestimosPage}/>             
             <Route path='/' Component={EmprestimosPage}/>            
          </Routes>
    </>
);
}

export default App;