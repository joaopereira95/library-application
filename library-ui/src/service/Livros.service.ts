import axios from 'axios';

import { LivroModel } from '../model/Livro.model';
import { CadastroLivroModel } from '../model/CadastroLivro.model';
import { LivroOnlineModel } from '../model/LivroOnline.model';

const baseUrl = 'http://localhost:8080';

export async function cadastrarLivro(cadastroLivro: CadastroLivroModel): Promise<LivroModel> {

  try {
    const response = await axios.post<LivroModel>(`${baseUrl}/livros`, cadastroLivro);
    return response.data;

  } catch (error) {
    throw new Error(`Erro ao cadastrar livro: ${error}`);
  }

}

export async function atualizarLivro(cadastroLivro: CadastroLivroModel): Promise<void> {

  try {
    await axios.put<LivroModel>(`${baseUrl}/livros/${cadastroLivro.id}`, cadastroLivro);

  } catch (error) {
    throw new Error(`Erro ao atualizar livro: ${error}`);

  }

}

export async function excluirLivro(codigoLivro: number): Promise<void> {

  try {
    await axios.delete(`${baseUrl}/livros/${codigoLivro}`);

  } catch (error) {
    throw new Error(`Erro ao excluir livro: ${error}`);

  }

}

export async function listarLivros(): Promise<LivroModel[]> {
  
  try {
    const response = await axios.get<LivroModel[]>(`${baseUrl}/livros`);
    return response.data;

  } catch (error) {
    throw new Error(`Erro ao listar livros: ${error}`);

  }

}

export async function consultarLivrosOnlinePorTitulo(titulo: string): Promise<LivroOnlineModel[]> {
  
  try {
    const response = await axios.get<LivroOnlineModel[]>(`${baseUrl}/livros/online?titulo=${titulo}`);
    return response.data;

  } catch (error) {
    throw new Error(`Erro ao consultar livros online: ${error}`);

  }

}

export async function importarLivroOnline(id: string): Promise<LivroOnlineModel> {
  
  try {
    const response = await axios.post(`${baseUrl}/livros/online/importacao`, {id});
    return response.data;

  } catch (error) {
    throw new Error(`Erro ao importar livro: ${error}`);

  }

}

export async function listarRecomendacoesLivros(codigoUsuario: number): Promise<LivroModel[]> {
  
  try {
    const response = await axios.get<LivroModel[]>(`${baseUrl}/livros/recomendacoes/${codigoUsuario}`);
    return response.data;

  } catch (error) {
    throw new Error(`Erro ao listar recomendações de livros: ${error}`);

  }

}
