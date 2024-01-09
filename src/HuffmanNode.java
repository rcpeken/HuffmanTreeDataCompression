import java.util.Stack;

public class HuffmanNode {
    Huffman root;

    public HuffmanNode() {
        this.root = null;
    }
    private void insert(char karakter,int frequency){
        Huffman huffman=new Huffman(frequency, karakter);
        if (root==null){
            root=huffman;
        }else {
            Huffman parent =new Huffman(root.frequency+frequency,'x');
            parent.leftChild=huffman;
            parent.rightChild=root;
            root=parent;

        }
    }
    public void ekle(Link head){
        Link temp=head;
        while (temp!=null){
            insert(temp.data, temp.frequency);
            temp=temp.next;
        }
    }
    public void displayTree() {
        Stack<Huffman> globalStack = new Stack<Huffman>(); // Global Stack Nesne Tipi Node
        globalStack.push(root); //global stack başlangıcı olarak root yerleştir
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("......................................................");
        while (!isRowEmpty) { //satır boştan farklıysa çalış yani false olduğunda çalış
            Stack<Huffman> localStack = new Stack<Huffman>(); // Local Stack
            isRowEmpty = true; //varsayılan değer olarak satır boş diye ata

            for (int j = 0; j < nBlanks; j++)
                System.out.print(' '); //32 karakter boşluk koy

            while (!globalStack.isEmpty()) { //global stack boş değilse
                Huffman temp = (Huffman) globalStack.pop(); //globalstackten Node Tipinde getir (Local Stackten yerleşenlerden en soldan başla sağa doğru getir)
                if (temp != null) { //temp doluysa stackten root-sol ç.-sağ ç. şeklinde veri çekilir.
                    System.out.print(temp.karakter); // root - sol çocuk -sağ çocuk şeklinde verileri yaz...
                    localStack.push(temp.leftChild); //locakstack e önce sol çocuğu yerleştir
                    localStack.push(temp.rightChild);// sonra sağ çocuğu yerleştir

                    if (temp.leftChild != null || temp.rightChild != null) //sağ ya da sol çocuk varsa isrowempty=false
                        isRowEmpty = false; //satır dolu
                }
                else { //temp nullsa
                    System.out.print("--");//boşsa  2 tire ata
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) //32 boşluk vardı sayılar arası şimdi 2 katı oldu
                    System.out.print(' ');
            } // end while globalStack boş değil
            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop()); //global stacke local stackten gelenleri yerleştir(önce sağ düğm sonra sol düğüm)
            //Localstacktekileri sağdan sola GlobalStack'e yerleştir.
        } // end while isRowEmpty false
        System.out.println("......................................................");
    }
    public void generateHuffmanCodes() {
        generateHuffmanCodesRecursive(root, "");

    }

    private void generateHuffmanCodesRecursive(Huffman node, String code) {
        if (node != null) {
            if (node.leftChild == null && node.rightChild == null) {
                // Bu bir yaprak düğümü ise, yani bir karakteri temsil ediyorsa
                System.out.println(node.karakter + " : " + code);
            }

            generateHuffmanCodesRecursive(node.leftChild, code + "0");
            generateHuffmanCodesRecursive(node.rightChild, code + "1");
        }
    }
    public String encodeDataset(String dataset) {
        StringBuilder encodedData = new StringBuilder();

        for (char c : dataset.toCharArray()) {
            encodedData.append(getHuffmanCode(root, c));
        }

        return encodedData.toString();
    }

    private String getHuffmanCode(Huffman node, char character) {
        if (node != null) {
            if (node.leftChild == null && node.rightChild == null && (char) node.karakter == character) {
                return "";
            }

            String leftCode = getHuffmanCode(node.leftChild, character);
            if (leftCode != null) {
                return "0" + leftCode;
            }

            String rightCode = getHuffmanCode(node.rightChild, character);
            if (rightCode != null) {
                return "1" + rightCode;
            }
        }

        return null;
    }
}
