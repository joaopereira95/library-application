import { Categoria } from "./Categoria.model";

export interface LivroModel {
    id: number;
    titulo: string;
    autor: string;
    isbn: string;
    dataPublicacao: Date;
    categoria: Categoria;
}