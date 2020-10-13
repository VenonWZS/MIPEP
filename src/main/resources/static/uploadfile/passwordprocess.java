package org.metaproject.login;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class passwordprocess {

//    public static void main(String[] args) {
//
//        // 对输入文本SHA256加密
//
//
//        Scanner in = new Scanner(System.in);
//
//        System.out.println("Input text in one line to be encrypted: ");
//        String text = in.nextLine();
//        try (BufferedWriter bw = new BufferedWriter(
//                new OutputStreamWriter(new FileOutputStream("src.txt"), StandardCharsets.UTF_8))) {
//            bw.write(text);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(new FileInputStream("src.txt"), StandardCharsets.UTF_8))) {
//            String src = br.readLine();
//            String encryptedSrc = Encrypt(src, "SHA-256");
//            try (BufferedWriter bw = new BufferedWriter(
//                    new OutputStreamWriter(new FileOutputStream("encryptedSrc.txt"), StandardCharsets.UTF_8))) {
//                bw.write(encryptedSrc);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Source string: " + src);
//            System.out.println("Encrypted string: " + encryptedSrc);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        in.close();
//
//    }

    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Encrypted failed！");
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}