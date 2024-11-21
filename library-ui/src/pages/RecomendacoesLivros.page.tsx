import { useEffect, useState } from 'react';

import { Button, Container, Dialog, DialogActions, DialogContent  } from '@mui/material';

import SnackbarComponent from '../component/SnackbarComponent';
import BackdropComponent from '../component/BackdropComponent';

import { listarUsuarios } from '../service/Usuarios.service';

import { UsuarioModel } from '../model/Usuario.model';
import { listarRecomendacoesLivros } from '../service/Livros.service';
import { LivroModel } from '../model/Livro.model';
import TabelaUsuariosComRecomendacoes from '../component/livros/recomendacoes/TabelaUsuariosComRecomendacoes';
import TabelaLivrosRecomendados from '../component/livros/recomendacoes/TabelaLivrosRecomendados';
import { Problem } from '../model/Problem.model';

function RecomendacoesLivrosPage() {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);
  const [messageText, setMessageText] = useState('');
  const [usuarios, setUsuarios] = useState<UsuarioModel[]>([]);
  const [livrosRecomendados, setLivrosRecomendados] = useState<LivroModel[]>([]);
  const [openRecommendDialog, setOpenRecommendDialog] = useState(false);

  const openLoading = () => {
    setLoading(true);
  };

  const closeLoading = () => {
    setLoading(false);
  };

  const closeErrorMessage = () => {
    setErrorMessage(false);
  };
  
  const openErrorMessage = (message: string) => {
    setMessageText(message);
    setErrorMessage(true);   
  };

  const handleRecomendar = (usuario: UsuarioModel) => {

    openLoading();

    listarRecomendacoesLivros(usuario.id)
      .then(recomendacoes => {

      setOpenRecommendDialog(true);
      setLivrosRecomendados(recomendacoes);

    }).catch((error: Problem) => {
      openErrorMessage(error.detail);

    }).finally(() => {
      closeLoading();
    })
    
  }

  const fecharDialogoRecomendacoes = () => {
    setLivrosRecomendados([]);
    setOpenRecommendDialog(false);
  };
  
  const buscarUsuarios = () => {
    openLoading();
    
    listarUsuarios()
      .then(response => {
        setUsuarios(response);
      })
      .catch((error: Problem) => {
        openErrorMessage(error.detail);
      })
      .finally(closeLoading);
  };

  useEffect(() => {
    buscarUsuarios();
  }, []);

  return (
    <Container>
      <BackdropComponent open={loading} />
      <SnackbarComponent open={errorMessage} message={messageText} onClose={closeErrorMessage} severity="error" />

      {usuarios.length > 0 &&
        <TabelaUsuariosComRecomendacoes usuarios={usuarios} onRecommend={handleRecomendar} />
      }

      <Dialog open={openRecommendDialog} onClose={fecharDialogoRecomendacoes} fullWidth={true}>
        
        <DialogContent>
        <TabelaLivrosRecomendados livros={livrosRecomendados}/>
        </DialogContent>
        <DialogActions>
          <Button onClick={fecharDialogoRecomendacoes} color="primary">
            Fechar
          </Button>
        </DialogActions>
      </Dialog>

    </Container>
  );
}

export default RecomendacoesLivrosPage;