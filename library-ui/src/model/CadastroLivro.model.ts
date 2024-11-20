export interface CadastroLivroModel {
    id: number;
    titulo: string;
    autor: string;
    isbn: string;
    dataPublicacao: Date | null;
    codigoCategoria: number;
}