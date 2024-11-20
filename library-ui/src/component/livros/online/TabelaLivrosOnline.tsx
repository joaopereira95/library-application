import React, { useState } from 'react';

import { Typography, Paper, IconButton } from '@mui/material';
import { DataGrid, GridAddIcon, GridColDef, GridPaginationModel } from '@mui/x-data-grid';

import { LivroOnlineModel } from '../../../model/LivroOnline.model';

interface TabelaLivrosOnlineProps {
  livros: LivroOnlineModel[];
  onImport: (livro: LivroOnlineModel) => void;
}

const TabelasLivrosOnline: React.FC<TabelaLivrosOnlineProps> = ({ livros, onImport }) => {
  const [paginationModel] = useState<GridPaginationModel>({ page: 0, pageSize: 20 });

  const columns: GridColDef[] = [
    { field: 'id', headerName: 'Código', flex: 1 },
    { field: 'titulo', headerName: 'Título', flex: 1 },
    { field: 'autor', headerName: 'Autor', flex: 1 },
    { field: 'isbn', headerName: 'ISBN', flex: 1 },
    {
      field: 'dataPublicacao',
      headerName: 'Data de publicação',
      flex: 1,
      valueFormatter: (params) =>
        new Date((params.value as string).concat('T00:00:00')).toLocaleDateString('pt-BR'),    
    },
    {
      field: 'categoria',
      headerName: 'Categoria',
      flex: 1,
      valueGetter: (params) => params.row.categoria.nome,
    },
    {
      field: 'action',
      headerName: 'Ações',
      flex: 1,
      sortable: false,
      filterable: false,
      renderCell: (params) => (
        <div style={{ display: 'flex', gap: '8px' }}>
          <IconButton
            color="primary"
            title='Importar livro'
            onClick={() => onImport(params.row as LivroOnlineModel)}
          >
            <GridAddIcon />
          </IconButton>
          
        </div>
      ),
    },
  ];

  return (
    <>
      <Typography variant="h5" align="center" gutterBottom>
        Livros encontrados
      </Typography>
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
    </>
  );
};

export default TabelasLivrosOnline;
