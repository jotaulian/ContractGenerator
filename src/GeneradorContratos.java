import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class GeneradorContratos {

    public void crearContrato(String rutaContrato){
        String contenidoContrato = cargarContrato(rutaContrato);

        contenidoContrato = changeContractInfo("Ingresa la fecha actual:", contenidoContrato, "[CURRENT_DATE]");
        contenidoContrato = changeContractInfo("Ingresa el nombre de la empresa:", contenidoContrato, "[COMPANY_NAME]");
        contenidoContrato = changeContractInfo("Ingresa el nombre del empleado:", contenidoContrato, "[EMPLOYEE_NAME]");
        contenidoContrato = changeContractInfo("Ingresa la ciudad:", contenidoContrato, "[CITY]");
        contenidoContrato = changeContractInfo("Ingresa el país:", contenidoContrato, "[COUNTRY]");
        contenidoContrato = changeContractInfo("Ingresa el puesto del empleado:", contenidoContrato, "[job title]");

        guardarContratoModificado(contenidoContrato);
    }

    public void guardarContratoModificado(String contenidoContrato) {
        try {
            FileWriter fw = new FileWriter("contrato_completado.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenidoContrato);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String changeContractInfo(String pregunta, String contenidoContrato, String target) {
        Scanner teclado = new Scanner(System.in);

        System.out.println(pregunta);
        String newInfo = teclado.nextLine();
        return contenidoContrato.replace(target, newInfo);
    }

    public String cargarContrato(String rutaContrato){
        String contenido = "";
        try {
            Path ruta = Paths.get(rutaContrato);
            List<String> lineas = Files.readAllLines(ruta);
            for (String linea : lineas){
                contenido += linea + "\n";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return contenido;
    }

}
