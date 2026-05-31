package br.edu.atitus.mathias_karling_stadtlober.zoo_digital.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import br.edu.atitus.mathias_karling_stadtlober.zoo_digital.animais.*;
import br.edu.atitus.mathias_karling_stadtlober.zoo_digital.comportamentos.*;
import br.edu.atitus.mathias_karling_stadtlober.zoo_digital.especies.Animal;

public class ZooDigitalApp {
    private static final Scanner SC = new Scanner(System.in);
    private static final List<Animal> animais = new ArrayList<>();

    public static void main(String[] args) {
        carregarAnimaisIniciais();
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            System.out.println();
            switch (opcao) {
                case 1 -> cadastrarAnimal();
                case 2 -> listarTodosAnimais();
                case 3 -> listarCorredores();
                case 4 -> listarNadadores();
                case 5 -> listarVoadores();
                case 6 -> listarPredadores();
                case 7 -> exibirTotalAnimais();
                case 0 -> System.out.println("Encerrando o Zoo Digital...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);
        SC.close();
    }

    private static void exibirMenu() {
        System.out.println("===== ZOO DIGITAL =====");
        System.out.println("1 - Cadastrar animal");
        System.out.println("2 - Listar todos os animais");
        System.out.println("3 - Listar animais corredores");
        System.out.println("4 - Listar animais nadadores");
        System.out.println("5 - Listar animais voadores");
        System.out.println("6 - Listar animais predadores");
        System.out.println("7 - Exibir total de animais");
        System.out.println("0 - Sair");
    }

    private static void cadastrarAnimal() {
        for (int i = 1; i <= 17; i++) {
            System.out.println(i + " - " + nomeTipo(i));
        }
        int tipo = lerInteiro("Informe o tipo do animal: ");
        String nome = lerTexto("Informe o nome do animal: ");
        int idade = lerInteiro("Informe a idade do animal: ");
        Animal animal = criarAnimal(tipo, nome, idade);
        if (animal == null) {
            System.out.println("Tipo de animal inválido. Cadastro cancelado.");
            return;
        }
        animais.add(animal);
        System.out.println("Animal cadastrado com sucesso: " + animal.getDescricao());
        animal.comer("ração especial");
    }

    private static String nomeTipo(int tipo) {
        return switch (tipo) {
            case 1 -> "Cachorro"; case 2 -> "Gato"; case 3 -> "Golfinho"; case 4 -> "Leao"; case 5 -> "Morcego";
            case 6 -> "Pato"; case 7 -> "Pinguim"; case 8 -> "Arara"; case 9 -> "Aguia"; case 10 -> "PeixeMorcego";
            case 11 -> "Traira"; case 12 -> "Salmao"; case 13 -> "Tubarao"; case 14 -> "Tartaruga"; case 15 -> "Iguana";
            case 16 -> "Cobra"; case 17 -> "Crocodilo"; default -> "Desconhecido";
        };
    }

    private static Animal criarAnimal(int tipo, String nome, int idade) {
        return switch (tipo) {
            case 1 -> new Cachorro(nome, idade); case 2 -> new Gato(nome, idade); case 3 -> new Golfinho(nome, idade);
            case 4 -> new Leao(nome, idade); case 5 -> new Morcego(nome, idade); case 6 -> new Pato(nome, idade);
            case 7 -> new Pinguim(nome, idade); case 8 -> new Arara(nome, idade); case 9 -> new Aguia(nome, idade);
            case 10 -> new PeixeMorcego(nome, idade); case 11 -> new Traira(nome, idade); case 12 -> new Salmao(nome, idade);
            case 13 -> new Tubarao(nome, idade); case 14 -> new Tartaruga(nome, idade); case 15 -> new Iguana(nome, idade);
            case 16 -> new Cobra(nome, idade); case 17 -> new Crocodilo(nome, idade); default -> null;
        };
    }

    private static void listarTodosAnimais() {
        if (animais.isEmpty()) { System.out.println("Nenhum animal cadastrado."); return; }
        for (Animal animal : animais) {
            System.out.println(animal.getDescricao());
            animal.emitirSom();
            animal.comer();
            System.out.println("-----------------------------");
        }
    }

    private static void listarCorredores() {
        boolean encontrou = false;
        for (Animal animal : animais) if (animal instanceof Corrida corredor) { System.out.println(animal.getDescricao()); corredor.correr(); System.out.println("-----------------------------"); encontrou = true; }
        if (!encontrou) System.out.println("Nenhum animal corredor encontrado.");
    }
    private static void listarNadadores() {
        boolean encontrou = false;
        for (Animal animal : animais) if (animal instanceof Nado nadador) { System.out.println(animal.getDescricao()); nadador.nadar(); System.out.println("-----------------------------"); encontrou = true; }
        if (!encontrou) System.out.println("Nenhum animal nadador encontrado.");
    }
    private static void listarVoadores() {
        boolean encontrou = false;
        for (Animal animal : animais) if (animal instanceof Voo voador) { System.out.println(animal.getDescricao()); voador.voar(); System.out.println("-----------------------------"); encontrou = true; }
        if (!encontrou) System.out.println("Nenhum animal voador encontrado.");
    }
    private static void listarPredadores() {
        boolean encontrou = false;
        for (Animal animal : animais) if (animal instanceof Predacao predador) { System.out.println(animal.getDescricao()); predador.cacar(); System.out.println("-----------------------------"); encontrou = true; }
        if (!encontrou) System.out.println("Nenhum animal predador encontrado.");
    }
    private static void exibirTotalAnimais() {
        System.out.println("Total de animais criados no sistema: " + Animal.getContador());
        System.out.println("Animais armazenados na lista: " + animais.size());
    }
    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try { return Integer.parseInt(SC.nextLine()); } catch (NumberFormatException e) { System.out.println("Valor inválido. Digite um número inteiro."); }
        }
    }
    private static String lerTexto(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = SC.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            System.out.println("Texto inválido. Tente novamente.");
        }
    }
    private static void carregarAnimaisIniciais() {
        animais.add(new Cachorro("Rex", 5)); animais.add(new Gato("Mimi", 3)); animais.add(new Golfinho("Flipper", 8));
        animais.add(new Pato("Donald", 4)); animais.add(new Pinguim("Pingo", 6)); animais.add(new PeixeMorcego("Bat", 2));
        animais.add(new Traira("Trai", 3)); animais.add(new Leao("Simba", 7)); animais.add(new Morcego("Bruce", 2));
        animais.add(new Arara("Loro", 5)); animais.add(new Aguia("Thor", 4)); animais.add(new Salmao("Sal", 2));
        animais.add(new Tubarao("Jaws", 9)); animais.add(new Tartaruga("Tuga", 12)); animais.add(new Iguana("Iggy", 4));
        animais.add(new Cobra("Naja", 6)); animais.add(new Crocodilo("Croco", 10));
    }
}
