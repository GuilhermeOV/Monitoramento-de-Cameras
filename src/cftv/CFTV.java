package cftv;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author 6114456
 */
public class CFTV extends javax.swing.JFrame {

    /**
     * Creates new form CFTV1
     */
    private javax.swing.Timer timer;
    private final List<Cameras> cameras;
    private final ButtonGroup buttonGroup;
    private final List<JLabel> gravandoCamera;
    private final List<JLabel> tempoGravacao;
    private final List<JLabel> cameraZoom;
    private final List<JLabel> gravandoCameraZoom;
    private final List<JLabel> tempoGravacaoZoom;
    
    
    public CFTV() {
         initComponents();
        cameras = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            cameras.add(new Cameras());
        }
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioCamera1);
        buttonGroup.add(radioCamera2);
        buttonGroup.add(radioCamera3);
        buttonGroup.add(radioCamera4);
        buttonGroup.add(radioCamera5);
        buttonGroup.add(radioCamera6);

        gravandoCamera = new ArrayList<>();
        tempoGravacao = new ArrayList<>();
        cameraZoom = new ArrayList<>();
        gravandoCameraZoom = new ArrayList<>();
        tempoGravacaoZoom = new ArrayList<>();

        gravandoCamera.add(gravandoCamera1);
        gravandoCamera.add(gravandoCamera2);
        gravandoCamera.add(gravandoCamera3);
        gravandoCamera.add(gravandoCamera4);
        gravandoCamera.add(gravandoCamera5);
        gravandoCamera.add(gravandoCamera6);

        tempoGravacao.add(tempoGravacao1);
        tempoGravacao.add(tempoGravacao2);
        tempoGravacao.add(tempoGravacao3);
        tempoGravacao.add(tempoGravacao4);
        tempoGravacao.add(tempoGravacao5);
        tempoGravacao.add(tempoGravacao6);

        cameraZoom.add(cameraZoom1);
        cameraZoom.add(cameraZoom2);
        cameraZoom.add(cameraZoom3);
        cameraZoom.add(cameraZoom4);
        cameraZoom.add(cameraZoom5);
        cameraZoom.add(cameraZoom6);

        gravandoCameraZoom.add(gravandoCameraZoom1);
        gravandoCameraZoom.add(gravandoCameraZoom2);
        gravandoCameraZoom.add(gravandoCameraZoom3);
        gravandoCameraZoom.add(gravandoCameraZoom4);
        gravandoCameraZoom.add(gravandoCameraZoom5);
        gravandoCameraZoom.add(gravandoCameraZoom6);

        tempoGravacaoZoom.add(tempoGravacaoZoom1);
        tempoGravacaoZoom.add(tempoGravacaoZoom2);
        tempoGravacaoZoom.add(tempoGravacaoZoom3);
        tempoGravacaoZoom.add(tempoGravacaoZoom4);
        tempoGravacaoZoom.add(tempoGravacaoZoom5);
        tempoGravacaoZoom.add(tempoGravacaoZoom6);
       
       
    }
    
   
    

    @SuppressWarnings("unchecked")
    
    
      private Cameras getCameraSelecionada() {
        int index = getCameraSelecionadaIndex();
        return index != -1 ? cameras.get(index) : null;
    }
      
       private int getCameraSelecionadaIndex() {
        if (radioCamera1.isSelected()) return 0;
        if (radioCamera2.isSelected()) return 1;
        if (radioCamera3.isSelected()) return 2;
        if (radioCamera4.isSelected()) return 3;
        if (radioCamera5.isSelected()) return 4;
        if (radioCamera6.isSelected()) return 5;
        return -1;
    }
    
       
       private void atualizarSelecaoCamera(int localizador) {
    switch (localizador) {
        case 0:
            radioCamera1.setSelected(true);
            break;
        case 1:
            radioCamera2.setSelected(true);
            break;
        case 2:
            radioCamera3.setSelected(true);
            break;
        case 3:
            radioCamera4.setSelected(true);
            break;
        case 4:
            radioCamera5.setSelected(true);
            break;
        case 5:
            radioCamera6.setSelected(true);
            break;
    }
}
       
       private void atualizarInterfaceComBaseNoLocalizador(int localizador) {
    for (JLabel cameraLabel : cameraZoom) {
        cameraLabel.setVisible(false);
    }
    cameraZoom.get(localizador).setVisible(true);

    if (cameras.get(localizador).isGravando()) {
        gravandoCameraZoom.get(localizador).setVisible(true);
        tempoGravacaoZoom.get(localizador).setVisible(true);
    }
}
      
     private void iniciarGravacao(JLabel gravandoLabel, JLabel tempoLabel) {
        Cameras camera = getCameraSelecionada();
        if (camera != null) {
            camera.iniciarGravacao();
            gravandoLabel.setVisible(true);
            tempoLabel.setVisible(true);
            if (!camera.isZoom() && gravandoLabel == gravandoCameraZoom.get(getCameraSelecionadaIndex())) {
                gravandoLabel.setVisible(false);
                tempoLabel.setVisible(false);
            }
            timer = new Timer(1000, (ActionEvent e) -> {
                if (camera.isGravando()) {
                    atualizarLabelTempo(tempoLabel, camera.getContador());
                } else {
                    timer.stop();
                    timer = null;
                }
            });
            timer.start();
        }
    }
     
     
   
    
    private void pararGravacao() {
        Cameras camera = getCameraSelecionada();
        if (camera != null) {
            camera.pararGravacao();
            int index = getCameraSelecionadaIndex();
            gravandoCamera.get(index).setVisible(false);
            tempoGravacao.get(index).setVisible(false);
            gravandoCameraZoom.get(index).setVisible(false);
            tempoGravacaoZoom.get(index).setVisible(false);
            btGravar.setBackground(Color.white);
        }
    }
    
     private void atualizarLabelTempo(JLabel label, int contador) {
        int horas = contador / 3600;
        int minutos = (contador % 3600) / 60;
        int segundos = contador % 60;
        String tempoString = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        label.setText(tempoString);
    }
    
        
      private void esconderTodosOsRótulosDeGravacao() {
        for (JLabel label : gravandoCamera) {
            label.setVisible(false);
        }
        for (JLabel label : tempoGravacao) {
            label.setVisible(false);
        }
    }
      
    private void salvarImagem(JLabel cameraLabel) {
          int index = getCameraSelecionadaIndex();
        if (index != -1) {
            Cameras camera = cameras.get(index);
            BufferedImage imagem = new BufferedImage(cameraLabel.getWidth(), cameraLabel.getHeight(), BufferedImage.TYPE_INT_RGB);
            cameraLabel.paint(imagem.getGraphics());

            camera.salvarImagem(imagem);
            JOptionPane.showMessageDialog(null, "Imagem salva com sucesso em pasta PRINTS");
        }
    }
    
   
   
    
      
     private void updateInterface(boolean status) {
        telaCamera1.setEnabled(status);
        telaCamera2.setEnabled(status);
        telaCamera3.setEnabled(status);
        telaCamera4.setEnabled(status);
        telaCamera5.setEnabled(status);
        telaCamera6.setEnabled(status);
        btGravar.setEnabled(status);
        btZoom.setEnabled(status);
        btPrint.setEnabled(status);
        btSubir.setEnabled(status);
        btDireita.setEnabled(status);
        btEsquerda.setEnabled(status);
        btDescer.setEnabled(status);
        btLigarDesligar.setBackground(status ? Color.GREEN : Color.RED);
    } 
      
    public void iniciarZoom(JLabel zoom) {
        Cameras camera = getCameraSelecionada();
        if (camera != null) {
            camera.ativarZoom();
            esconderTodosOsRotulosDeGravacao();
            zoom.setVisible(true);
            int index = getCameraSelecionadaIndex();
            if (camera.isGravando()) {
                tempoGravacaoZoom.get(index).setVisible(true);
                gravandoCameraZoom.get(index).setVisible(true);
            }
        }
    }
    
    public void pararZoom() {
        Cameras camera = getCameraSelecionada();
        if (camera != null) {
            camera.desativarZoom();
            for (JLabel zoom : cameraZoom) {
                zoom.setVisible(false);
            }
            int index = getCameraSelecionadaIndex();
            gravandoCameraZoom.get(index).setVisible(false);
            tempoGravacaoZoom.get(index).setVisible(false);
            mostrarTodosOsRotulosDeGravacao();
        }
    }
    
    private void mostrarTodosOsRotulosDeGravacao() {
        telaCamera1.setVisible(true);
        telaCamera2.setVisible(true);
        telaCamera3.setVisible(true);
        telaCamera4.setVisible(true);
        telaCamera5.setVisible(true);
        telaCamera6.setVisible(true);
        radioCamera1.setVisible(true);
        radioCamera2.setVisible(true);
        radioCamera3.setVisible(true);
        radioCamera4.setVisible(true);
        radioCamera5.setVisible(true);
        radioCamera6.setVisible(true);
        for (int i = 0; i < cameras.size(); i++) {
            if (cameras.get(i).isGravando()) {
                gravandoCamera.get(i).setVisible(true);
                tempoGravacao.get(i).setVisible(true);
            }
        }
    }
    
    private void esconderTodosOsRotulosDeGravacao() {
        telaCamera1.setVisible(false);
        telaCamera2.setVisible(false);
        telaCamera3.setVisible(false);
        telaCamera4.setVisible(false);
        telaCamera5.setVisible(false);
        telaCamera6.setVisible(false);
        radioCamera1.setVisible(false);
        radioCamera2.setVisible(false);
        radioCamera3.setVisible(false);
        radioCamera4.setVisible(false);
        radioCamera5.setVisible(false);
        radioCamera6.setVisible(false);
        for (int i = 0; i < cameras.size(); i++) {
            if (cameras.get(i).isGravando()) {
                gravandoCamera.get(i).setVisible(false);
                tempoGravacao.get(i).setVisible(false);
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tempoGravacaoZoom1 = new javax.swing.JLabel();
        tempoGravacaoZoom2 = new javax.swing.JLabel();
        tempoGravacaoZoom3 = new javax.swing.JLabel();
        tempoGravacaoZoom4 = new javax.swing.JLabel();
        tempoGravacaoZoom5 = new javax.swing.JLabel();
        tempoGravacaoZoom6 = new javax.swing.JLabel();
        gravandoCameraZoom1 = new javax.swing.JLabel();
        gravandoCameraZoom2 = new javax.swing.JLabel();
        gravandoCameraZoom3 = new javax.swing.JLabel();
        gravandoCameraZoom4 = new javax.swing.JLabel();
        gravandoCameraZoom5 = new javax.swing.JLabel();
        gravandoCameraZoom6 = new javax.swing.JLabel();
        cameraZoom1 = new javax.swing.JLabel();
        cameraZoom2 = new javax.swing.JLabel();
        cameraZoom3 = new javax.swing.JLabel();
        cameraZoom4 = new javax.swing.JLabel();
        cameraZoom5 = new javax.swing.JLabel();
        cameraZoom6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btLigarDesligar = new javax.swing.JButton();
        btGravar = new javax.swing.JButton();
        btZoom = new javax.swing.JButton();
        btPrint = new javax.swing.JButton();
        btSubir = new javax.swing.JButton();
        btDescer = new javax.swing.JButton();
        btDireita = new javax.swing.JButton();
        btEsquerda = new javax.swing.JButton();
        radioCamera1 = new javax.swing.JRadioButton();
        radioCamera2 = new javax.swing.JRadioButton();
        radioCamera3 = new javax.swing.JRadioButton();
        radioCamera4 = new javax.swing.JRadioButton();
        radioCamera5 = new javax.swing.JRadioButton();
        radioCamera6 = new javax.swing.JRadioButton();
        gravandoCamera1 = new javax.swing.JLabel();
        gravandoCamera2 = new javax.swing.JLabel();
        gravandoCamera3 = new javax.swing.JLabel();
        gravandoCamera4 = new javax.swing.JLabel();
        gravandoCamera5 = new javax.swing.JLabel();
        gravandoCamera6 = new javax.swing.JLabel();
        tempoGravacao1 = new javax.swing.JLabel();
        telaCamera1 = new javax.swing.JLabel();
        tempoGravacao2 = new javax.swing.JLabel();
        telaCamera2 = new javax.swing.JLabel();
        tempoGravacao3 = new javax.swing.JLabel();
        telaCamera3 = new javax.swing.JLabel();
        tempoGravacao4 = new javax.swing.JLabel();
        telaCamera4 = new javax.swing.JLabel();
        tempoGravacao5 = new javax.swing.JLabel();
        telaCamera5 = new javax.swing.JLabel();
        tempoGravacao6 = new javax.swing.JLabel();
        telaCamera6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tempoGravacaoZoom1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacaoZoom1.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacaoZoom1.setVisible(false);
        tempoGravacaoZoom1.setText("00:00:00");
        jPanel1.add(tempoGravacaoZoom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        tempoGravacaoZoom2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacaoZoom2.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacaoZoom2.setText("00:00:00");
        tempoGravacaoZoom2.setVisible(false);
        jPanel1.add(tempoGravacaoZoom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        tempoGravacaoZoom3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacaoZoom3.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacaoZoom3.setText("00:00:00");
        tempoGravacaoZoom3.setVisible(false);
        jPanel1.add(tempoGravacaoZoom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        tempoGravacaoZoom4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacaoZoom4.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacaoZoom4.setText("00:00:00");
        tempoGravacaoZoom4.setVisible(false);
        jPanel1.add(tempoGravacaoZoom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        tempoGravacaoZoom5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacaoZoom5.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacaoZoom5.setText("00:00:00");
        tempoGravacaoZoom5.setVisible(false);
        jPanel1.add(tempoGravacaoZoom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        tempoGravacaoZoom6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacaoZoom6.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacaoZoom6.setText("00:00:00");
        tempoGravacaoZoom6.setVisible(false);
        jPanel1.add(tempoGravacaoZoom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        gravandoCameraZoom1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCameraZoom1.setVisible(false);
        jPanel1.add(gravandoCameraZoom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        gravandoCameraZoom2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCameraZoom2.setVisible(false);
        jPanel1.add(gravandoCameraZoom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        gravandoCameraZoom3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCameraZoom3.setVisible(false);
        jPanel1.add(gravandoCameraZoom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        gravandoCameraZoom4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCameraZoom4.setVisible(false);
        jPanel1.add(gravandoCameraZoom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        gravandoCameraZoom5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCameraZoom5.setVisible(false);
        jPanel1.add(gravandoCameraZoom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        gravandoCameraZoom6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCameraZoom6.setVisible(false);
        jPanel1.add(gravandoCameraZoom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        cameraZoom1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera.1.zoom.jpg"))); // NOI18N
        cameraZoom1.setText("jLabel1");
        cameraZoom1.setVisible(false);
        jPanel1.add(cameraZoom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 10, 1160, 380));

        cameraZoom2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera2.0.zoom.jpg"))); // NOI18N
        cameraZoom2.setText("jLabel1");
        cameraZoom2.setVisible(false);
        jPanel1.add(cameraZoom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 10, 1160, 380));

        cameraZoom3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera3.0.zoom.jpg"))); // NOI18N
        cameraZoom3.setText("jLabel1");
        cameraZoom3.setVisible(false);
        jPanel1.add(cameraZoom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 10, 1160, 380));

        cameraZoom4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera4.0.zoom.jpg"))); // NOI18N
        cameraZoom4.setText("jLabel1");
        cameraZoom4.setVisible(false);
        jPanel1.add(cameraZoom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 10, 1160, 380));

        cameraZoom5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera5.0.zoom.jpg"))); // NOI18N
        cameraZoom5.setText("jLabel1");
        cameraZoom5.setVisible(false);
        jPanel1.add(cameraZoom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 10, 1160, 380));

        cameraZoom6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera6.0.zoom.jpg"))); // NOI18N
        cameraZoom6.setText("jLabel1");
        cameraZoom6.setVisible(false);
        jPanel1.add(cameraZoom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-150, 10, 1160, 380));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btLigarDesligar.setBackground(new java.awt.Color(255, 51, 51));
        btLigarDesligar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/Hnet.com-image.png"))); // NOI18N
        btLigarDesligar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(153, 0, 0), new java.awt.Color(153, 0, 0), new java.awt.Color(153, 0, 0), new java.awt.Color(153, 0, 0)));
        btLigarDesligar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLigarDesligarActionPerformed(evt);
            }
        });

        btGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/gravar.png"))); // NOI18N
        btGravar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btGravar.setEnabled(false);
        btGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGravarActionPerformed(evt);
            }
        });

        btZoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/zoom.png"))); // NOI18N
        btZoom.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btZoom.setEnabled(false);
        btZoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btZoomActionPerformed(evt);
            }
        });

        btPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/print.png"))); // NOI18N
        btPrint.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btPrint.setEnabled(false);
        btPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintActionPerformed(evt);
            }
        });

        btSubir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/istockphoto-1350036827-612x612.jpg"))); // NOI18N
        btSubir.setEnabled(false);
        btSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSubirActionPerformed(evt);
            }
        });

        btDescer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/baixo.jpg"))); // NOI18N
        btDescer.setEnabled(false);
        btDescer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDescerActionPerformed(evt);
            }
        });

        btDireita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/direita.jpg"))); // NOI18N
        btDireita.setEnabled(false);
        btDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDireitaActionPerformed(evt);
            }
        });

        btEsquerda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/lado esquerdo.jpg"))); // NOI18N
        btEsquerda.setEnabled(false);
        btEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEsquerdaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btLigarDesligar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btGravar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(btZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(btPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(btDescer, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(btDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btDescer, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btEsquerda, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btLigarDesligar)
                    .addComponent(btZoom, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPrint)
                    .addComponent(btGravar))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 393, -1, -1));
        jPanel1.add(radioCamera1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, 20));
        jPanel1.add(radioCamera2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, -1, -1));
        jPanel1.add(radioCamera3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, -1, -1));
        jPanel1.add(radioCamera4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, -1, -1));
        jPanel1.add(radioCamera5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 190, -1, -1));
        jPanel1.add(radioCamera6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 190, -1, -1));

        gravandoCamera1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCamera1.setVisible(false);
        jPanel1.add(gravandoCamera1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 40));

        gravandoCamera2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCamera2.setVisible(false);
        jPanel1.add(gravandoCamera2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, -1, -1));

        gravandoCamera3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCamera3.setVisible(false);
        jPanel1.add(gravandoCamera3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, -1, -1));

        gravandoCamera4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCamera4.setVisible(false);
        jPanel1.add(gravandoCamera4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        gravandoCamera5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCamera5.setVisible(false);
        jPanel1.add(gravandoCamera5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, -1, -1));

        gravandoCamera6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/pngtree-video-recording-button-with-record-symbol-in-red-white-colors-vector-png-image_10106569 (1).png"))); // NOI18N
        gravandoCamera6.setVisible(false);
        jPanel1.add(gravandoCamera6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 190, -1, -1));

        tempoGravacao1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacao1.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacao1.setText("00:00:00");
        tempoGravacao1.setVisible(false);
        jPanel1.add(tempoGravacao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 110, 20));

        telaCamera1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera.1.jpg"))); // NOI18N
        telaCamera1.setToolTipText("");
        telaCamera1.setEnabled(false);
        jPanel1.add(telaCamera1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 500, 230));

        tempoGravacao2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacao2.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacao2.setText("00:00:00");
        tempoGravacao2.setVisible(false);
        tempoGravacao2.setToolTipText("");
        jPanel1.add(tempoGravacao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, -1, -1));

        telaCamera2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera2.0.jpg"))); // NOI18N
        telaCamera2.setEnabled(false);
        jPanel1.add(telaCamera2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 349, 182));

        tempoGravacao3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacao3.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacao3.setText("00:00:00");
        tempoGravacao3.setVisible(false);
        jPanel1.add(tempoGravacao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, -1, -1));

        telaCamera3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera3.0.jpg"))); // NOI18N
        telaCamera3.setEnabled(false);
        jPanel1.add(telaCamera3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 0, -1, 182));

        tempoGravacao4.setBackground(new java.awt.Color(255, 255, 255));
        tempoGravacao4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacao4.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacao4.setVisible(false);
        tempoGravacao4.setText("00:00:00");
        jPanel1.add(tempoGravacao4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        telaCamera4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera4.0.jpg"))); // NOI18N
        telaCamera4.setEnabled(false);
        jPanel1.add(telaCamera4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 348, 187));

        tempoGravacao5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacao5.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacao5.setText("00:00:00");
        tempoGravacao5.setVisible(false);
        jPanel1.add(tempoGravacao5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, -1, -1));

        telaCamera5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera5.0.jpg"))); // NOI18N
        telaCamera5.setEnabled(false);
        jPanel1.add(telaCamera5, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 188, -1, 187));

        tempoGravacao6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tempoGravacao6.setForeground(new java.awt.Color(255, 0, 0));
        tempoGravacao6.setText("00:00:00");
        tempoGravacao6.setVisible(false);
        jPanel1.add(tempoGravacao6, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, -1, -1));

        telaCamera6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cftv/camera6.0.jpg"))); // NOI18N
        telaCamera6.setEnabled(false);
        jPanel1.add(telaCamera6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 190, -1, 187));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
 private void esconderZoom() {
    for (JLabel zoom : cameraZoom) {
        zoom.setVisible(false);
    }
    for (JLabel label : gravandoCameraZoom) {
        label.setVisible(false);
    }
    for (JLabel label : tempoGravacaoZoom) {
        label.setVisible(false);
    }
}
    private void btEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEsquerdaActionPerformed
       for (Cameras camera : cameras) {
        camera.moverEsquerda(cameras.size());
    }
    int localizadorAtual = cameras.get(getCameraSelecionadaIndex()).getLocalizador();
    atualizarInterfaceComBaseNoLocalizador(localizadorAtual);
    atualizarSelecaoCamera(localizadorAtual);
    }//GEN-LAST:event_btEsquerdaActionPerformed

    private void btZoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btZoomActionPerformed
        // TODO add your handling code here:
     Cameras camera = getCameraSelecionada();
        if (camera != null) {
            if (camera.isZoom()) {
                pararZoom();
            } else {
                iniciarZoom(cameraZoom.get(getCameraSelecionadaIndex()));
            }
        }
    }//GEN-LAST:event_btZoomActionPerformed

    private void btLigarDesligarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLigarDesligarActionPerformed
         // TODO add your handling code here:
        boolean status = true;
        for (Cameras camera : cameras) {
            camera.ligarDesligar();
            if (!camera.isLigado()) {
                status = false;
            }
        }
        updateInterface(status);
    }//GEN-LAST:event_btLigarDesligarActionPerformed

    private void btGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGravarActionPerformed
   Cameras camera = getCameraSelecionada();
        if (camera != null) {
            if (camera.isGravando()) {
                pararGravacao();
            } else {
                iniciarGravacao(gravandoCamera.get(getCameraSelecionadaIndex()), tempoGravacao.get(getCameraSelecionadaIndex()));
                iniciarGravacao(gravandoCameraZoom.get(getCameraSelecionadaIndex()), tempoGravacaoZoom.get(getCameraSelecionadaIndex()));
            }
        }     
    }//GEN-LAST:event_btGravarActionPerformed

    private void btDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDireitaActionPerformed
       for (Cameras camera : cameras) {
        camera.moverDireita(cameras.size());
       
                
    }
    int localizadorAtual = cameras.get(getCameraSelecionadaIndex()).getLocalizador();
    atualizarInterfaceComBaseNoLocalizador(localizadorAtual);
    atualizarSelecaoCamera(localizadorAtual);
    }//GEN-LAST:event_btDireitaActionPerformed

    private void btSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSubirActionPerformed
     for (Cameras camera : cameras) {
        camera.moverParaCima(2,3);
    }
    int localizadorAtual = cameras.get(getCameraSelecionadaIndex()).getLocalizador();
    atualizarInterfaceComBaseNoLocalizador(localizadorAtual);
    atualizarSelecaoCamera(localizadorAtual);
    }//GEN-LAST:event_btSubirActionPerformed

    private void btDescerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDescerActionPerformed
     for (Cameras camera : cameras) {
        camera.moverParaBaixo(2,3);
    }
    int localizadorAtual = cameras.get(getCameraSelecionadaIndex()).getLocalizador();
    atualizarInterfaceComBaseNoLocalizador(localizadorAtual);
    atualizarSelecaoCamera(localizadorAtual);
    }//GEN-LAST:event_btDescerActionPerformed

    private void btPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintActionPerformed
        int index = getCameraSelecionadaIndex();
        if (index != -1) {
            
            salvarImagem(cameraZoom.get(index));
        }
        
    }//GEN-LAST:event_btPrintActionPerformed

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        // TODO add your handling code here:
       //
    }//GEN-LAST:event_jPanel1ComponentResized
    
  
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CFTV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CFTV().setVisible(true);
        });
    }
    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDescer;
    private javax.swing.JButton btDireita;
    private javax.swing.JButton btEsquerda;
    private javax.swing.JButton btGravar;
    private javax.swing.JButton btLigarDesligar;
    private javax.swing.JButton btPrint;
    private javax.swing.JButton btSubir;
    private javax.swing.JButton btZoom;
    private javax.swing.JLabel cameraZoom1;
    private javax.swing.JLabel cameraZoom2;
    private javax.swing.JLabel cameraZoom3;
    private javax.swing.JLabel cameraZoom4;
    private javax.swing.JLabel cameraZoom5;
    private javax.swing.JLabel cameraZoom6;
    private javax.swing.JLabel gravandoCamera1;
    private javax.swing.JLabel gravandoCamera2;
    private javax.swing.JLabel gravandoCamera3;
    private javax.swing.JLabel gravandoCamera4;
    private javax.swing.JLabel gravandoCamera5;
    private javax.swing.JLabel gravandoCamera6;
    private javax.swing.JLabel gravandoCameraZoom1;
    private javax.swing.JLabel gravandoCameraZoom2;
    private javax.swing.JLabel gravandoCameraZoom3;
    private javax.swing.JLabel gravandoCameraZoom4;
    private javax.swing.JLabel gravandoCameraZoom5;
    private javax.swing.JLabel gravandoCameraZoom6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton radioCamera1;
    private javax.swing.JRadioButton radioCamera2;
    private javax.swing.JRadioButton radioCamera3;
    private javax.swing.JRadioButton radioCamera4;
    private javax.swing.JRadioButton radioCamera5;
    private javax.swing.JRadioButton radioCamera6;
    private javax.swing.JLabel telaCamera1;
    private javax.swing.JLabel telaCamera2;
    private javax.swing.JLabel telaCamera3;
    private javax.swing.JLabel telaCamera4;
    private javax.swing.JLabel telaCamera5;
    private javax.swing.JLabel telaCamera6;
    private javax.swing.JLabel tempoGravacao1;
    private javax.swing.JLabel tempoGravacao2;
    private javax.swing.JLabel tempoGravacao3;
    private javax.swing.JLabel tempoGravacao4;
    private javax.swing.JLabel tempoGravacao5;
    private javax.swing.JLabel tempoGravacao6;
    private javax.swing.JLabel tempoGravacaoZoom1;
    private javax.swing.JLabel tempoGravacaoZoom2;
    private javax.swing.JLabel tempoGravacaoZoom3;
    private javax.swing.JLabel tempoGravacaoZoom4;
    private javax.swing.JLabel tempoGravacaoZoom5;
    private javax.swing.JLabel tempoGravacaoZoom6;
    // End of variables declaration//GEN-END:variables
}
