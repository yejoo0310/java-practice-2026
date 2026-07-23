package src.ch4.ex05;

public class Song {
    private String title, singer, lang;
    private int year;

    public Song(String title, String singer, int year,String lang){
        this.title = title;
        this.singer = singer;
        this.year = year;
        this.lang = lang;
    }

    public void show(){
        System.out.println(year + "년 " + lang + "의 " + singer + "가(이) 부른 " + title);
    }

    public static void main(String[] args){
        Song song = new Song("가로수 그늘 아래 서면", "이문세", 1988, "한국");
        song.show();
    }
}
