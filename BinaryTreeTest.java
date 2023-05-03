


class BTNode {
    String data;
    BTNode Lchild;
    BTNode Rchild;

    public BTNode() {
    }

    public BTNode(String dt) {
        data = dt;
        Lchild = null;
        Rchild = null;
    }

    public BTNode(BTNode lc, String dt, BTNode rc) {
        data = dt;
        Lchild = lc;
        Rchild = rc;
    }

}
class BinaryTree {

    BTNode root;

    public BinaryTree() {
        root=null;
    }

    public BinaryTree(BinaryTree ltree, String data, BinaryTree rtree) {
        root=new BTNode(ltree.root,data,rtree.root);
    }

    public boolean isEmpty() {
        if(root==null)
            return true;
        else
            return false;
    }

    public BinaryTree leftSubTree(){
        if(this.isEmpty()==true)
            return null;
        else{
            BinaryTree ltree = new BinaryTree();
            ltree.root = root.Lchild;
            return ltree;
        }
    }

    public BinaryTree rightSubTree(){
        if(isEmpty()==true)
            return null;
        else{
            BinaryTree rtree=new BinaryTree();
            rtree.root=root.Rchild;
            return rtree;
        }
    }

    public String rootData(){
        return root.data;
    }

    public void inorder(){ // 중위 순회
        theInOrder(root);
    }
    private void theInOrder(BTNode t){
        if(t!=null){
            theInOrder(t.Lchild);
            System.out.print(t.data);
            theInOrder(t.Rchild);

        }
    }
    public void preorder() {//전위 순회
        thePreOrder(root);
    }
    private void thePreOrder(BTNode t){
        if(t!=null){
            System.out.print(t.data);
            thePreOrder(t.Lchild);
            thePreOrder(t.Rchild);
        }
    }

    public void postorder() { //후위 순회
        thePostOrder(root);
    }
    private void thePostOrder(BTNode t){
        if(t!=null){
            thePostOrder(t.Lchild);
            thePostOrder(t.Rchild);
            System.out.print(t.data);
        }
    }

    public boolean equals(BinaryTree tr) {
        return theEqual(this.root,tr.root);
    }
    private boolean theEqual(BTNode s,BTNode t){
        if(s==null && t==null)
            return true;
        else if(s!=null && t!=null){
            if((s.data).equals(t.data)) {
                if (theEqual(s.Lchild, t.Lchild))
                    return theEqual(s.Rchild,t.Rchild);
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }
    public BinaryTree copy(){
        BinaryTree newTree=new BinaryTree();
        newTree.root=theCopy(root);
        return newTree;
    }
    private BTNode theCopy(BTNode t){
        if(t==null)
            return null;
        BTNode l=theCopy(t.Lchild);
        BTNode r=theCopy(t.Rchild);
        BTNode newNode=new BTNode();
        newNode.data=t.data;
        newNode.Lchild=l;
        newNode.Rchild=r;
        return newNode;
    }


}


class BinaryTreeTest {
    public static void main(String args[]) {
        BinaryTree btree;
        BinaryTree ltree;
        BinaryTree rtree;
        BinaryTree current;

        rtree = new BinaryTree(new BinaryTree(), "H", new BinaryTree());
        btree = new BinaryTree(new BinaryTree(), "G", rtree);
        ltree = btree;
        btree = new BinaryTree(ltree, "E", new BinaryTree());
        ltree = new BinaryTree(new BinaryTree(), "D", new BinaryTree());
        rtree = btree;
        btree = new BinaryTree(ltree, "B", rtree);
        ltree = new BinaryTree(new BinaryTree(), "F", new BinaryTree());
        current = new BinaryTree(ltree, "C", new BinaryTree());
        ltree = btree;
        rtree = current;
        btree = new BinaryTree(ltree, "A", rtree);

        System.out.print("중위 순회: ");
        btree.inorder();
        System.out.println();

        System.out.print("전위 순회: ");
        btree.preorder();
        System.out.println();

        System.out.print("후위 순회: ");
        btree.postorder();
        System.out.println();

        current = btree.copy();

        current.inorder();
        System.out.println();

        if (btree.equals(current))
            System.out.println("They are the same trees");
        else
            System.out.println("They are different trees");

    }
}



