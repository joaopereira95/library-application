import { CadastroUsuarioModel } from '../model/CadastroUsuario.model';
import { UsuarioModel } from '../model/Usuario.model';
import apiClient from './ApiClient';

const baseUrl = 'http://localhost:8080';

export async function cadastrarUsuario(cadastroUsuario: CadastroUsuarioModel): Promise<UsuarioModel> {
    const response = await apiClient.post<UsuarioModel>(`${baseUrl}/usuarios`, cadastroUsuario);
    return response.data;
}

export async function atualizarUsuario(cadastroUsuario: CadastroUsuarioModel): Promise<void> {
    await apiClient.put<CadastroUsuarioModel>(`${baseUrl}/usuarios/${cadastroUsuario.id}`, cadastroUsuario);
}

export async function excluirUsuario(codigoUsuario: number): Promise<void> {
    await apiClient.delete(`${baseUrl}/usuarios/${codigoUsuario}`);
}

export async function listarUsuarios(): Promise<UsuarioModel[]> {
    const response = await apiClient.get<UsuarioModel[]>(`${baseUrl}/usuarios`);
    return response.data;
}
