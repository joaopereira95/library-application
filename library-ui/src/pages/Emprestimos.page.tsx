import { useEffect, useState } from 'react';

import { Button, Container, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';

import SnackbarComponent from '../component/SnackbarComponent';
import BackdropComponent from '../component/BackdropComponent';

import { atualizarLivro, cadastrarLivro, excluirLivro, listarLivros } from '../service/Livros.service';

import { CadastroEmprestimoModel } from '../model/CadastroEmprestimo.model';
import { cadastrarEmprestimo, listarEmprestimos, realizarDevolucao } from '../service/Emprestimos.service';
import GerenciamentoEmprestimos from '../component/emprestimos/GerenciamentoEmprestimos';
import TabelaEmprestimos from '../component/emprestimos/TabelaLivros';
import { EmprestimoModel } from '../model/Emprestimo.model';

function EmprestimosPage() {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);
  const [successMessage, setSuccessMessage] = useState(false);
  const [messageText, setMessageText] = useState('');
  const [emprestimos, setEmprestimos] = useState<EmprestimoModel[]>([]);
  const [emprestimoEmDevolucao, setEmprestimoEmDevolucao] = useState<EmprestimoModel | null>(null); 
  const [openConfirmDialog, setOpenConfirmDialog] = useState(false); 

  const MSG_EMPRESTIMO_CADASTRADO_COM_SUCESSO = 'O empréstimo foi cadastrado com sucesso!';
  const MSG_DEVOLUCAO_REALIZADA_COM_SUCESSO = 'A devolução do empréstimo foi realizada com sucesso!';
  const MSG_ERRO_CADASTRO_EMPRESTIMO = 'Ocorreu um erro ao cadastrar o empréstimo.';
  const MSG_ERRO_ATUALIZACAO_EMPRESTIMO = 'Ocorreu um erro ao realizar devolução de empréstimo.';
  const MSG_ERRO_AO_BUSCAR_EMPRESTIMOS = 'Ocorreu um erro ao buscar os empréstimos.';

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
  
  const handleSubmit = (emprestimo: CadastroEmprestimoModel, limparFormulario: () => void) => {
    cadastrarNovoEmprestimo(emprestimo);
    limparFormulario();

  };

  const cadastrarNovoEmprestimo = (emprestimo: CadastroEmprestimoModel) => {
    cadastrarEmprestimo(emprestimo)
      .then(() => {
        openSuccessMessage(MSG_EMPRESTIMO_CADASTRADO_COM_SUCESSO);      
        buscarEmprestimos();
      })
      .catch(() => {
        openErrorMessage(MSG_ERRO_CADASTRO_EMPRESTIMO);
      })
      .finally(closeLoading);
  }

  const handleDevolver = (emprestimo: EmprestimoModel) => {
    setEmprestimoEmDevolucao(emprestimo);
    setOpenConfirmDialog(true);
  }

  const confirmarDevolucao = () => {
    if (emprestimoEmDevolucao) {
      realizarDevolucao(emprestimoEmDevolucao.id)
        .then(() => {
          setEmprestimoEmDevolucao(null);
          setOpenConfirmDialog(false);
          openSuccessMessage(MSG_DEVOLUCAO_REALIZADA_COM_SUCESSO); 
          buscarEmprestimos();  
        })
        .catch(() => {
          openErrorMessage(MSG_ERRO_ATUALIZACAO_EMPRESTIMO);
        });
      
    }
  };

  const cancelarDevolucao = () => {
    setEmprestimoEmDevolucao(null);
    setOpenConfirmDialog(false);
  };
  
  const buscarEmprestimos = () => {
    listarEmprestimos()
      .then(response => {
        setEmprestimos(response);
      })
      .catch(() => {
        openErrorMessage(MSG_ERRO_AO_BUSCAR_EMPRESTIMOS);
      });
  };

  useEffect(() => {
    buscarEmprestimos();
  }, []);

  return (
    <Container>
      <BackdropComponent open={loading} />
      <SnackbarComponent open={errorMessage} message={messageText} onClose={closeErrorMessage} severity="error" />
      <SnackbarComponent open={successMessage} message={messageText} onClose={closeSuccessMessage} severity="success" />
      <GerenciamentoEmprestimos onSubmit={handleSubmit} openErrorMessage={openErrorMessage} openLoading={openLoading} emprestimo={emprestimoEmDevolucao}/>

      { emprestimos.length > 0 &&
        <TabelaEmprestimos emprestimos={emprestimos} onReturn={handleDevolver} />
      }

    <Dialog open={openConfirmDialog} onClose={cancelarDevolucao}>
        <DialogTitle>Confirmar devolução de livro</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Deseja confirmar a devolução do livro <strong>{emprestimoEmDevolucao?.livro?.titulo}</strong>?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={cancelarDevolucao} color="primary">
            Cancelar
          </Button>
          <Button onClick={confirmarDevolucao} color="error" autoFocus>
            Confirmar
          </Button>
        </DialogActions>
      </Dialog>

    </Container>
  );
}

export default EmprestimosPage;