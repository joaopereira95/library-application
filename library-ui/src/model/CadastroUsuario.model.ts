export interface CadastroUsuarioModel {
    id: number;
    nome: string;
    email: string;
    telefone: string;
    dataCadastro: Date | null;
}