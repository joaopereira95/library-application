import React, { useState, useEffect } from 'react';
import { TextField, Button, Grid, Paper, FormControl, InputLabel, Select, MenuItem, Typography } from '@mui/material';

import DatePicker from 'react-datepicker';
import { ptBR } from 'date-fns/locale';
import 'react-datepicker/dist/react-datepicker.css';
import "../../../customDatePickerWidth.css";

import { CadastroLivroModel } from '../../../model/CadastroLivro.model';
import { Categoria } from '../../../model/Categoria.model';

import { listarCategorias } from '../../../service/Categorias.service';

interface CadastroLivroProps {
  onSubmit: (evento: CadastroLivroModel, limparFormulario: () => void) => void;
  openErrorMessage: (message: string) => void;
  openLoading: () => void;
  livro?: CadastroLivroModel | null;
}
const CadastroLivros: React.FC<CadastroLivroProps> = ({ onSubmit, openErrorMessage, openLoading, livro: livroAlteracao }) => {
  const [livro, setLivro] = useState<CadastroLivroModel>({
    id: 0,
    titulo: '',
    autor: '',
    isbn: '',
    dataPublicacao: null,
    codigoCategoria: 0
  });
  const [categorias, setCategorias] = useState<Categoria[]>([]);

  useEffect(() => {
    const buscarCategorias = async () => {
      try {
        const response = await listarCategorias();
        setCategorias(response);
      } catch (error) {
        openErrorMessage('Erro ao buscar as categorias.');
        console.error('Erro ao buscar as categorias:', error);
      }
    };

    buscarCategorias();

    if (livroAlteracao) {
      setLivro(livroAlteracao);
    }

  }, [livroAlteracao]);

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setLivro({ ...livro, [name]: value });
  };

  const handleDateChange = (date: Date | null, field: string) => {
    setLivro({ ...livro, [field]: date });
  };

  const handleCategoriaChange = (event: any) => {
    setLivro({ ...livro, codigoCategoria: event.target.value as number });
  };

  const handleSubmit = (e: any) => {
    e.preventDefault();

    if (!validarFormulario()) {
      return;
    }

    openLoading();
    
    onSubmit(livro, limparFormulario);
  };

  const validarFormulario = () => {
    if (!livro.titulo) {
      openErrorMessage('Preencha o título do livro');
      return false;
    }

    if (!livro.autor) {
      openErrorMessage('Preencha o autor do livro');
      return false;
    }

    if (!livro.isbn) {
      openErrorMessage('Preencha o ISBN do livro');
      return false;
    }

    if (!livro.dataPublicacao) {
      openErrorMessage('Preencha a data de publicação do livro');
      return false;
    }
    
    return true;
  };

  const limparFormulario = () => {
    setLivro({
      id: 0,
      titulo: '',
      autor: '',
      isbn: '',
      dataPublicacao: null,
      codigoCategoria: 0
    });
  }

  return (
    <>
      <Typography variant="h4" align="center" gutterBottom>Cadastro de Livros</Typography>
      <Paper variant="outlined" style={{ padding: '20px', marginBottom: '20px' }}>
        <form onSubmit={handleSubmit}>
          <Grid container spacing={2}>
            <Grid item xs={12} md={12}>
              <TextField
                fullWidth
                label="Título"
                name="titulo"
                value={livro.titulo}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} md={12}>
              <TextField
                fullWidth
                label="Autor"
                name="autor"
                value={livro.autor}
                onChange={handleChange}
              />
            </Grid>

            <Grid item xs={12} md={12}>
              <TextField
                fullWidth
                label="ISBN"
                name="isbn"
                value={livro.isbn}
                onChange={handleChange}
              />
            </Grid>
            <Grid item xs={12} md={12}>
              <FormControl fullWidth>
                <InputLabel>Categoria</InputLabel>
                <Select
                  value={livro.codigoCategoria}
                  onChange={handleCategoriaChange}
                >
                  {categorias.map((categoria) => (
                    <MenuItem key={categoria.id} value={categoria.id}>
                      {categoria.nome}
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
                    selected={livro.dataPublicacao}
                    showYearDropdown
                    showMonthDropdown
                    placeholderText='DD/MM/AAAA'
                    customInput={<TextField fullWidth label='Data de publicação' autoComplete='off' />}
                    dateFormat="dd/MM/yyyy"
                    onChange={(date) => handleDateChange(date, 'dataPublicacao')}
                />
              </div>
            </Grid>
            
          </Grid>
          <Button type="submit" variant="contained" color="primary" style={{ marginTop: '20px' }}> { livro.id === 0 ? 'Cadastrar livro' : 'Atualizar livro'} </Button>
        </form>
      </Paper>
    </>
  );
};

export default CadastroLivros;