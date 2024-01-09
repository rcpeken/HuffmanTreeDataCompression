public class Huffman {
    int frequency;
    char karakter;
    Huffman leftChild,rightChild;

    public Huffman(int frequency, char karakter) {
        this.frequency = frequency;
        this.karakter = karakter;
        leftChild=null;
        rightChild=null;
    }
}
