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
 * @author Aleksa
 */

public class PlayerFrame extends JFrame {

    MP3Player player;
    File songFile;
    String currentDirectory = "home.user";
    String currentPath;
    String imagePath;
    String appName = "Aleksa Cakić SI 23/17";

    boolean isOnRepeat = false;
    boolean windowCollapsed = false;
    int xMouse, yMouse;

    public PlayerFrame() {
        initComponents();
        songFile = new File("rs/aleksa/sampleSong/Shadow of Intent - The Heretic Prevails (lyric video).mp3");
        appTitle.setText(appName);
        String fileName = songFile.getName();
        currPlaying.setText(fileName);
        player = mp3Player();
        player.addToPlayList(songFile);
        currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
    }

    private void playMouseClicked(MouseEvent e) {
        // TODO add your code here
        player.play();
        play.setIcon(new ImageIcon("D:\\AMusicPlayer\\src\\rs\\aleksa\\images\\play_enabled.png"));
        currPlaying.setText("Sada na repertoaru: " + songFile.getName());
    }

    private void stopMouseClicked(MouseEvent e) {
        // TODO add your code here
        player.stop();
    }

    private void pauseMouseClicked(MouseEvent e) {
        // TODO add your code here
        player.pause();
    }

    private void repeatMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (isOnRepeat == false) {
            isOnRepeat = true;
            player.setRepeat(isOnRepeat);

            repeat.setIcon(new ImageIcon("D:\\AMusicPlayer\\src\\rs\\aleksa\\images\\repeat_enabled.png"));
        } else if (isOnRepeat == true) {
            isOnRepeat = false;
            player.setRepeat(isOnRepeat);

            repeat.setIcon(new ImageIcon("D:\\AMusicPlayer\\src\\rs\\aleksa\\images\\repeat.png"));
        }
    }

    private void appTitleMousePressed(MouseEvent e) {
        // TODO add your code here
        xMouse = e.getX();
        yMouse = e.getY();
    }

    private void appTitleMouseDragged(MouseEvent e) {
        // TODO add your code here
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();

        this.setLocation(x - xMouse, y - yMouse);
    }

    private void quitMouseClicked(MouseEvent e) {
        // TODO add your code here
        this.dispose();
    }

    private void settingsMouseClicked(MouseEvent e) {
        // TODO add your code here
        JOptionPane.showMessageDialog(this, "Postavke ovde");
    }

    private void openMouseClicked(MouseEvent e) {
        // TODO add your code here
        chosenFile();
    }

    private void appTitleMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (e.getClickCount() == 2) {
            if (windowCollapsed == false) {
                windowCollapsed = true;
                this.setSize(new Dimension(this.getSize().width, 50));
                appTitle.setFont(new Font("Trebuchet MS", appTitle.getFont().getStyle() | Font.BOLD, appTitle.getFont().getSize() + 6));
                appTitle.setText("Sada se slusa: " + songFile.getName());
            } else if (windowCollapsed == true) {
                windowCollapsed = false;
                this.setSize(new Dimension(this.getSize().width, 250));
                appTitle.setFont(new Font("Trebuchet MS", appTitle.getFont().getStyle() | Font.BOLD, appTitle.getFont().getSize() + 18));
                appTitle.setText(appName);
            } else {
                return;
            }
        }
    }

    private void volumeMouseClicked(MouseEvent e) {
        // TODO add your code here
        volumeDownControl(0.1);
    }

    private void volume1MouseClicked(MouseEvent e) {
        // TODO add your code here
        volumeUpControl(0.1);
    }

    private void volume2MouseClicked(MouseEvent e) {
        // TODO add your code here
        volumeControl(1.0);
    }

    private void muteMouseClicked(MouseEvent e) {
        // TODO add your code here
        volumeControl(0.0);
    }

    private void nextSongMouseClicked(MouseEvent e) {
        // TODO add your code here
        player.skipBackward();
        currPlaying.setName(currPlaying.getName());
    }

    private void previousSongMouseClicked(MouseEvent e) {
        // TODO add your code here
        player.skipForward();
        currPlaying.setName(currPlaying.getName());
    }

    private void currPlayingMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (e.getClickCount() == 2) {
            AboutApp dialog = new AboutApp(this);
            dialog.setSize(500, 500);
            dialog.setVisible(true);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Aleksa
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        container = new JPanel();
        header = new JPanel();
        appTitle = compFactory.createLabel("APP TITLE");
        quit = compFactory.createLabel("");
        settings = compFactory.createLabel("");
        previousSong = compFactory.createLabel("");
        nextSong = compFactory.createLabel("");
        display = new JPanel();
        currPlaying = compFactory.createLabel("text");
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
            container.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,container. getBorder( )) ); container. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

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

                //---- previousSong ----
                previousSong.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/forward.png")));
                previousSong.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        previousSongMouseClicked(e);
                    }
                });

                //---- nextSong ----
                nextSong.setIcon(new ImageIcon(getClass().getResource("/rs/aleksa/images/reverse.png")));
                nextSong.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        nextSongMouseClicked(e);
                    }
                });

                GroupLayout headerLayout = new GroupLayout(header);
                header.setLayout(headerLayout);
                headerLayout.setHorizontalGroup(
                    headerLayout.createParallelGroup()
                        .addGroup(headerLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(appTitle, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nextSong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(previousSong, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(130, 130, 130)
                            .addComponent(settings, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(quit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                );
                headerLayout.setVerticalGroup(
                    headerLayout.createParallelGroup()
                        .addGroup(headerLayout.createSequentialGroup()
                            .addGroup(headerLayout.createParallelGroup()
                                .addComponent(quit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(settings, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(headerLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(appTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(headerLayout.createParallelGroup()
                                        .addComponent(previousSong, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nextSong, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap())
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

                GroupLayout displayLayout = new GroupLayout(display);
                display.setLayout(displayLayout);
                displayLayout.setHorizontalGroup(
                    displayLayout.createParallelGroup()
                        .addGroup(displayLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(currPlaying, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                            .addContainerGap())
                );
                displayLayout.setVerticalGroup(
                    displayLayout.createParallelGroup()
                        .addGroup(displayLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(currPlaying, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
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
                        .addGroup(containerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(header, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(display, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
            );
            containerLayout.setVerticalGroup(
                containerLayout.createParallelGroup()
                    .addGroup(containerLayout.createSequentialGroup()
                        .addComponent(header, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
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
    private JLabel previousSong;
    private JLabel nextSong;
    private JPanel display;
    private JLabel currPlaying;
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

    private MP3Player mp3Player() {
        MP3Player mp3Player = new MP3Player();
        return mp3Player;
    }

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
                } catch (LineUnavailableException e) {
                    System.out.println("Nesto ovde ne valja! " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Nesto ovde ne valja! " + e.getMessage());
                } finally {
                    if (line != null && !opened)
                        line.close();
                }
            }
        }
    }

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
                    Double volumeToCut = valueToPlusMinus;
                    float changedCalc = (float) ((float) currentVolume + (double) volumeToCut);
                    volControl.setValue(changedCalc);
                } catch (LineUnavailableException e) {
                    System.out.println("Nesto ovde ne valja! " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Nesto ovde ne valja! " + e.getMessage());
                } finally {
                    if (line != null && !opened)
                        line.close();
                }
            }
        }
    }

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
                } catch (LineUnavailableException e) {
                    System.out.println("Nesto ovde ne valja! " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Nesto ovde ne valja! " + e.getMessage());
                } finally {
                    if (line != null && !opened)
                        line.close();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.println("Aleksa, ne budali na stackoverflowu, molim te: " + e.getMessage());
        } catch (InstantiationException e) {
            java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.println("Aleksa, ne budali na stackoverflowu, molim te: " + e.getMessage());
        } catch (IllegalAccessException e) {
            java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.println("Aleksa, ne budali na stackoverflowu, molim te: " + e.getMessage());
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            java.util.logging.Logger.getLogger(PlayerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.println("Aleksa, ne budali na stackoverflowu, molim te: " + e.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> new PlayerFrame().setVisible(true));
    }

    public void chosenFile() {
        File[] songFiles;
        JFileChooser openFileChooser = new JFileChooser(currentDirectory);

        openFileChooser.setFileFilter(new FileTypeFilter(".mp3", "Otvorite samo MP3 fajlove!"));
        openFileChooser.setMultiSelectionEnabled(true);
        int result = openFileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            songFiles = openFileChooser.getSelectedFiles();
            name(songFiles);
            currPlaying.setText("Sada na repertoaru: " + songFile.getName());
        }
    }

    public void name(File[] someFiles) {
        for (File song : someFiles) {
            songFile = song;
            player.addToPlayList(songFile);
            player.skipForward();
            currentDirectory = songFile.getAbsolutePath();
        }
    }
}
