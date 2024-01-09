public class Link {

        public char data;
        public int frequency;
        public Link next;

        public  Link(char d,int f) {
            data = d;
            frequency=f;
        }

        public void displayLink() {
            System.out.print(data + " ");
        }

}
