import { useState } from 'react';

import { Button, Container, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';

import SnackbarComponent from '../component/SnackbarComponent';
import BackdropComponent from '../component/BackdropComponent';

import { consultarLivrosOnlinePorTitulo, importarLivroOnline } from '../service/Livros.service';

import { LivroOnlineModel } from '../model/LivroOnline.model';
import TabelasLivrosOnline from '../component/livros/online/TabelaLivrosOnline';
import BuscaLivrosOnline from '../component/livros/online/BuscaLivrosOnline';
import { Problem } from '../model/Problem.model';

function LivrosOnlinePage() {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);
  const [successMessage, setSuccessMessage] = useState(false);
  const [messageText, setMessageText] = useState('');
  const [livros, setLivros] = useState<LivroOnlineModel[]>([]);
  const [livroEmImportacao, setLivroEmImportacao] = useState<LivroOnlineModel | null>(null); 
  const [openConfirmDialog, setOpenConfirmDialog] = useState(false); 

  const MSG_LIVRO_IMPORTADO_COM_SUCESSO = 'O livro foi importado com sucesso!';
  const MSG_ERRO_NENHUM_LIVRO_ENCONTRADO = 'Nenhum livro foi encontrado com o título informado.';

  const closeLoading = () => {
    setLoading(false);
  };

  const openLoading = () => {
    setLoading(true);
  };

  const closeErrorMessage = () => {
    setErrorMessage(false);
  };
  
  const openErrorMessage = (message: string) => {
    setMessageText(message);
    setErrorMessage(true);   
  };

  const closeSuccessMessage = () => {
    setSuccessMessage(false);
  };
  
  const openSuccessMessage = (message: string) => {
    setMessageText(message);
    setSuccessMessage(true);   
  };

  const pesquisarLivros = (titulo: string) => {
    consultarLivrosOnlinePorTitulo(titulo)
      .then((livros) => {
        setLivros(livros);

        if (livros.length === 0) {
          openErrorMessage(MSG_ERRO_NENHUM_LIVRO_ENCONTRADO);
        }

      })
      .catch((error: Problem) => {
        openErrorMessage(error.detail);
      })
      .finally(closeLoading);
  }

  const handleImportar = (livro: LivroOnlineModel) => {
    setLivroEmImportacao(livro);
    setOpenConfirmDialog(true);
  }

  const confirmarImportacao = () => {

    if (livroEmImportacao) {

      openLoading();

      importarLivroOnline(livroEmImportacao.id)
        .then(() => {
          setLivroEmImportacao(null);
          setOpenConfirmDialog(false);
          openSuccessMessage(MSG_LIVRO_IMPORTADO_COM_SUCESSO); 
        })
        .catch((error: Problem) => {
          openErrorMessage(error.detail);
        }).finally(closeLoading)
      
    }
  };

  const cancelarImportacao = () => {
    setLivroEmImportacao(null);
    setOpenConfirmDialog(false);
  };

  return (
    <Container>
      <BackdropComponent open={loading} />
      <SnackbarComponent open={errorMessage} message={messageText} onClose={closeErrorMessage} severity="error" />
      <SnackbarComponent open={successMessage} message={messageText} onClose={closeSuccessMessage} severity="success" />
      <BuscaLivrosOnline onSearch={pesquisarLivros} openErrorMessage={openErrorMessage} openLoading={openLoading} />

      { livros.length > 0 &&
        <TabelasLivrosOnline livros={livros} onImport={handleImportar} />
      }

    <Dialog open={openConfirmDialog} onClose={cancelarImportacao}>
        <DialogTitle>Confirmar importação de livro</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Deseja importar o livro <strong>{livroEmImportacao?.titulo}</strong>?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={cancelarImportacao} color="primary">
            Cancelar
          </Button>
          <Button onClick={confirmarImportacao} color="error" autoFocus>
            Confirmar
          </Button>
        </DialogActions>
      </Dialog>

    </Container>
  );
}

export default LivrosOnlinePage;