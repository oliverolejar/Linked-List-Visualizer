public class SinglyLinkedList {

    private Node<Integer> front;

    public SinglyLinkedList(){
        front = null;
    }

    public SinglyLinkedList(Node<Integer> node){
        front = node;
    }

    public Node<Integer> getFront(){
        return front;
    }

    public void addNode(int num) {
        if ( front == null ){
            front = new Node<>(num);
        } else {
            Node<Integer> curr = front;
            while ( curr.getNext() != null ){
                curr = curr.getNext();
            }
            curr.setNext(new Node<>(num));
        }
    }

    public void removeNode(int num){
        if ( front == null ){
            return;
        } else if ( front.getItem() == num ){
            front = front.getNext();
        } else {
            Node<Integer> curr = front;
            while ( curr.getNext() != null ){
                if ( curr.getNext().getItem() == num ){
                    curr.setNext(curr.getNext().getNext());
                    return;
                } else {
                    curr = curr.getNext();
                }
            }
        }
    }

    public boolean contains(int target){
        Node<Integer> curr = front;
        while ( curr != null ){
            if ( curr.getItem() == target ){
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

}