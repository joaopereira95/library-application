import axios from 'axios';

import { CadastroEmprestimoModel } from '../model/CadastroEmprestimo.model';
import { EmprestimoModel } from '../model/Emprestimo.model';
import apiClient from './ApiClient';

const baseUrl = 'http://localhost:8080';

export async function cadastrarEmprestimo(cadastroEmprestimo: CadastroEmprestimoModel): Promise<EmprestimoModel> {
    console.log('entrou no cadastrar emprestimo');

    const response = await apiClient.post<EmprestimoModel>(`${baseUrl}/emprestimos`, cadastroEmprestimo);

    return response.data;


}

export async function realizarDevolucao(codigoEmprestimo: number): Promise<void> {

  try {
    await axios.put(`${baseUrl}/emprestimos/devolucao/${codigoEmprestimo}`);

  } catch (error) {
    throw new Error(`Erro ao realizar devolução: ${error}`);

  }

}

export async function listarEmprestimos(): Promise<EmprestimoModel[]> {
  
  try {
    const response = await axios.get<EmprestimoModel[]>(`${baseUrl}/emprestimos`);
    return response.data;

  } catch (error) {
    throw new Error(`Erro ao listar empréstimos: ${error}`);

  }

}


