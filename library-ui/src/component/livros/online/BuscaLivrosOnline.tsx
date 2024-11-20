import React, { useState } from 'react';
import { TextField, Button, Grid, Paper, FormControl, InputLabel, Select, MenuItem, Typography } from '@mui/material';

interface CadastroLivroProps {
  onSearch: (titulo: string, limparFormulario: () => void) => void;
  openErrorMessage: (message: string) => void;
  openLoading: () => void;
}

const BuscaLivrosOnline: React.FC<CadastroLivroProps> = ({ onSearch, openErrorMessage, openLoading }) => {
  const [titulo, setTitulo] = useState<string>('');

  const handleSearch = (e: any) => {
    e.preventDefault();

    if (!validarFormulario()) {
      return;
    }

    openLoading();
    
    onSearch(titulo, limparFormulario);
  };

  const handleChange = (e: any) => {
    const { value } = e.target;
    setTitulo(value);
  };

  const validarFormulario = () => {
    if (!titulo) {
      openErrorMessage('Preencha o título do livro a ser pesquisado');
      return false;
    }
    
    return true;
  };

  const limparFormulario = () => {
    setTitulo('');
  }

  return (
    <>
      <Typography variant="h4" align="center" gutterBottom>Consulta online de livros no Google Books</Typography>
      <Paper variant="outlined" style={{ padding: '20px', marginBottom: '20px' }}>
        <form onSubmit={handleSearch}>
          <Grid container spacing={2}>
            <Grid item xs={12} md={12}>
              <TextField
                fullWidth
                label="Título"
                name="titulo"
                value={titulo}
                onChange={handleChange}
              />
            </Grid>
            
          </Grid>
          <Button type="submit" variant="contained" color="primary" style={{ marginTop: '20px' }}> Consultar livros</Button>
        </form>
      </Paper>
    </>
  );
};

export default BuscaLivrosOnline;