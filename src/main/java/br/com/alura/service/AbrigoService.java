package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AbrigoService {

    Scanner scanner = new Scanner(System.in);

    private final ClientHttpConfiguration clientHttp;
    public AbrigoService(ClientHttpConfiguration clientHttp){
        this.clientHttp = clientHttp;
    }

    public void listartAbrigo() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos";

        HttpResponse<String> response = clientHttp.processaGet(uri);

        String responseBody = response.body();

        Abrigo[] abrigos = new ObjectMapper().readValue(responseBody, Abrigo[].class);
        List<Abrigo> abrigoList = Arrays.asList(abrigos);

        if(abrigoList.isEmpty()){
            System.out.println("Não existem abrigos cadastrados");
        }else {
            System.out.println("Abrigos cadastrados:");
            mostrarAbrigos(abrigoList);
        }
    }

    private static void mostrarAbrigos(List<Abrigo> abrigos) {
        for (Abrigo abrigo : abrigos) {
            long id = abrigo.getId();
            String nome = abrigo.getNome();
            System.out.println(id +" - " +nome);
        }
    }

    public void cadastrarAbnrigo(Abrigo abrigo) throws IOException, InterruptedException {

        lerDadosCadastro(abrigo);

        String uri = "http://localhost:8080/abrigos";

        HttpResponse<String> response = clientHttp.processaPost(uri, abrigo);

        int statusCode = response.statusCode();
        String responseBody = response.body();

        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!");
            System.out.println(responseBody);
        } else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:");
            System.out.println(responseBody);
        }

    }

    private void lerDadosCadastro(Abrigo abrigo) {
        System.out.println("Digite o nome do abrigo:");
        String nome = scanner.nextLine();
        System.out.println("Digite o telefone do abrigo:");
        String telefone = scanner.nextLine();
        System.out.println("Digite o email do abrigo:");
        String email = scanner.nextLine();

        abrigo.setNome(nome);
        abrigo.setTelefone(telefone);
        abrigo.setEmail(email);
    }
}
