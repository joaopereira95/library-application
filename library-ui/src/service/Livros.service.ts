import { LivroModel } from '../model/Livro.model';
import { CadastroLivroModel } from '../model/CadastroLivro.model';
import { LivroOnlineModel } from '../model/LivroOnline.model';
import apiClient from './ApiClient';

const baseUrl = 'http://localhost:8080';

export async function cadastrarLivro(cadastroLivro: CadastroLivroModel): Promise<LivroModel> {
    const response = await apiClient.post<LivroModel>(`${baseUrl}/livros`, cadastroLivro);
    return response.data;
}

export async function atualizarLivro(cadastroLivro: CadastroLivroModel): Promise<void> {
    await apiClient.put<LivroModel>(`${baseUrl}/livros/${cadastroLivro.id}`, cadastroLivro);
}

export async function excluirLivro(codigoLivro: number): Promise<void> {
    await apiClient.delete(`${baseUrl}/livros/${codigoLivro}`);
}

export async function listarLivros(): Promise<LivroModel[]> {
    const response = await apiClient.get<LivroModel[]>(`${baseUrl}/livros`);
    return response.data;
}

export async function consultarLivrosOnlinePorTitulo(titulo: string): Promise<LivroOnlineModel[]> {  
    const response = await apiClient.get<LivroOnlineModel[]>(`${baseUrl}/livros/online?titulo=${titulo}`);
    return response.data;
}

export async function importarLivroOnline(id: string): Promise<LivroOnlineModel> {  
    const response = await apiClient.post(`${baseUrl}/livros/online/importacao`, {id});
    return response.data;
}

export async function listarRecomendacoesLivros(codigoUsuario: number): Promise<LivroModel[]> {  
    const response = await apiClient.get<LivroModel[]>(`${baseUrl}/livros/recomendacoes/${codigoUsuario}`);
    return response.data;
}
