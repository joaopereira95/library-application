import React, { useState, useEffect } from 'react';
import { TextField, Button, Grid, Paper, FormControl, InputLabel, Select, MenuItem, Typography } from '@mui/material';
import DatePicker from 'react-datepicker';

import { ptBR } from 'date-fns/locale';
import 'react-datepicker/dist/react-datepicker.css';
import "../../customDatePickerWidth.css";

import { CadastroEmprestimoModel } from '../../model/CadastroEmprestimo.model';
import { EmprestimoModel } from '../../model/Emprestimo.model';
import { UsuarioModel } from '../../model/Usuario.model';
import { LivroModel } from '../../model/Livro.model';

import { listarLivros } from '../../service/Livros.service';
import { listarUsuarios } from '../../service/Usuarios.service';

interface GerenciamentoEmprestimosProps {
  onSubmit: (evento: CadastroEmprestimoModel, limparFormulario: () => void) => void;
  openErrorMessage: (message: string) => void;
  openLoading: () => void;
  emprestimo?: EmprestimoModel | null;
}
const GerenciamentoEmprestimos: React.FC<GerenciamentoEmprestimosProps> = ({ onSubmit, openErrorMessage, openLoading, emprestimo: emprestimoAlteracao }) => {
  const [emprestimo, setEmprestimo] = useState<CadastroEmprestimoModel>({
    id: 0,
    codigoLivro: 0,
    codigoUsuario: 0,
    dataEmprestimo: null
  });
  const [usuarios, setUsuarios] = useState<UsuarioModel[]>([]);
  const [livros, setLivros] = useState<LivroModel[]>([]);

  useEffect(() => {
    const listarUsuariosCadastrados = async () => {
      try {
        const response = await listarUsuarios();
        setUsuarios(response);
      } catch (error) {
        openErrorMessage('Erro ao buscar os usuários.');
        console.error('Erro ao buscar os usuários:', error);
      }
    };

    const listarLivrosCadastrados = async () => {
      try {
        const response = await listarLivros();
        setLivros(response);
      } catch (error) {
        openErrorMessage('Erro ao buscar os livros.');
        console.error('Erro ao buscar os livros:', error);
      }
    };

    listarUsuariosCadastrados();
    listarLivrosCadastrados();

  }, []);

  const handleDateChange = (date: Date | null, field: string) => {
    setEmprestimo({ ...emprestimo, [field]: date });
  };

  const handleUsuarioChange = (event: any) => {
    setEmprestimo({ ...emprestimo, codigoUsuario: event.target.value as number });
  };

  const handleLivroChange = (event: any) => {
    setEmprestimo({ ...emprestimo, codigoLivro: event.target.value as number });
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();

    if (!validarFormulario()) {
      return;
    }

    openLoading();
    
    onSubmit(emprestimo, limparFormulario);
  };

  const validarFormulario = () => {
    if (!emprestimo.codigoLivro) {
      openErrorMessage('Preencha o livro');
      return false;
    }

    if (!emprestimo.codigoUsuario) {
      openErrorMessage('Preencha o usuário');
      return false;
    }


    if (!emprestimo.dataEmprestimo) {
      openErrorMessage('Preencha a data de empréstimo do livro');
      return false;
    }
    
    return true;
  };

  const limparFormulario = () => {
    setEmprestimo({
      id: 0,
      codigoLivro: 0,
      codigoUsuario: 0,
      dataEmprestimo: null
    });
  }

  return (
    <>
      <Typography variant="h4" align="center" gutterBottom>Gerenciamento de Empréstimos</Typography>
      <Paper variant="outlined" style={{ padding: '20px', marginBottom: '20px' }}>
        <form onSubmit={handleSubmit}>
        <Grid container spacing={2}>
            <Grid item xs={12} md={12}>
              <FormControl fullWidth>
                <InputLabel>Livro</InputLabel>
                <Select
                  value={emprestimo.codigoLivro}
                  onChange={handleLivroChange}
                >
                  {livros.map((livro) => (
                    <MenuItem key={livro.id} value={livro.id}>
                      {livro.titulo}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={12} md={12}>
              <FormControl fullWidth>
                <InputLabel>Usuário</InputLabel>
                <Select
                  value={emprestimo.codigoUsuario}
                  onChange={handleUsuarioChange}
                >
                  {usuarios.map((usuario) => (
                    <MenuItem key={usuario.id} value={usuario.id}>
                      {usuario.nome}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Grid>
            <Grid item xs={6} md={6}>
              <div className="customDatePickerWidth">
                <DatePicker
                    autoComplete='off'
                    locale={ptBR}
                    selected={emprestimo.dataEmprestimo}
                    showYearDropdown
                    showMonthDropdown
                    placeholderText='DD/MM/AAAA'
                    customInput={<TextField fullWidth label='Data de empréstimo' autoComplete='off' />}
                    dateFormat="dd/MM/yyyy"
                    onChange={(date) => handleDateChange(date, 'dataEmprestimo')}
                />
              </div>
            </Grid>
          </Grid>
          <Button type="submit" variant="contained" color="primary" style={{ marginTop: '20px' }}> Cadastrar empréstimo </Button>
        </form>
      </Paper>
    </>
  );
};

export default GerenciamentoEmprestimos;