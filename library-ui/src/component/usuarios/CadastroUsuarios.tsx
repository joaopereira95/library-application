import React, { useState, useEffect } from 'react';
import { TextField, Button, Grid, Paper, Typography } from '@mui/material';

import { CadastroUsuarioModel } from '../../model/CadastroUsuario.model';

interface CadastroUsuarioProps {
  onSubmit: (evento: CadastroUsuarioModel, limparFormulario: () => void) => void;
  openErrorMessage: (message: string) => void;
  openLoading: () => void;
  usuario?: CadastroUsuarioModel | null;
}
const CadastroUsuarios: React.FC<CadastroUsuarioProps> = ({ onSubmit, openErrorMessage, openLoading, usuario: usuarioAlteracao }) => {
  const [usuario, setUsuario] = useState<CadastroUsuarioModel>({
    id: 0,
    nome: '',
    email: '',
    telefone: '',
    dataCadastro: null

  });

  useEffect(() => {  

    if (usuarioAlteracao) {
      setUsuario(usuarioAlteracao);
    }

  }, [usuarioAlteracao]);

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setUsuario({ ...usuario, [name]: value });
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();

    if (!validarFormulario()) {
      return;
    }

    openLoading();
    
    onSubmit(usuario, limparFormulario);
  };

  const validarFormulario = () => {
    if (!usuario.nome) {
      openErrorMessage('Preencha o nome do usuário');
      return false;
    }

    if (!usuario.email) {
      openErrorMessage('Preencha o e-mail do usuário');
      return false;
    }

    if (!usuario.telefone) {
      openErrorMessage('Preencha o telefone do usuário');
      return false;
    }
    
    return true;
  };

  const limparFormulario = () => {
    setUsuario({
      id: 0,
      nome: '',
      email: '',
      telefone: '',
      dataCadastro: null
    });
  }

  return (
    <>
      <Typography variant="h4" align="center" gutterBottom>Cadastro de Usuários</Typography>
      <Paper variant="outlined" style={{ padding: '20px', marginBottom: '20px' }}>
        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={12} md={12}>
              <TextField
                fullWidth
                label="Nome"
                name="nome"
                value={usuario.nome}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} md={12}>
              <TextField
                fullWidth
                label="E-mail"
                name="email"
                value={usuario.email}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} md={12}>
              <TextField
                fullWidth
                label="Telefone"
                name="telefone"
                value={usuario.telefone}
                onChange={handleChange}
              />
            </Grid>
            
            
          </Grid>
          <Button type="submit" variant="contained" color="primary" style={{ marginTop: '20px' }}> { usuario.id === 0 ? 'Cadastrar usuário' : 'Atualizar usuário'} </Button>
        </form>
      </Paper>
    </>
  );
};

export default CadastroUsuarios;