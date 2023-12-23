package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AbrigoServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);

    private AbrigoService abrigoService = new AbrigoService(client);
    private PetsService petsService = new PetsService(client);

    private HttpResponse<String> response = mock(HttpResponse.class);
    private Abrigo abrigo = new Abrigo("TesteTestando", "61999999999", "abrigo_carlos@gmail.com");

    @Test
    public void deveVerificarSeQuandoExsiteAbrigo() throws IOException, InterruptedException {

        abrigo.setId(0L);
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdENome = "0 - TesteTestando";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[{"+abrigo.toString()+"}]");
        when(client.processaGet(anyString())).thenReturn(response);

        abrigoService.listartAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdENome = lines[1];

        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectedIdENome, actualIdENome);
    }

    @Test
    public void deveVerificarSeQuandoNaoExisteBrigos() throws IOException, InterruptedException {

        abrigo.setId(0L);
        String expected= "Não existem abrigos cadastrados";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(client.processaGet(anyString())).thenReturn(response);

        abrigoService.listartAbrigo();

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[0];

        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void deveVerificarSeDispararRequisicaoPostSeraChamado() throws IOException, InterruptedException {

        String userInput = String.format("Teste%spets.csv",
                System.lineSeparator());

        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        when(client.processaPost(anyString(), any())).thenReturn(response);

        petsService.importartPetsdoAbrigo();
        verify(client.processaPost(anyString(), anyString()), atLeast(1));
    }
}
