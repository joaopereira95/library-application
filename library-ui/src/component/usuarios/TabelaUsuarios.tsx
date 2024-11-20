import React, { useState } from 'react';

import { Typography, Paper, IconButton } from '@mui/material';
import { DataGrid, GridColDef, GridPaginationModel } from '@mui/x-data-grid';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

import { UsuarioModel } from '../../model/Usuario.model';

interface TabelaUsuariosProps {
  usuarios: UsuarioModel[];
  onEdit: (usuario: UsuarioModel) => void;
  onDelete: (usuario: UsuarioModel) => void;
}

const TabelaUsuarios: React.FC<TabelaUsuariosProps> = ({ usuarios, onEdit, onDelete }) => {
  const [paginationModel] = useState<GridPaginationModel>({ page: 0, pageSize: 20 });

  const columns: GridColDef[] = [
    { field: 'id', headerName: 'Código', flex: 1 },
    { field: 'nome', headerName: 'Nome', flex: 1 },
    { field: 'email', headerName: 'E-mail', flex: 1 },
    { field: 'telefone', headerName: 'Telefone', flex: 1 },
    {
      field: 'dataCadastro',
      headerName: 'Data de cadastro',
      flex: 1,
      valueFormatter: (params) =>
        new Date((params.value as string).concat('T00:00:00')).toLocaleDateString('pt-BR'),
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
            title='Atualizar usuário'
            onClick={() => onEdit(params.row as UsuarioModel)}
          >
            <EditIcon />
          </IconButton>
          <IconButton
            color="error"
            title='Excluir usuário'
            onClick={() => onDelete(params.row as UsuarioModel)}
          >
            <DeleteIcon />
          </IconButton>
          
        </div>
      ),
    },
  ];

  return (
    <>
      <Typography variant="h5" align="center" gutterBottom>
        Usuários cadastrados
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

export default TabelaUsuarios;
