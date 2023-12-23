package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetsService;

import java.io.IOException;

public class ImportartPetsdoAbrigo implements Command{
    @Override
    public void execute(){
        try {
            ClientHttpConfiguration client = new ClientHttpConfiguration();
            PetsService petsService = new PetsService(client);

            petsService.importartPetsdoAbrigo();

        }catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }

    }
}
