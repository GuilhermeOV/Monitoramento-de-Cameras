package cftv;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Cameras {
    private boolean ligado;
    private boolean gravando;
    private int contador;
    private boolean zoom;
    private Timer timer;
    private int localizador;

    public Cameras() {
        this.ligado = false;
        this.gravando = false;
        this.contador = 0;
        this.zoom = false;
        this.localizador = 0;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void ligarDesligar() {
        ligado = !ligado;
    }

    public boolean isGravando() {
        return gravando;
    }

    public void iniciarGravacao() {
        if (ligado) {
            gravando = true;
            iniciarTimer();
        }
    }

    private void iniciarTimer() {
        if (timer == null) {
            timer = new Timer(1000, (ActionEvent e) -> {
                if (gravando) {
                    contador++;
                }
            });
            timer.start();
        }
    }

    public int getContador() {
        return contador;
    }

    public void pararGravacao() {
        gravando = false;
        pararTimer();
    }

    private void pararTimer() {
        if (timer != null) {
            timer.stop();
            timer = null;
            contador = 0;
        }
    }

    public boolean isZoom() {
        return zoom;
    }

    public void ativarZoom() {
        zoom = true;
    }

    public void desativarZoom() {
        zoom = false;
        localizador = 0;
    }

    public void salvarImagem(BufferedImage imagem) {
        String pasta = "src/prints";
        String arquivo = pasta + "/camera_" + System.currentTimeMillis() + ".png";

        File outputFile = new File(arquivo);
        outputFile.getParentFile().mkdirs();

        try {
            ImageIO.write(imagem, "png", outputFile);
        } catch (IOException e) {
        }
    }

    public void moverDireita(int totalCameras) {
       
        localizador = (localizador + 1) % totalCameras;
       }

    

    public void moverEsquerda(int totalCameras) {
        if(zoom){
        localizador = (localizador - 1 + totalCameras) % totalCameras;}
         
    }

     public void moverParaCima(int totalLinhas, int totalColunas) {
         if(zoom){
        int linhaAtual = localizador / totalColunas;
        int colunaAtual = localizador % totalColunas;
        int novaLinha = (linhaAtual - 1 + totalLinhas) % totalLinhas;
        localizador = novaLinha * totalColunas + colunaAtual;}
         
    }

    public void moverParaBaixo(int totalLinhas, int totalColunas) {
        if(zoom){
        int linhaAtual = localizador / totalColunas;
        int colunaAtual = localizador % totalColunas;
        int novaLinha = (linhaAtual + 1) % totalLinhas;
        localizador = novaLinha * totalColunas + colunaAtual;}
    }
    public int getLocalizador() {
        return localizador;
    }
}
