package br.com.alura;

import java.io.IOException;

public class CommandExecute {

    public void executeCommand(Command command) throws IOException, InterruptedException {

        command.execute();
    }
}
