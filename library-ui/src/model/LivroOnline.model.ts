import { Categoria } from "./Categoria.model";

export interface LivroOnlineModel {
    id: string;
    titulo: string;
    autor: string;
    isbn: string;
    dataPublicacao: Date;
    categoria: Categoria;
}