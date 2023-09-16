/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import com.app.swing.blurhash.BlurHash;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class test {
    public static void main(String[] args) {
        try{
            BufferedImage image = ImageIO.read(new File("D:\\Workspace\\java\\chat_application\\Chat_Application_Java\\src\\com\\app\\icon\\testing\\test-2.jpg"));
            String blurhashStr = BlurHash.encode(image); 
            System.out.println(blurhashStr);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
