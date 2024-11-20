import { LivroModel } from "./Livro.model";
import { UsuarioModel } from "./Usuario.model";

export interface EmprestimoModel {
    id: number;
    usuario: UsuarioModel;
    livro: LivroModel;
    dataEmprestimo: Date;
    status: string;
}