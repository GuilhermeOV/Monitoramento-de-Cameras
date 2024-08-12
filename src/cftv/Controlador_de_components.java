package cftv;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Controlador_de_components {
    Cameras cameras[];
    JRadioButton[] radioButtons;
    JLabel[] telaCameras;
    
 public Controlador_de_components(Cameras[] cameras, JRadioButton[] radioButtons, JLabel[] telaCameras) {}
 
 public Cameras selecionarCamera(JRadioButton radioButton){
     for (int i=0; i<radioButtons.length; i++){
         if (radioButtons[i]==radioButton){
             return cameras[i];
         }
     }
        return null;
 }
 
 public JLabel selecionarTela(int tela){
     if(tela>=0 && tela < telaCameras.length){
         return telaCameras[tela];
     }
        return null;
 }
    
}
