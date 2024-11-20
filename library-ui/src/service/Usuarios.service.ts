import axios from 'axios';

import { LivroModel } from '../model/Livro.model';
import { CadastroUsuarioModel } from '../model/CadastroUsuario.model';
import { UsuarioModel } from '../model/Usuario.model';

const baseUrl = 'http://localhost:8080';

export async function cadastrarUsuario(cadastroUsuario: CadastroUsuarioModel): Promise<UsuarioModel> {

  try {
    const response = await axios.post<UsuarioModel>(`${baseUrl}/usuarios`, cadastroUsuario);
    return response.data;

  } catch (error) {
    throw new Error(`Erro ao cadastrar usuário: ${error}`);
  }

}

export async function atualizarUsuario(cadastroUsuario: CadastroUsuarioModel): Promise<void> {

  try {
    await axios.put<CadastroUsuarioModel>(`${baseUrl}/usuarios/${cadastroUsuario.id}`, cadastroUsuario);

  } catch (error) {
    throw new Error(`Erro ao atualizar usuário: ${error}`);

  }

}

export async function excluirUsuario(codigoUsuario: number): Promise<void> {

  try {
    await axios.delete(`${baseUrl}/usuario/${codigoUsuario}`);

  } catch (error) {
    throw new Error(`Erro ao excluir usuário: ${error}`);

  }

}

export async function listarUsuarios(): Promise<UsuarioModel[]> {
  
  try {
    const response = await axios.get<UsuarioModel[]>(`${baseUrl}/usuarios`);
    return response.data;

  } catch (error) {
    throw new Error(`Erro ao listar usuários: ${error}`);

  }

}
