import { CadastroEmprestimoModel } from '../model/CadastroEmprestimo.model';
import { EmprestimoModel } from '../model/Emprestimo.model';
import apiClient from './ApiClient';

const baseUrl = 'http://localhost:8080';

export async function cadastrarEmprestimo(cadastroEmprestimo: CadastroEmprestimoModel): Promise<EmprestimoModel> {
    const response = await apiClient.post<EmprestimoModel>(`${baseUrl}/emprestimos`, cadastroEmprestimo);
    return response.data;
}

export async function realizarDevolucao(codigoEmprestimo: number): Promise<void> {
    await apiClient.put(`${baseUrl}/emprestimos/devolucao/${codigoEmprestimo}`);
}

export async function listarEmprestimos(): Promise<EmprestimoModel[]> {  
    const response = await apiClient.get<EmprestimoModel[]>(`${baseUrl}/emprestimos`);
    return response.data;
}


