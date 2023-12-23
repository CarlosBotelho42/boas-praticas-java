package br.com.alura.domain;

public class Pet {

    private Long id;
    private String tipo;
    private String nome;
    private String raca;
    private Integer idade;
    private String cor;
    private Float peso;

    public Pet(String tipo, String nome, String raca, Integer idade, String cor, Float peso) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;
    }

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public Float getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return """
                     "id":%s,"tipo":"%s","nome":"%s","raca":"%s,"idade":"%s,"cor":"%s,"peso":"%s"
                     """.formatted(this.id, this.tipo, this.nome, this.raca, this.idade, this.cor, this.peso);
    }

}
