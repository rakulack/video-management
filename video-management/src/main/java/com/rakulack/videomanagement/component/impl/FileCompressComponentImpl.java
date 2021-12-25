package com.rakulack.videomanagement.component.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.rakulack.videomanagement.component.FileCompressComponent;

import org.springframework.stereotype.Component;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.info.VideoSize;

@Component
public class FileCompressComponentImpl implements FileCompressComponent {

    @Override
    public InputStream compress(InputStream is, String fileName) {
        // webmは未対応みたいなんで、そのまま返す。
        if ("webm".equals(fileName.substring(fileName.lastIndexOf(".") + 1))) {
            return is;
        }
        try {
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            Path tempPath = FileSystems.getDefault().getPath("temp." + extension);
            Files.copy(is, tempPath, StandardCopyOption.REPLACE_EXISTING);
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("libmp3lame");
            audio.setBitRate(Integer.valueOf(128000));
            audio.setSamplingRate(Integer.valueOf(44100));
            audio.setChannels(Integer.valueOf(2));
            VideoAttributes video = new VideoAttributes();
            video.setCodec("libx264");
            video.setBitRate(Integer.valueOf(6000000));
            video.setFrameRate(Integer.valueOf(24));
            video.setSize(new VideoSize(854, 480));
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setAudioAttributes(audio);
            attrs.setVideoAttributes(video);
            File source = new File("temp." + extension);
            File dest = new File("source.mp4");
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), dest, attrs);
            return new FileInputStream("source.mp4");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return is;

    }

    @Override
    public void deleteTempFile(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        try {
            Files.delete(FileSystems.getDefault().getPath("temp." + extension));
            Files.delete(FileSystems.getDefault().getPath("source.mp4"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
