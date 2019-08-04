package rs.aleksa.mainapp;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import jaco.mp3.player.MP3Player;
import rs.aleksa.filefilter.FileTypeFilter;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.nio.file.Paths;
/*
 * Created by JFormDesigner on Sun Jul 28 19:15:35 CEST 2019
 */

/**
 * Ovaj program je namenjen predmetu Projektovanje Softvera.
 * Ovaj program prima muziku iz putanje fajlova, i isčitava odatle.
 * Mislim da ne treba da se inkorporira baza podataka jer neke baze neće raditi
 * na drugim operativnim sistemima, te isčitavanje putanje fajlova je u ovom primeru
 * bolja opcija.
 *
 * @author Aleksa
 */

public class PlayerFrame extends JFrame {

    private MP3Player player;
    private File songFile;
    private String currentDirectory = "home.user";
    private String imagePath;
    private String appName = "Aleksa Cakić SI 23/17";

    File[] songFiles;

    private boolean isOnRepeat = false;
    private boolean windowCollapsed = false;
    private int xMouse, yMouse;

    /**
     * Konstruktor koji inicijalizuje celu aplikaciju sa @initComponents() metodom.
     */
    public PlayerFrame() {
        initComponents();
        songFile = new File(" ");
        appTitle.setText(appName);
        String fileName = songFile.getName();
        currPlaying.setText(fileName);
        player = mp3Player();
        player.addToPlayList(songFile);
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
    }

    /**
     * Metoda koja pokreće stream.
     *
     * @param e
     */
    private void playMouseClicked(MouseEvent e) {
        try {
            player.play();
            player.getPlayList();
            play.setIcon(new ImageIcon("D:\\AMusicPlayer\\src\\rs\\aleksa\\images\\play_enabled.png"));
            currPlaying.setText("Sada na repertoaru: " + songFile.getName());
        } catch (Exception e1) {
            System.out.println(e.getClickCount() + " je kliknuto i izazvalo : " + e1.getMessage());
        }
    }

    /**
     * Metoda koja zaustavlja stream.
     *
     * @param e
     */
    private void stopMouseClicked(MouseEvent e) {
        try {
            player.stop();
        } catch (Exception e1) {
            System.out.println(e.getClickCount() + " je kliknuto i izazvalo : " + e1.getMessage());
        }
    }

    /**
     * Metoda koja pauzira stream.
     *
     * @param e
     */
    private void pauseMouseClicked(MouseEvent e) {
        try {
            player.pause();
        } catch (Exception e1) {
            System.out.println(e.getClickCount() + " je kliknuto i izazvalo : " + e1.getMessage());
        }
    }

    /**
     * Metoda koja postavlja stream u while(true) loop.
     *
     * @param e
     */
    private void repeatMouseClicked(MouseEvent e) {
        try {
            if (!isOnRepeat) {
                isOnRepeat = true;
                player.setRepeat(true);

                repeat.setIcon(new ImageIcon("D:\\AMusicPlayer\\src\\rs\\aleksa\\images\\repeat_enabled.png"));
            } else {
                isOnRepeat = false;
                player.setRepeat(false);

                repeat.setIcon(new ImageIcon("D:\\AMusicPlayer\\src\\rs\\aleksa\\images\\repeat.png"));
            }
        } catch (Exception e1) {
            System.out.println(e.getClickCount() + " je kliknuto i izazvalo : " + e1.getMessage());
        }
    }

    /**
     * Metoda koja inicijalizuje koordinate radi pomeranja prozora.
     *
     * @param e
     */
    private void appTitleMousePressed(MouseEvent e) {
        xMouse = e.getX();
        yMouse = e.getY();
    }

    /**
     * Metoda koja omogučava logiku iza koordinata i pomeranja prozora.
     *
     * @param e
     */
    private void appTitleMouseDragged(MouseEvent e) {
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }

    /**
     * Metoda koja zatvara i uništava proces.
     *
     * @param e
     */
    private void quitMouseClicked(MouseEvent e) {
        try {
            player.stop();
            this.dispose();
        } catch (Exception e1) {
            System.out.println(e.getClickCount() + " je kliknuto i izazvalo : " + e1.getMessage());
        }
    }

    /**
     * Metoda koja pokreće setting pannel.
     *
     * @param e
     */
    private void settingsMouseClicked(MouseEvent e) {
        try {
            JOptionPane.showMessageDialog(this, "Postavke ovde");
        } catch (Exception e1) {
            System.out.println(e.getClickCount() + " je kliknuto i izazvalo : " + e1.getMessage());
        }
    }

    /**
     * Metoda koja otvara fajl sistem iz koga se biraju podaci.
     *
     * @param e
     */
    private void openMouseClicked(MouseEvent e) {
        try {
            chosenFile();
        } catch (Exception e1) {
            System.out.println(e.getClickCount() + " je kliknuto i izazvalo : " + e1.getMessage());
        }
    }

    /**
     * Metoda koja je kolapsovati prozor i umanjiti ga kako ne bi smetao.
     *
     * @param e
     */
    private void appTitleMouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            if (windowCollapsed == false) {
                windowCollapsed = true;
                this.setSize(new Dimension(this.getSize().width, 50));

                appTitle.setFont(new Font("Nirmala UI", 0, 12));
                appTitle.setText("Playing Now... | " + songFile.getName());
            } else if (windowCollapsed == true) {
                windowCollapsed = false;
                this.setSize(new Dimension(this.getSize().width, 250));

                appTitle.setFont(new Font("Nirmala UI", 0, 18));
                appTitle.setText(appName);
            }
        }
    }

    /**
     * Metoda za utišavanje.
     *
     * @param e
     */
    private void volumeMouseClicked(MouseEvent e) {
        volumeDownControl(0.1);
    }

    /**
     * Metoda za pojačavanje.
     *
     * @param e
     */
    private void volume1MouseClicked(MouseEvent e) {
        volumeUpControl(0.1);
    }

    /**
     * Metoda za pojačavanje do maksimuma.
     *
     * @param e
     */
    private void volume2MouseClicked(MouseEvent e) {
        volumeControl(1.0);
    }

    /**
     * Metoda za maksimalno utišavanje, ili mute opcija.
     *
     * @param e
     */
    private void muteMouseClicked(MouseEvent e) {
        volumeControl(0.0);
    }

    /**
     * Metoda koja otvara app dialog.
     *
     * @param e
     */
    private void currPlayingMouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            AboutApp dialog = new AboutApp(this);
            dialog.setSize(500, 500);
            dialog.setVisible(true);
        }
    }

    /**
     * Metoda koja pušta sledeći element u stream-u.
     *
     * @param e
     */
    private void previousMouseClicked(MouseEvent e) {
        player.skipBackward();
        currPlaying.setName("Sada na repertoaru: " + songFile.getName());
    }

    /**
     * Metoda koja pušta prethodni element u streamu.
     *
     * @param e
     */
    private void nextMouseClicked(MouseEvent e) {
        player.skipForward();
        currPlaying.setName("Sada na repertoaru: " + songFile.getName());
    }

    /**
     * Metoda koja inicijalizuje sve komponente glavne aplikacije.
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Aleksa
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        container = new JPanel();
        header = new JPanel();
        appTitle = compFactory.createLabel("APP TITLE");
        quit = compFactory.createLabel("");
        settings = compFactory.createLabel("");
        display = new JPanel();
        currPlaying = compFactory.createLabel("text");
        previous = new JButton();
        next = new JButton();
        controlPannel = new JPanel();
        repeat = compFactory.createLabel("");
        pause = compFactory.createLabel("");
        play = compFactory.createLabel("");
        stop = compFactory.createLabel("");
        open = compFactory.createLabel("");
        volume = compFactory.createLabel("");
        volume1 = compFactory.createLabel("");
        volume2 = compFactory.createLabel("");
        mute = compFactory.createLabel("");

        //======== this ========
        setTitle("Aleksin Muzicki Plejer");
        setIconImage(new ImageIcon(getClass().getResource("/rs/aleksa/images/play.png")).getImage());
        setResizable(false);
        setUndecorated(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new CardLayout());

        //======== container ========
        {
            container.setBackground(new Color(7, 63, 86));
            container.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
                    new javax.swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e"
                    , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM
                    , new java.awt.Font("D\u0069al\u006fg", java.awt.Font.BOLD, 12)
                    , java.awt.Color.red), container.getBorder()));
            container.addPropertyChangeListener(
                    new java.beans.PropertyChangeListener() {
                        @Override
                        public void propertyChange(java.beans.PropertyChangeEvent e
                        ) {
                            if ("\u0062or\u0064er".equals(e.getPropertyName())) throw new RuntimeException()
                                    ;
                        }
                    });

            //======== header ========
            {
                header.setBackground(new Color(36, 93, 116));

                //---- appTitle ----
                appTitle.setText("APP TITLE");
                appTitle.setFont(new Font("Trebuchet MS", appTitle.getFont().getStyle() | Font.BOLD, appTitle.getFont().getSize() + 8));
                appTitle.setHorizontalAlignment(SwingConstants.LEFT);
                appTitle.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        appTitleMouseClicked(e);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        appTitleMousePressed(e);
                    }
                });
                appTitle.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        appTitleMouseDragged(e);
                    }
                });

                //---- quit ----
                quit.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/quit.png")));
                quit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        quitMouseClicked(e);
                    }
                });

                //---- settings ----
                settings.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/settings.png")));
                settings.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        settingsMouseClicked(e);
                    }
                });

                GroupLayout headerLayout = new GroupLayout(header);
                header.setLayout(headerLayout);
                headerLayout.setHorizontalGroup(
                        headerLayout.createParallelGroup()
                                .addGroup(headerLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(appTitle, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(settings, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(quit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                );
                headerLayout.setVerticalGroup(
                        headerLayout.createParallelGroup()
                                .addComponent(settings, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(quit, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addGroup(headerLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(appTitle, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                );
            }

            //======== display ========
            {
                display.setBackground(new Color(7, 63, 86));
                display.setBorder(new LineBorder(new Color(34, 202, 237, 140), 2, true));

                //---- currPlaying ----
                currPlaying.setHorizontalAlignment(SwingConstants.CENTER);
                currPlaying.setText("P L A Y I N G ...");
                currPlaying.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        currPlayingMouseClicked(e);
                    }
                });

                //---- previous ----
                previous.setText("<");
                previous.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        previousMouseClicked(e);
                    }
                });

                //---- next ----
                next.setText(">");
                next.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        nextMouseClicked(e);
                    }
                });

                GroupLayout displayLayout = new GroupLayout(display);
                display.setLayout(displayLayout);
                displayLayout.setHorizontalGroup(
                        displayLayout.createParallelGroup()
                                .addGroup(displayLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(previous)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(currPlaying, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(next)
                                        .addContainerGap())
                );
                displayLayout.setVerticalGroup(
                        displayLayout.createParallelGroup()
                                .addGroup(displayLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(displayLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(currPlaying, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                                .addComponent(previous)
                                                .addComponent(next))
                                        .addContainerGap())
                );
            }

            //======== controlPannel ========
            {
                controlPannel.setBackground(new Color(7, 63, 86));

                //---- repeat ----
                repeat.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/repeat.png")));
                repeat.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        repeatMouseClicked(e);
                    }
                });

                //---- pause ----
                pause.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/pause.png")));
                pause.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        pauseMouseClicked(e);
                    }
                });

                //---- play ----
                play.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/play.png")));
                play.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        playMouseClicked(e);
                    }
                });

                //---- stop ----
                stop.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/stop.png")));
                stop.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        stopMouseClicked(e);
                    }
                });

                //---- open ----
                open.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/open.png")));
                open.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        openMouseClicked(e);
                    }
                });

                //---- volume ----
                volume.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/volume_down.png")));
                volume.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        volumeMouseClicked(e);
                    }
                });

                //---- volume1 ----
                volume1.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/volume_up.png")));
                volume1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        volume1MouseClicked(e);
                    }
                });

                //---- volume2 ----
                volume2.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/volume_full.png")));
                volume2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        volume2MouseClicked(e);
                    }
                });

                //---- mute ----
                mute.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/mute.png")));
                mute.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        muteMouseClicked(e);
                    }
                });

                GroupLayout controlPannelLayout = new GroupLayout(controlPannel);
                controlPannel.setLayout(controlPannelLayout);
                controlPannelLayout.setHorizontalGroup(
                        controlPannelLayout.createParallelGroup()
                                .addGroup(controlPannelLayout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(repeat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pause, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(play, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(stop, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(open, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(volume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(volume1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(volume2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(mute, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(15, Short.MAX_VALUE))
                );
                controlPannelLayout.setVerticalGroup(
                        controlPannelLayout.createParallelGroup()
                                .addComponent(mute, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(volume2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(volume1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(volume, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(open, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stop, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(play, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pause, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(repeat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
            }

            GroupLayout containerLayout = new GroupLayout(container);
            container.setLayout(containerLayout);
            containerLayout.setHorizontalGroup(
                    containerLayout.createParallelGroup()
                            .addComponent(controlPannel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.TRAILING, containerLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(containerLayout.createParallelGroup()
                                            .addComponent(display, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
            );
            containerLayout.setVerticalGroup(
                    containerLayout.createParallelGroup()
                            .addGroup(containerLayout.createSequentialGroup()
                                    .addComponent(header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(display, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(controlPannel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())
            );
        }
        contentPane.add(container, "card1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Aleksa
    private JPanel container;
    private JPanel header;
    private JLabel appTitle;
    private JLabel quit;
    private JLabel settings;
    private JPanel display;
    private JLabel currPlaying;
    private JButton previous;
    private JButton next;
    private JPanel controlPannel;
    private JLabel repeat;
    private JLabel pause;
    private JLabel play;
    private JLabel stop;
    private JLabel open;
    private JLabel volume;
    private JLabel volume1;
    private JLabel volume2;
    private JLabel mute;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    /**
     * Inicijalizacija i pokretanje mp3 java plugina.
     *
     * @return
     */
    private MP3Player mp3Player() {
        return new MP3Player();
    }

    /**
     * Metoda koja je zadužena za utišavanje tona.
     *
     * @param valueToPlusMinus
     */
    private void volumeDownControl(Double valueToPlusMinus) {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for (Mixer.Info mixerInfo : mixers) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();

            for (Line.Info lineInfo : lineInfos) {
                Line line = null;
                boolean opened = true;

                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;

                    if (!opened)
                        line.open();

                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    float currentVolume = volControl.getValue();
                    Double volumeToCut = valueToPlusMinus;
                    float changedCalc = (float) ((float) currentVolume - (double) volumeToCut);
                    volControl.setValue(changedCalc);
                } catch (LineUnavailableException | IllegalArgumentException e) {
                    System.out.println("Nesto ovde ne valja! " + e.getMessage());
                } finally {
                    if (line != null && !opened)
                        line.close();
                }
            }
        }
    }

    /**
     * Metoda koja je zadužena za pojačavanje tona.
     *
     * @param valueToPlusMinus
     */
    private void volumeUpControl(Double valueToPlusMinus) {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for (Mixer.Info mixerInfo : mixers) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();

            for (Line.Info lineInfo : lineInfos) {
                Line line = null;
                boolean opened = true;

                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;

                    if (!opened)
                        line.open();

                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    float currentVolume = volControl.getValue();
                    float changedCalc = (float) ((float) currentVolume + (double) valueToPlusMinus);
                    volControl.setValue(changedCalc);
                } catch (LineUnavailableException | IllegalArgumentException e) {
                    System.out.println("Nesto ovde ne valja! " + e.getMessage());
                } finally {
                    if (line != null && !opened)
                        line.close();
                }
            }
        }
    }

    /**
     * Metoda koja je zadužena kontrolu tona, ili samo pojačavanje do maksimuma ili minimuma.
     *
     * @param valueToPlusMinus
     */
    private void volumeControl(Double valueToPlusMinus) {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        for (Mixer.Info mixerInfo : mixers) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            Line.Info[] lineInfos = mixer.getTargetLineInfo();

            for (Line.Info lineInfo : lineInfos) {
                Line line = null;
                boolean opened = true;

                try {
                    line = mixer.getLine(lineInfo);
                    opened = line.isOpen() || line instanceof Clip;

                    if (!opened)
                        line.open();

                    FloatControl volControl = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                    float currentVolume = volControl.getValue();
                    Double volumeToCut = valueToPlusMinus;
                    float changedCalc = (float) ((double) volumeToCut);
                    volControl.setValue(changedCalc);
                } catch (LineUnavailableException | IllegalArgumentException e) {
                    System.out.println("Nesto ovde ne valja! " + e.getMessage());
                } finally {
                    if (line != null && !opened)
                        line.close();
                }
            }
        }
    }

    /**
     * Main metoda koja pokreće ceo program. Koncipirana je tako da maksimalno pazi
     * na errore koji bi mogli izleteti, te ih kontroli lambda izrazom.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.println("Aleksa, ne budali na stackoverflowu, molim te: " + e.getMessage());
        }

        // Lambda koja kontroliše greške.
        java.awt.EventQueue.invokeLater(() -> new PlayerFrame().setVisible(true));
    }

    /**
     * Metoda koja poziva i koja kada se pokrene otvara JFileChooser prozor
     * kako bi korisnik mogao da otvori prozor i putanju do potrebnih fajlova.
     */
    public void chosenFile() {
        JFileChooser openFileChooser = new JFileChooser(currentDirectory);
        openFileChooser.setFileFilter(new FileTypeFilter(".mp3", "Otvorite samo MP3 fajlove!"));
        openFileChooser.setMultiSelectionEnabled(true);
        int result = openFileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            songFiles = openFileChooser.getSelectedFiles();
            processFiles(songFiles);
        }
    }

    /**
     * Metoda koja procesuje fajlove i dodaje ih u queue iz kojeg će se dalje očitavati.
     *
     * @param someFiles
     */
    private void processFiles(File[] someFiles) {
        for (File song : someFiles) {
            songFile = song;
            player.addToPlayList(songFile);
            player.skipForward();
            currentDirectory = songFile.getAbsolutePath();
        }
    }

    public MP3Player getPlayer() {
        return player;
    }

    public File getSongFile() {
        return songFile;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAppName() {
        return appName;
    }

    public File[] getSongFiles() {
        return songFiles;
    }

    public boolean isOnRepeat() {
        return isOnRepeat;
    }

    public boolean isWindowCollapsed() {
        return windowCollapsed;
    }

    public int getxMouse() {
        return xMouse;
    }

    public int getyMouse() {
        return yMouse;
    }

    public JPanel getContainer() {
        return container;
    }

    public JPanel getHeader() {
        return header;
    }

    public JLabel getAppTitle() {
        return appTitle;
    }

    public JLabel getQuit() {
        return quit;
    }

    public JLabel getSettings() {
        return settings;
    }

    public JPanel getDisplay() {
        return display;
    }

    public JLabel getCurrPlaying() {
        return currPlaying;
    }

    public JButton getPrevious() {
        return previous;
    }

    public JButton getNext() {
        return next;
    }

    public JPanel getControlPannel() {
        return controlPannel;
    }

    public JLabel getRepeat() {
        return repeat;
    }

    public JLabel getPause() {
        return pause;
    }

    public JLabel getPlay() {
        return play;
    }

    public JLabel getStop() {
        return stop;
    }

    public JLabel getOpen() {
        return open;
    }

    public JLabel getVolume() {
        return volume;
    }

    public JLabel getVolume1() {
        return volume1;
    }

    public JLabel getVolume2() {
        return volume2;
    }

    public JLabel getMute() {
        return mute;
    }
}
