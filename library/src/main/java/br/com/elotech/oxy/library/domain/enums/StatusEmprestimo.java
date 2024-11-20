package br.com.elotech.oxy.library.domain.enums;

public enum StatusEmprestimo {

    EMPRESTADO(0), DEVOLVIDO(1);

    private int codigo;

    StatusEmprestimo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }


}
