//객체 어댑터를 활용한 MP3 플레이어 확장 예제
//기존 MP3 플레이어 인터페이스(MediaPlayer)

// Target 인터페이스 ( 클라이언트가 기대하는 인터페이스)
interface  MediaPlayer {
    void play(String audioType, String fileName);
}

// 기존 MP3 플레이어(Mp3Player)
// Adaptee 클래스 (기존 MP3 플레이어, MP3만 지원)
class Mp3Player implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else {
            System.out.println("Invalid format. MP3 only supported.");
        }
    }
}

public class AdapterPatternExample {
    public static void main(String[] args) {
        // MediaPlayer 인터페이스 타입의 Mp3Player 객체 생성
        MediaPlayer player = new Mp3Player();

        // MP3 파일 재생 (정상 작동)
        player.play("mp3", "song.mp3");

        // MP3가 아닌 다른 형식의 파일 재생 시도 (지원하지 않는 형식)
        player.play("mp4", "video.mp4");
        player.play("avi", "movie.avi");
    }
}


///////////////////////////////////////////////////////
//객체 어댑터
// MP3 플레이어에서만 음악을 재생할 수 있는 시스템이 있다고 가정하고,
// 이 시스템에서 MP4 및 AVI 파일도 재생할 수 있도록 어댑터를 사용하는 예제를 만들어보겠습니다.
//객체 어댑터를 활용한 MP3 플레이어 확장 예제

// 추가 지원이 필요한 고급 미디어 플레이어 (AdvancedMediaPlayer)
// Mp4와 AVI 형식을 지원하는 새로운 플레이어(AdvancedMediaPlayer)가 있지만,
// 기존 MediaPlayer 인터페이스와 다릅니다.

// Adaptee (MP4, AVI를 지원하는 새로운 플레이어)
class AdvancedMediaPlayer {
    public void playMp4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }

    public void playAvi(String fileName) {
        System.out.println("Playing AVI file: " + fileName);
    }
}

// 객체어댑터(MediaAdapter)
// Adapter 클래스 (객체 어댑터 방식, MediaPlayer 인터페이스 구현)
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;

    // 생성자에서 AdvancedMediaPlayer 객체를 생성
    public MediaAdapter() {
        this.advancedMediaPlayer = new AdvancedMediaPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);  // MP4 파일 재생
        } else if (audioType.equalsIgnoreCase("avi")) {
            advancedMediaPlayer.playAvi(fileName);  // AVI 파일 재생
        } else {
            System.out.println("Unsupported format: " + audioType);
        }
    }
}

}

// 통합된 플레이어(AudioPlayer)
// 기존 MP3 플레이어(Mp3Player)를 확장하여 모든 파일 형식을 지원하도록 변경합니다.
// 클라이언트 역할을 하는 통합 플레이어 (MP3 + MP4 + AVI 지원)
// 객체어댑터(MediaAdapter)
// Adapter 클래스 (AdvancedMediaPlayer를 MediaPlayer로 변환)
class AudioPlayer implements MediaPlayer {
    private MediaPlayer mediaPlayer;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file:" + fileName);
        } else if (audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("avi")) {
            mediaAdapter = new MediaAdapter(); // 어댑터 사용
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media format : " + audioType);
        }
    }
}

// 실행 코드 (클라이언트)
public class AdapterPatternExample {
    public static void main(String[] args) {
        MediaPlayer player = new AudioPlayer();

        player.play("mp3", "song.mp3");
        player.play("mp4", "video.mp4");
        player.play("avi", "movie.avi");
        player.play("wav", "sound.wav");  // 지원하지 않는 형식
    }
}







































