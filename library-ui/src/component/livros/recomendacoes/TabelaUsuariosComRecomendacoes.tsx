import React, { useState } from 'react';

import { Typography, Paper, IconButton } from '@mui/material';
import { DataGrid, GridColDef, GridMenuIcon, GridPaginationModel } from '@mui/x-data-grid';

import { UsuarioModel } from '../../../model/Usuario.model';

interface TabelaUsuariosComRecomendacoesProps {
  usuarios: UsuarioModel[];
  onRecommend: (usuario: UsuarioModel) => void;
}

const TabelaUsuariosComRecomendacoes: React.FC<TabelaUsuariosComRecomendacoesProps> = ({ usuarios, onRecommend }) => {
  const [paginationModel] = useState<GridPaginationModel>({ page: 0, pageSize: 20 });

  const columns: GridColDef[] = [
    { field: 'id', headerName: 'Código', flex: 1 },
    { field: 'nome', headerName: 'Nome', flex: 1 },
   
    {
      field: 'action',
      headerName: 'Ações',
      flex: 1,
      sortable: false,
      filterable: false,
      renderCell: (params) => (
        <div style={{ display: 'flex', gap: '8px' }}>
         
          <IconButton
            color="default"
            title='Recomendações de livros'
            onClick={() => onRecommend(params.row as UsuarioModel)}
          >
            <GridMenuIcon />
          </IconButton>
        </div>
      ),
    },
  ];

  return (
    <>
      <Typography variant="h5" align="center" gutterBottom>
        Recomendações de livros por usuário
      </Typography>
      <Paper variant="outlined" style={{ width: '100%', marginBottom: '20px' }}>
        <DataGrid
          rows={usuarios}
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

export default TabelaUsuariosComRecomendacoes;
