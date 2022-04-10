package files;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList;

public class CPF {
    private final ArrayList<Integer> listaAleatoria = new ArrayList<>();
    private ArrayList<Integer> listaNumMultiplicados = null;

    private int geraNumAleatorio(){

        return (int) (Math.random() * 10);
    }

    private void geraCPFParcial(){
        for(int i = 0; i < 9; i++){
            listaAleatoria.add(geraNumAleatorio());
        }

    }

    private void geraPrimeiroDigito(){
        listaNumMultiplicados = new ArrayList<>();
        int primeiroDigito;
        int totalSomatoria = 0;
        int restoDivisao;
        int peso = 10;

        for(int item : listaAleatoria){
            listaNumMultiplicados.add(item * peso);

            peso--;
        }

        for(int item : listaNumMultiplicados){
            totalSomatoria += item;
        }

        restoDivisao = (totalSomatoria % 11);

        if(restoDivisao < 2){
            primeiroDigito = 0;
        } else{
            primeiroDigito = 11 - restoDivisao;
        }

        listaAleatoria.add(primeiroDigito);

    }

    private void geraSegundoDigito(){
        listaNumMultiplicados = new ArrayList<>();
        int segundoDigito;
        int totalSomatoria = 0;
        int restoDivisao;
        int peso = 11;

        for(int item : listaAleatoria){
            listaNumMultiplicados.add(item * peso);

            peso--;
        }

        for(int item : listaNumMultiplicados){
            totalSomatoria += item;
        }

        restoDivisao = (totalSomatoria % 11);

        if(restoDivisao < 2){
            segundoDigito = 0;
        } else{
            segundoDigito = 11 - restoDivisao;
        }

        listaAleatoria.add(segundoDigito);

    }

    public String geraCPFFinal() {
        geraCPFParcial();
        geraPrimeiroDigito();
        geraSegundoDigito();

        String cpf = "";
        StringBuilder texto = new StringBuilder();

        for(int item : listaAleatoria){
            texto.append(item);
        }

        try{
            MaskFormatter mf = new MaskFormatter("###.###.###-##");
            mf.setValueContainsLiteralCharacters(false);
            cpf = mf.valueToString(texto.toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return cpf;
    }
}