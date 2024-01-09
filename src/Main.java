import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int[] dizi = new int[256];
        String directory = "C:\\Users\\Recep\\Desktop\\veriYapilari_bireysel\\Odev\\src\\";
        String fileName1 = "Dataset-2.txt";
        String fileName2= "EncodeDataset.txt";
        String dataset = "";
        try {
            FileReader file=new FileReader(directory+fileName1);
            int satir;
            while ((satir = file.read()) != -1) {
                // Satır içerisinde gezip karakterlerin ASCII değerlerini 1 artır
                dataset += (char) satir;
                dizi[satir]++;
            }
            System.out.println("Dosyadaki veri: "+dataset);

           /* for (int i = 0; i < dizi.length; i++) {
                System.out.print(" i: " + i + " value: " + dizi[i]);
            }*/
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
        LinkList linkList =new LinkList();
        for (char c = 0; c < dizi.length; c++) {
            if (dizi[c] != 0) {
                linkList.ekle( c, dizi[c]);
            }
        }

        linkList.displayList();
        HuffmanNode huffmanNode=new HuffmanNode();
        huffmanNode.ekle(linkList.getHead());
        huffmanNode.displayTree();
        huffmanNode.generateHuffmanCodes();

        String huffmanCodingPrint = huffmanNode.encodeDataset(dataset);



        System.out.println("Metnin Huffman coding çıktısı: " + huffmanCodingPrint);
        try (FileWriter writer = new FileWriter(directory + "EncodeDataset.txt")) {
            writer.write("Encoding Dataset is: " + huffmanCodingPrint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}