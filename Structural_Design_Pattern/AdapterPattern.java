// Uses Adapter Pattern Method - used to integrate the new media player library with the existing system. 

// Media Player Program

import java.util.Scanner;

// Target interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee interface (Advanced Media Player)
interface AdvancedMediaPlayer {
    void playMp4(String fileName);
    void playVlc(String fileName);
}

// Concrete class for AdvancedMediaPlayer
class Mp4Player implements AdvancedMediaPlayer {
    public void playMp4(String fileName) {
        System.out.println("Playing MP4 file. Name: " + fileName);
    }

    public void playVlc(String fileName) {
        // Do nothing
    }
}

class VlcPlayer implements AdvancedMediaPlayer {
    public void playMp4(String fileName) {
        // Do nothing
    }

    public void playVlc(String fileName) {
        System.out.println("Playing VLC file. Name: " + fileName);
    }
}

// Adapter class to adapt AdvancedMediaPlayer to MediaPlayer
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new Mp4Player();
        } else if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        }
    }
}

// Concrete class implementing MediaPlayer
class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file. Name: " + fileName);
        } else if (audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("vlc")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported.");
        }
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AudioPlayer audioPlayer = new AudioPlayer();

        // Asking for user input
        System.out.println("Enter the audio type (mp3, mp4, vlc): ");
        String audioType = scanner.nextLine();

        System.out.println("Enter the file name: ");
        String fileName = scanner.nextLine();

        // Play the media based on user input
        audioPlayer.play(audioType, fileName);

        scanner.close();
    }
}

/*To compile the program use javac AdapterPattern.java and
 to run the program, use java AdapterPattern in the Terminal */
