import axios, { AxiosError } from 'axios';
import { Categoria } from '../model/Categoria.model';

const baseUrl = 'http://localhost:8080';

export async function listarCategorias(): Promise<Categoria[]> {
  try {
    const response = await axios.get<Categoria[]>(`${baseUrl}/categorias`);
    return response.data;
  } catch (error: any) {  
    throw new Error(`Erro ao listar categorias: ${error}`);
  }
}
