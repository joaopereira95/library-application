import React, { useState } from 'react';

import { Typography, Paper, Button, IconButton } from '@mui/material';
import { DataGrid, GridCheckCircleIcon, GridColDef, GridPaginationModel } from '@mui/x-data-grid';

import { EmprestimoModel } from '../../model/Emprestimo.model';

interface TabelaEmprestimosProps {
  emprestimos: EmprestimoModel[];
  onReturn: (emprestimo: EmprestimoModel) => void;
}

const TabelaEmprestimos: React.FC<TabelaEmprestimosProps> = ({ emprestimos, onReturn }) => {
  const [paginationModel] = useState<GridPaginationModel>({ page: 0, pageSize: 20 });

  const columns: GridColDef[] = [
    { field: 'id', headerName: 'Código', flex: 1 },
    {
      field: 'livro',
      headerName: 'Livro',
      flex: 1,
      valueGetter: (params) => params.row.livro.titulo,
    },
    {
      field: 'usuario',
      headerName: 'Usuário',
      flex: 1,
      valueGetter: (params) => params.row.usuario.nome,
    },
    {
      field: 'dataEmprestimo',
      headerName: 'Data de empréstimo',
      flex: 1,
      valueFormatter: (params) =>
        new Date((params.value as string)).toLocaleDateString('pt-BR'),    
    },
    {
      field: 'dataDevolucao',
      headerName: 'Data de devolução',
      flex: 1,
      valueFormatter: (params) => {
        if (params.value) {
          return new Date((params.value as string)).toLocaleDateString('pt-BR');
        }
      }        
            
    },
    { field: 'status', headerName: 'Status', flex: 1 },
    {
      field: 'action',
      headerName: 'Ações',
      flex: 1,
      sortable: false,
      filterable: false,
      renderCell: (params) => (
        <div style={{ display: 'flex', gap: '8px' }}>
          <IconButton
            disabled={params.row.status !== 'EMPRESTADO'}
            color="success"
            title='Devolver livro'
            onClick={() => onReturn(params.row as EmprestimoModel)}
          >
            <GridCheckCircleIcon />
          </IconButton>
         
        </div>
      ),
    },
  ];

  return (
    <>
      <Typography variant="h5" align="center" gutterBottom>
        Empréstimos cadastrados
      </Typography>
      <Paper variant="outlined" style={{ width: '100%', marginBottom: '20px' }}>
        <DataGrid
          rows={emprestimos}
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

export default TabelaEmprestimos;
