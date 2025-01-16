public class Node<G> {

    private G item;
    private Node<G> next;

    public Node(){
        item = null;
        next = null;
    }

    public Node(G item){
        this.item = item;
        next = null;
    }

    public G getItem(){
        return item;
    }

    public void setItem(G newItem){
        item = newItem;
    }

    public Node<G> getNext(){
        return next;
    }

    public void setNext(Node<G> newNode){
        next = newNode;
    }

}
