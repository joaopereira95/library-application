import { Categoria } from '../model/Categoria.model';
import apiClient from './ApiClient';

const baseUrl = 'http://localhost:8080';

export async function listarCategorias(): Promise<Categoria[]> {
    const response = await apiClient.get<Categoria[]>(`${baseUrl}/categorias`);
    return response.data;
  
}
