package markus.wieland.scrabble.new_version.helper;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileReader {

    private final Activity activity;
    private final Gson gson;

    public FileReader(Activity activity) {
        this.activity = activity;
        this.gson = new Gson();
    }

    public <T> T read(String fileName, Class<T> tClass) {
        String fileContent = read(fileName);
        return gson.fromJson(fileContent, tClass);
    }

    public String read(String fileName) {
        try (InputStream inputStream = this.activity.getAssets().open(fileName)){
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            int bytesRead = inputStream.read(buffer);
            Log.i(FileReader.class.getSimpleName(), "Read " + bytesRead + " bytes from " + fileName);
            return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
