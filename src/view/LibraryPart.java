package view;

import com.mpatric.mp3agic.*;
import model.Song;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This JPanel is placed at the left side of MainPaige which includes library and playlists
 */

public class LibraryPart extends JPanel {

    private JLabel options;
    private JLabel icon;
    private JLabel Jpotify;
    private static final String JPOTIFY_LABEL = "Jpotify";
    private JLabel libraryLabel;
    private JTextField fileChooserBtn;
    private JTextField songsBtn;
    private JTextField albumsBtn;
    private JLabel playlistLabel;
    private JTextField newPlaylistBtn;
    private JTextField sharedPlaylistBtn;
    private JTextField favouriteBtn;
    private Song song;
    private ShowPanel showPanel;
    private Color foreground;
    private Color pressedBackground;

    ArrayList<Song> songs = new ArrayList<>();
    ArrayList<Song> favouriteSongs = new ArrayList<>();
    HashMap<String, ArrayList<Song>> Album = new HashMap<>(); // this HashMap is for albums
    HashMap<String, ArrayList<Song>> Playlist = new HashMap<>(); // this HashMap is for Playlist


    public LibraryPart() {

        super();
        setSize(400, 400);
        this.setBackground(new Color(24, 24, 24));
        setLayout(new GridLayout(15, 1));
        foreground = new Color(179, 179, 179);
        pressedBackground = new Color(45, 45, 45);

        options = new JLabel("      ● ● ●");
        options.setForeground(Color.WHITE);
        options.setFont(new Font("Arial", Font.BOLD, 6));
        options.setToolTipText("options");
        options.setBackground(new Color(24, 24, 24));
        options.setForeground(Color.white);
        options.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        this.add(options);

//        add(Box.createRigidArea(new Dimension(0, 5)));

        icon = new JLabel();
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon JpotifyIcon = new ImageIcon("/Users/apple/Downloads/iconfinder_spotify_1217164.png");
        Image image = JpotifyIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        JpotifyIcon = new ImageIcon(newimg);
        icon.setIcon(JpotifyIcon);
        add(icon);

        Jpotify = new JLabel(JPOTIFY_LABEL);
        Jpotify.setFont(new Font("Arial", Font.BOLD, 10));
        Jpotify.setForeground(foreground);
        Jpotify.setBackground(this.getBackground());
        Jpotify.setHorizontalAlignment(SwingConstants.CENTER);
        add(Jpotify);


        libraryLabel = new JLabel("    LIBRARY");
        libraryLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 9));
        libraryLabel.setForeground(foreground);
        libraryLabel.setBackground(this.getBackground());
        add(libraryLabel);
//        add(Box.createRigidArea(new Dimension(0, 5)));


/**
 * This button is for adding a new song to the program
 */
        fileChooserBtn = new JTextField("   Add To Library");
        fileChooserBtn.setFont(new Font("Arial", Font.BOLD, 9));
        fileChooserBtn.setEditable(false);
        fileChooserBtn.setBackground(this.getBackground());
        fileChooserBtn.setForeground(foreground);
        fileChooserBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                fileChooserBtn.setBackground(pressedBackground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                fileChooserBtn.setBackground(getBackground());
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter(".mp3 files", "mp3"));
                fileChooser.setCurrentDirectory(new File("E:/"));
                int result = fileChooser.showOpenDialog(fileChooser);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    addSong(selectedFile.getAbsolutePath());
                    saveSong(selectedFile.getAbsolutePath());
                    System.out.println("song saved");
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(fileChooserBtn);


/**
 * This button shows all existed songs according to last time played
 */
        songsBtn = new JTextField("   Songs");
        songsBtn.setFont(new Font("Arial", Font.BOLD, 9));
        songsBtn.setEditable(false);
        songsBtn.setBackground(this.getBackground());
        songsBtn.setForeground(foreground);
        songsBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                songsBtn.setBackground(pressedBackground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                songsBtn.setBackground(getBackground());
                for (Song song : songs) {
                    System.out.println(song.getTitle());
                }
                showPanel.setSongs(songs);
                showPanel.revalidate();


            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(songsBtn);


/**
 * This button shows all albums according to last time played
 */
        albumsBtn = new JTextField("   Albums");
        albumsBtn.setFont(new Font("Arial", Font.BOLD, 9));
        albumsBtn.setBackground(this.getBackground());
        albumsBtn.setForeground(foreground);
        albumsBtn.setEditable(false);
        albumsBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                albumsBtn.setBackground(pressedBackground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                albumsBtn.setBackground(getBackground());
                System.out.println(Album.keySet());
                showPanel.setAlbums(Album);
                showPanel.revalidate();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(albumsBtn);

        add(Box.createRigidArea(new Dimension(0, 5)));

        playlistLabel = new JLabel("    PLAYLISTS");
        playlistLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 9));
        playlistLabel.setForeground(foreground);
        playlistLabel.setBackground(this.getBackground());
        add(playlistLabel);


/**
 * This button creates and adds a new playlist
 */
        newPlaylistBtn = new JTextField("   New Playlis");
        newPlaylistBtn.setFont(new Font("Arial", Font.BOLD, 9));
        newPlaylistBtn.setEditable(false);
        newPlaylistBtn.setBackground(this.getBackground());
        newPlaylistBtn.setForeground(foreground);
        newPlaylistBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                newPlaylistBtn.setBackground(pressedBackground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                newPlaylistBtn.setBackground(getBackground());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(newPlaylistBtn);


/**
 * This buttons shows user's shared playlist on network
 */
        sharedPlaylistBtn = new JTextField("   Shared Playlist");
        sharedPlaylistBtn.setFont(new Font("Arial", Font.BOLD, 9));
        sharedPlaylistBtn.setEditable(false);
        sharedPlaylistBtn.setBackground(this.getBackground());
        sharedPlaylistBtn.setForeground(foreground);
        sharedPlaylistBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                sharedPlaylistBtn.setBackground(pressedBackground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                sharedPlaylistBtn.setBackground(getBackground());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(sharedPlaylistBtn);


/**
 * Every user has some favourite songs which is shown by this button
 */
        favouriteBtn = new JTextField("   Favourites");
        favouriteBtn.setFont(new Font("Arial", Font.BOLD, 9));
        favouriteBtn.setEditable(false);
        favouriteBtn.setBackground(this.getBackground());
        favouriteBtn.setForeground(foreground);
        favouriteBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                favouriteBtn.setBackground(pressedBackground);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                favouriteBtn.setBackground(getBackground());
                showPanel.setSongs(favouriteSongs);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        add(favouriteBtn);


        setVisible(true);
    } // end of constructor


    /**
     * Adds a song to songs arraylist
     *
     * @param path
     */
    void addSong(String path) {

        song = new Song(path);
        songs.add(song);
        System.out.println(song.getTitle());
        System.out.println(song.getAlbumName());
        addToAlbum(song);
    }

    /**
     * Adds a song to user's favourites songs
     *
     * @param path
     */
    public void addFavourite(String path) {

        song = new Song(path);
        favouriteSongs.add(song);
    }

    /**
     * this method add each song to it's album HashMap
     *
     * @param song
     */
    public void addToAlbum(Song song) throws NullPointerException {

        if (Album.containsKey(song.getAlbumName()))
            Album.get(song.getAlbumName()).add(song);

        else {
            ArrayList<Song> songs = new ArrayList<>();
            songs.add(song);
            Album.put(song.getAlbumName(), songs);
        }

    }

    /**
     * this method save added song to the program
     *
     * @param path
     */
    public void saveSong(String path) {

        Song song = new Song(path);
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(new File(song.getTitle() + ".mp3"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(song);

            o.close();
            f.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * this method is for using library's showpanel on other classes
     *
     * @return
     */
    public ShowPanel getShowPanel() {
        return showPanel;
    }

    public void setShowPanel(ShowPanel showPanel) {
        this.showPanel = showPanel;
    }


    @Override
    public String toString() {
        return "LibraryPart{" +
                "songs=" + songs +
                '}';
    }


}