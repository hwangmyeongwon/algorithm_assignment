class BTNode {
    int data;
    int key;
    BTNode Lchild;
    BTNode Rchild;

    public BTNode() {
    }

    public BTNode(int dt) {
        data = dt;
        Lchild = null;
        Rchild = null;
    }

    public BTNode(BTNode lc, int dt, BTNode rc) {
        data = dt;
        Lchild = lc;
        Rchild = rc;
    }

}
class BinarySearchTree{
    BTNode root;
    public BTNode search(int x){//순환 기법
        return searchBST(root,x);
    }
    private BTNode searchBST(BTNode p,int x){//순환 기법
        if(p==null)
            return null;
        if(p.data==x)
            return p;
        if(p.key<x)
            return searchBST(p.Rchild,x);
        else
            return searchBST(p.Lchild,x);
    }
    public BTNode search2(int x){//반복문 기법 이용 
        BTNode p=root;
        while(p!=null){
            if(x==p.key)return p;
            if(x<p.key)
                p=p.Lchild;
            else
                p=p.Rchild;
        }
        return null;
    }
}
public class BinarySearchTreeTest {
}
