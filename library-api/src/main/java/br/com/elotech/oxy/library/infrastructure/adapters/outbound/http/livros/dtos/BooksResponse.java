package br.com.elotech.oxy.library.infrastructure.adapters.outbound.http.livros.dtos;

import java.util.List;

public class BooksResponse {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BooksResponse{" +
                "items=" + items +
                '}';
    }
}