import React, { useState } from 'react';

import { Typography, Paper } from '@mui/material';
import { DataGrid, GridColDef, GridPaginationModel } from '@mui/x-data-grid';

import { LivroModel } from '../../../model/Livro.model';

interface TabelaRecomendacoesProps {
  livros: LivroModel[]
}

const TabelaLivrosRecomendados: React.FC<TabelaRecomendacoesProps> = ({ livros}) => {
  const [paginationModel] = useState<GridPaginationModel>({ page: 0, pageSize: 10 });

  const columns: GridColDef[] = [
    { field: 'titulo', headerName: 'Título', flex: 1 },
    { field: 'autor', headerName: 'Autor', flex: 1 },
    
    {
      field: 'categoria',
      headerName: 'Categoria',
      flex: 1,
      valueGetter: (params) => params.row.categoria.nome,
    }
    
  ];

  return (
    <>
      <Typography variant="h5" align="center" gutterBottom>
        Livros recomendados
      </Typography>
      {livros && livros.length > 0 ? (
        <Paper variant="outlined" style={{ width: '100%', marginBottom: '20px' }}>
          <DataGrid
            rows={livros}
            density="comfortable"
            columns={columns}
            initialState={{
              pagination: {
                paginationModel,
              },
            }}
            rowSelection={false}
            pageSizeOptions={[10, 20, 50, 100]}
            localeText={{
              columnMenuLabel: 'Opções',
              columnMenuUnsort: 'Não classificado',
              columnMenuSortAsc: 'Classificar por ordem crescente',
              columnMenuSortDesc: 'Classificar por ordem decrescente',
              columnMenuFilter: 'Filtro',
              columnMenuHideColumn: 'Ocultar',
              columnMenuShowColumns: 'Mostrar colunas',
              columnMenuManageColumns: 'Selecionar colunas',
              columnHeaderSortIconLabel: 'Ordenar',
              MuiTablePagination: {
                labelRowsPerPage: 'Linhas por página',
                labelDisplayedRows: ({ from, to, count }) =>
                  `${from} - ${to} de ${count}`,
              },
            }}
          />
        </Paper>
      ) : (
        <div style={{ textAlign: 'center', marginTop: '20px' }}>
          <Typography variant="body1">Sem recomendações de livros para este usuário.</Typography>
        </div>
      )}
    </>
  );
};

export default TabelaLivrosRecomendados;  
