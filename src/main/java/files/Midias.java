package files;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Midias {

    private static File downloadContent(String URL, String nomeArquivo) throws IOException {

            URL url = new URL(URL);
            File file = new File(nomeArquivo);

            FileUtils.copyURLToFile(url, file);

            return file;
    }

    public static File uploadConteudoMensagemImagem() throws IOException {

        return downloadContent("https://rest-assured.s3-us-west-2.amazonaws.com/thumbnail.png", "thumbnail.png");
    }
}
