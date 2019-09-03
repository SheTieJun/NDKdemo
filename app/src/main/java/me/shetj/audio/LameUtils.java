package me.shetj.audio;

public class LameUtils {
    static {
        System.loadLibrary("mp3lame");
    }

    public native static String testInit();

    public native static void init(int inSamplerate,
                                   int inChannel,
                                   int outSamplerate,
                                   int outBitrate,
                                   int quality);

    public native static int encode(short[] bufferLeft,
                                     short[] bufferRight,
                                     int samples,
                                     byte[] mp3buf);


    public native static int flush(byte[] mp3buf);


    public native static void close();

}