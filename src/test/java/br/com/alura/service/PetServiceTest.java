package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import br.com.alura.domain.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

public class PetServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);

    private PetsService petsService = new PetsService(client);
    private AbrigoService abrigoService = new AbrigoService(client);

    private HttpResponse<String> response = mock(HttpResponse.class);
    private final Pet pet = new Pet("CACHORRO","Rex","Poodle",5,"Marrom", 10.5F);

    Scanner scannerMock = Mockito.mock(Scanner.class);


    @Test
    public void deveVerificaPetsDoAbrigo() throws IOException, InterruptedException {

        Abrigo abrigo = new Abrigo();
        abrigo.setId(0L);
        abrigo.setNome("TesteTestando");
        abrigo.setTelefone("61999999999");
        abrigo.setEmail("abrigo_carlos@gmail.com");

        when(scannerMock.nextLine()).thenReturn("TesteTestando", "61999999999", "abrigo_carlos@gmail.com");
        when(client.processaPost(anyString(), any())).thenReturn(response);

        abrigoService.cadastrarAbnrigo(abrigo);
        verify(client.processaPost(anyString(), anyString()), atLeast(1));

        pet.setId(0L);
        String expectedPetsCadastrados = "Pets cadastrados: 0 - Cachorro - Rex - Labrador - 3 ano(s)";


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.statusCode()).thenReturn(200);
        when(response.body()).thenReturn(pet.toString());


        petsService.listartPetsdoAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualPetsCadastrados = lines[0];


        String expectedOutput = "Pets cadastrados: 0 - Cachorro - Rex - Labrador - 3 ano(s)";
        assertEquals(expectedPetsCadastrados, actualPetsCadastrados);

    }



}
