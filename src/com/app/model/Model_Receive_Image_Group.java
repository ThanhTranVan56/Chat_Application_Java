package com.app.model;

import com.app.swing.blurhash.BlurHash;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Base64;
import javax.imageio.ImageIO;
import org.json.JSONException;
import org.json.JSONObject;

public class Model_Receive_Image_Group {

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Model_Receive_Image_Group(int fileID, String fileName, String fileExtension, long fileSize, String image, int width, int height) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileSize = fileSize;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public Model_Receive_Image_Group() {
    }

    public Model_Receive_Image_Group(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            fileID = obj.getInt("fileID");
            fileName = obj.getString("fileName");
            fileExtension = obj.getString("fileExtension");
            fileSize = obj.getLong("fileSize");
            image = obj.getString("image");
            width = obj.getInt("width");
            height = obj.getInt("height");
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
    private int fileID;
    private String fileName;
    private String fileExtension;
    private long fileSize;
    private String image;
    private int width;
    private int height;
    private final String PATH_FILE = "client_data/";

    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("fileID", fileID);
            json.put("image", image);
            json.put("width", width);
            json.put("height", height);
            return json;
        } catch (JSONException e) {
            return null;
        }
    }

    public void bufferedImage() {
        BufferedImage images = BlurHash.decodeAndDraw(image, width, height, 1, BufferedImage.TYPE_INT_ARGB);
        System.out.println("iamge : " + this.image);
        System.out.println("width : " + this.width);
        System.out.println("height : " + this.height);
        System.out.println("fileExtension : " + this.fileExtension);
        //fileExtension.substring(1)
        String filePath = PATH_FILE + "@" + fileName + fileExtension; // Đường dẫn đến tệp tin bạn muốn lưu
        try {
            File outputFile = new File(filePath);
            ImageIO.write(images,"jpg", outputFile);
            System.out.println("Lưu tệp tin thành công.");
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu tệp tin: " + e.getMessage());
        }
    }

    private static final String[] fileSizeUnits = {"bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};

    private String getFileSizeConvert(double bytes) {
        String sizeToReturn;
        DecimalFormat df = new DecimalFormat("0.#");
        int index;
        for (index = 0; index < fileSizeUnits.length; index++) {
            if (bytes < 1024) {
                break;
            }
            bytes = bytes / 1024;
        }
        System.out.println("Systematic file size: " + bytes + " " + fileSizeUnits[index]);
        sizeToReturn = df.format(bytes) + " " + fileSizeUnits[index];
        return sizeToReturn;
    }

    private String extractExtension(String input) {
        if (input.startsWith(".")) {
            return input.substring(1);
        } else {
            return input;
        }
    }
}
