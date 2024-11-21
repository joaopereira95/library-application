import { useEffect, useState } from 'react';

import { Button, Container, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';

import SnackbarComponent from '../component/SnackbarComponent';
import BackdropComponent from '../component/BackdropComponent';

import { atualizarLivro, cadastrarLivro, excluirLivro, listarLivros } from '../service/Livros.service';

import { LivroModel } from '../model/Livro.model';
import { CadastroLivroModel } from '../model/CadastroLivro.model';

import TabelaLivros from '../component/livros/locais/TabelaLivros';
import CadastroLivros from '../component/livros/locais/CadastroLivros';
import { Problem } from '../model/Problem.model';

function LivrosPage() {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);
  const [successMessage, setSuccessMessage] = useState(false);
  const [messageText, setMessageText] = useState('');
  const [livros, setLivros] = useState<LivroModel[]>([]);
  const [livroEmEdicao, setLivroEmEdicao] = useState<CadastroLivroModel | null>(null);
  const [livroEmExclusao, setLivroEmExclusao] = useState<LivroModel | null>(null); 
  const [openConfirmDialog, setOpenConfirmDialog] = useState(false); 

  const MSG_LIVRO_CADASTRADO_COM_SUCESSO = 'O livro foi cadastrado com sucesso!';
  const MSG_LIVRO_ATUALIZADO_COM_SUCESSO = 'O livro foi atualizado com sucesso!';
  const MSG_LIVRO_EXCLUIDO_COM_SUCESSO = 'O livro foi excluído com sucesso!';

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
  
  const handleSubmit = (livro: CadastroLivroModel, limparFormulario: () => void) => {

    if (livro.id === 0) {
      cadastrarNovoLivro(livro);

    } else {
      atualizarLivroExistente(livro);
    }

    limparFormulario();

  };

  const cadastrarNovoLivro = (livro: CadastroLivroModel) => {
    cadastrarLivro(livro)
      .then(() => {
        openSuccessMessage(MSG_LIVRO_CADASTRADO_COM_SUCESSO);      
        buscarLivros();
      })
      .catch((error: Problem) => {
        openErrorMessage(error.detail);
      })
      .finally(closeLoading);
  }

  const atualizarLivroExistente = (livro: CadastroLivroModel) => {
    atualizarLivro(livro)
      .then(() => {
        openSuccessMessage(MSG_LIVRO_ATUALIZADO_COM_SUCESSO);      
        buscarLivros();
      })
      .catch((error: Problem) => {
        openErrorMessage(error.detail);
      })
      .finally(closeLoading);
  }

  const handleEditar = (livro: LivroModel) => {
    setLivroEmEdicao({
      id: livro.id,
      titulo: livro.titulo,
      autor: livro.autor,
      isbn: livro.isbn,
      dataPublicacao: livro.dataPublicacao,
      codigoCategoria: livro.categoria.id,
    });
  }

  const handleExcluir = (livro: LivroModel) => {
    setLivroEmExclusao(livro);
    setOpenConfirmDialog(true);
  }

  const confirmarExclusao = () => {

    openLoading();

    if (livroEmExclusao) {
      excluirLivro(livroEmExclusao.id)
        .then(() => {
          setLivroEmExclusao(null);
          setOpenConfirmDialog(false);
          openSuccessMessage(MSG_LIVRO_EXCLUIDO_COM_SUCESSO); 
          buscarLivros();  
        })
        .catch((error: Problem) => {
          openErrorMessage(error.detail);

        }).finally(closeLoading)
      
    }
  };

  const cancelarExclusao = () => {
    setLivroEmExclusao(null);
    setOpenConfirmDialog(false);
  };
  
  const buscarLivros = () => {
    listarLivros()
      .then(response => {
        setLivros(response);
      })
      .catch((error: Problem) => {
        openErrorMessage(error.detail);
      });
  };

  useEffect(() => {
    buscarLivros();
  }, []);

  return (
    <Container>
      <BackdropComponent open={loading} />
      <SnackbarComponent open={errorMessage} message={messageText} onClose={closeErrorMessage} severity="error" />
      <SnackbarComponent open={successMessage} message={messageText} onClose={closeSuccessMessage} severity="success" />
      <CadastroLivros onSubmit={handleSubmit} openErrorMessage={openErrorMessage} openLoading={openLoading} livro={livroEmEdicao}/>

      { livros.length > 0 &&
        <TabelaLivros livros={livros} onEdit={handleEditar} onDelete={handleExcluir} />
      }

    <Dialog open={openConfirmDialog} onClose={cancelarExclusao}>
        <DialogTitle>Confirmar exclusão de livro</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Deseja excluir o livro <strong>{livroEmExclusao?.titulo}</strong>?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={cancelarExclusao} color="primary">
            Cancelar
          </Button>
          <Button onClick={confirmarExclusao} color="error" autoFocus>
            Excluir
          </Button>
        </DialogActions>
      </Dialog>

    </Container>
  );
}

export default LivrosPage;