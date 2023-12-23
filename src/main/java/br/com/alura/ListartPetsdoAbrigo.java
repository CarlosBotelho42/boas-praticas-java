package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetsService;

import java.io.IOException;

public class ListartPetsdoAbrigo implements Command{
    @Override
    public void execute(){
        try {
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            PetsService petsService = new PetsService(client);

            petsService.listartPetsdoAbrigo();

        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
