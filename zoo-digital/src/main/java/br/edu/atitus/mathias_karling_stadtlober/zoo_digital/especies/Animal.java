package br.edu.atitus.mathias_karling_stadtlober.zoo_digital.especies;

import org.apache.commons.lang3.StringUtils;

public abstract class Animal {
    private String nome;
    private int idade;
    private static int contador = 0;

    public Animal(String nome, int idade) {
        setNome(nome);
        setIdade(idade);
        contador++;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (StringUtils.isBlank(nome)) { this.nome = "Sem nome"; return; }
        this.nome = StringUtils.capitalize(nome.trim().toLowerCase());
    }
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = Math.max(0, idade); }
    public abstract void emitirSom();
    public void comer() { System.out.println(getNome() + " está comendo."); }
    public final void comer(String alimento) {
        String item = StringUtils.isBlank(alimento) ? "alimento genérico" : alimento.trim().toLowerCase();
        System.out.println(getNome() + " está comendo " + item + ".");
    }
    public static int getContador() { return contador; }
    public String getEspecie() { return getClass().getSimpleName(); }
    public String getGrupo() { return getClass().getSuperclass().getSimpleName(); }
    public String getDescricao() {
        return "Nome: " + getNome() + " | Idade: " + getIdade() + " | Espécie: " + getEspecie() + " | Grupo: " + getGrupo();
    }
}
