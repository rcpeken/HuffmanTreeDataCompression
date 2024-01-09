public class LinkList {
    private Link head;


    public LinkList() {
        head = null;

    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertToHead(char d,int f) {
        Link newLink = new Link(d,f);

        newLink.next = head;
        head = newLink;
    }




    public void displayList() {
        Link current = head;
        while (current != null) {
            System.out.println("Karakter: " + current.data + ", Frekans: " + current.frequency);
            current = current.next;
        }
    }

    public void ekle(char karakter, int frekans) {
        Link newNode = new Link(karakter, frekans);
        if (head == null || frekans<head.frequency) {
            insertToHead(karakter, frekans);
        } else {
            Link current = head;
            while (current.next != null && current.next.frequency <= frekans) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public Link getHead() {
        return head;
    }
}
