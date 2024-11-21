import { useEffect, useState } from 'react';

import { Button, Container, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';

import SnackbarComponent from '../component/SnackbarComponent';
import BackdropComponent from '../component/BackdropComponent';

import { atualizarUsuario, cadastrarUsuario, excluirUsuario, listarUsuarios } from '../service/Usuarios.service';

import { UsuarioModel } from '../model/Usuario.model';
import { CadastroUsuarioModel } from '../model/CadastroUsuario.model';

import CadastroUsuarios from '../component/usuarios/CadastroUsuarios';
import TabelaUsuarios from '../component/usuarios/TabelaUsuarios';
import { Problem } from '../model/Problem.model';

function UsuariosPage() {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState(false);
  const [successMessage, setSuccessMessage] = useState(false);
  const [messageText, setMessageText] = useState('');
  const [usuarios, setUsuarios] = useState<UsuarioModel[]>([]);
  const [usuarioEmEdicao, setUsuarioEmEdicao] = useState<CadastroUsuarioModel | null>(null);
  const [usuarioEmExclusao, setUsuarioEmExclusao] = useState<UsuarioModel | null>(null); 
  const [openConfirmDialog, setOpenConfirmDialog] = useState(false); 

  const MSG_USUARIO_CADASTRADO_COM_SUCESSO = 'O usuário foi cadastrado com sucesso!';
  const MSG_USUARIO_ATUALIZADO_COM_SUCESSO = 'O usuário foi atualizado com sucesso!';
  const MSG_USUARIO_EXCLUIDO_COM_SUCESSO = 'O usuário foi excluído com sucesso!';

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
  
  const handleSubmit = (livro: CadastroUsuarioModel, limparFormulario: () => void) => {

    if (livro.id === 0) {
      cadastrarNovoUsuario(livro);

    } else {
      atualizarUsuarioExistente(livro);
    }

    limparFormulario();

  };

  const cadastrarNovoUsuario = (usuario: CadastroUsuarioModel) => {

    openLoading();
    
    cadastrarUsuario(usuario)
      .then(() => {
        openSuccessMessage(MSG_USUARIO_CADASTRADO_COM_SUCESSO);      
        buscarUsuarios();
      })
      .catch((error: Problem) => {
        openErrorMessage(error.detail);
      })
      .finally(closeLoading);
  }

  const atualizarUsuarioExistente = (usuario: CadastroUsuarioModel) => {

    openLoading();

    atualizarUsuario(usuario)
      .then(() => {
        openSuccessMessage(MSG_USUARIO_ATUALIZADO_COM_SUCESSO);      
        buscarUsuarios();
      })
      .catch((error: Problem) => {
        openErrorMessage(error.detail);
      })
      .finally(closeLoading);
  }

  const handleEditar = (usuario: UsuarioModel) => {
    setUsuarioEmEdicao({
      id: usuario.id,
      nome: usuario.nome,
      email: usuario.email,
      telefone: usuario.telefone,
      dataCadastro: usuario.dataCadastro
    });
  }

  const handleExcluir = (usuario: UsuarioModel) => {
    setUsuarioEmExclusao(usuario);
    setOpenConfirmDialog(true);
  }

  const confirmarExclusao = () => {
    
    if (usuarioEmExclusao) {

      openLoading();

      excluirUsuario(usuarioEmExclusao.id)
        .then(() => {
          setUsuarioEmExclusao(null);
          setOpenConfirmDialog(false);
          openSuccessMessage(MSG_USUARIO_EXCLUIDO_COM_SUCESSO); 
          buscarUsuarios();  
        })
        .catch((error: Problem) => {
          openErrorMessage(error.detail);

        }).finally(closeLoading);
      
    }
  };

  const cancelarExclusao = () => {
    setUsuarioEmExclusao(null);
    setOpenConfirmDialog(false);
  };
  
  const buscarUsuarios = () => {
    listarUsuarios()
      .then(response => {
        setUsuarios(response);
      })
      .catch((error: Error) => {
        openErrorMessage(error.message);
      })
  };

  useEffect(() => {
    buscarUsuarios();
  }, []);

  return (
    <Container>
      <BackdropComponent open={loading} />
      <SnackbarComponent open={errorMessage} message={messageText} onClose={closeErrorMessage} severity="error" />
      <SnackbarComponent open={successMessage} message={messageText} onClose={closeSuccessMessage} severity="success" />
      <CadastroUsuarios onSubmit={handleSubmit} openErrorMessage={openErrorMessage} openLoading={openLoading} usuario={usuarioEmEdicao}/>

      {usuarios.length > 0 &&
        <TabelaUsuarios usuarios={usuarios} onEdit={handleEditar} onDelete={handleExcluir} />
      }

    <Dialog open={openConfirmDialog} onClose={cancelarExclusao}>
        <DialogTitle>Confirmar exclusão de usuário</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Deseja excluir o usuário <strong>{usuarioEmExclusao?.nome}</strong>?
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

export default UsuariosPage;