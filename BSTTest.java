
class TreeNode {
    String key;
    TreeNode Lchild;
    TreeNode Rchild;
}

class BinarySearchTree {
    private TreeNode rootNode;

    public TreeNode BSTsearch(String K) {
        return searchBST(rootNode,K);
    }
    private TreeNode searchBST(TreeNode p,String k){
        if(p==null) return null;
        if(p.key==k) return p;
        if(p.key.compareTo(k)<0){
            return searchBST(p.Rchild,k);
        }
        return searchBST(p.Lchild,k);
    }


    public void BSTinsert(String K) {
        rootNode=insertBST(rootNode,K);
    }
    private TreeNode insertBST(TreeNode p,String k){
        if(p==null) {
            p=new TreeNode();
            p.key=k;
            return p;
        }
        else if(k.compareTo(p.key)<0) {
            p.Lchild = insertBST(p.Lchild, k);
            return p;
        }
        else if(k.compareTo(p.key)>0) {
            p.Rchild = insertBST(p.Rchild,k);
            return p;
        }
        else
            return p;
    }


    private TreeNode find_parent(TreeNode root, String x) {
        TreeNode T = root;
        TreeNode PT = root;
        int result;
        while (T != null) {
            if ((result = x.compareTo(T.key)) < 0) {
                PT=T;
                T = T.Lchild;
            } else if (result == 0) {
                return PT;
            } else {
                PT=T;
                T = T.Rchild;
            }
        }
        return null;
    }
    public void BSTdelete(String K) {
        rootNode = delete(rootNode, K);
    }

    private TreeNode delete(TreeNode root, String K) {
        TreeNode p = searchBST(root, K);
        TreeNode parent=find_parent(root, K);

        if (p == null)
        {
            return root;
        }

        if (p.Lchild == null && p.Rchild == null)
        {
            if(p==root) //추가
                return null;

            if (parent.Lchild == p)
                parent.Lchild = null;
            else
                parent.Rchild = null;
            return root;

        }
        else if (p.Lchild == null || p.Rchild == null)
        {
            if(p==root)
            {
             if(p.Lchild == null){
                 root=p.Rchild;
             }
             else{
                 root=p.Lchild;
             }
             return root;
            }
            if (p.Lchild != null)
            {
                if (parent.Lchild == p)
                    parent.Lchild = p.Lchild;
                else
                    parent.Rchild = p.Lchild;
            }
            else
                {
                if (parent.Lchild == p)
                    parent.Lchild = p.Rchild;
                else
                    parent.Rchild = p.Rchild;
                }
            return root;
        }
        else //삭제할 노드의 차수가 2인 경우
        {
            TreeNode q = maxNode(p.Lchild);//왼쪽 서브트리에서 최대 키값을 가진 원소를 탐색
            p.key = q.key;
            p.Lchild=delete(p.Lchild,p.key);
            return root;
        }

    }
    private TreeNode maxNode(TreeNode p){
        TreeNode current_node=p;
        while(current_node.Rchild != null){
            current_node=current_node.Rchild;
        }
        return current_node;
    }

   public boolean BSTsplit(String x, BinarySearchTree SmallTree, BinarySearchTree LargeTree) {
        TreeNode Small = new TreeNode();
        TreeNode Large = new TreeNode();
        TreeNode S= Small;
        TreeNode L= Large;
        TreeNode p=rootNode;
        int result;
        while(p!=null) {
            if ((result = x.compareTo(p.key)) == 0) {
                S.Rchild = p.Lchild;
                L.Lchild = p.Rchild;
                SmallTree.rootNode = Small.Rchild;
                LargeTree.rootNode= Large.Lchild;
                return true;
            } else if ((result= x.compareTo(p.key)) < 0) {
                L.Lchild = p;
                L = p;
                p = p.Lchild;
            } else {
                S.Rchild = p;
                S = p;
                p = p.Rchild;
            }
        }
        S.Rchild = null;
        L.Lchild = null;

       SmallTree.rootNode = Small.Rchild;
       LargeTree.rootNode= Large.Lchild;
        return false;
    }

    private void printNode(TreeNode n) {
        if (n != null) {
            System.out.print("(");
            printNode(n.Lchild);
            System.out.print(" " + n.key + " ");
            printNode(n.Rchild);
            System.out.print(")");
        }
    }

    public void print() {
        printNode(rootNode);
        System.out.println();
    }
}

class BSTTest {
    public static void main(String args[]) {

        BinarySearchTree t = new BinarySearchTree();
        t.BSTinsert("S");
        t.BSTinsert("J");
        t.BSTinsert("B");
        t.BSTinsert("D");
        t.BSTinsert("U");
        t.BSTinsert("M");
        t.BSTinsert("R");
        t.BSTinsert("Q");
        t.BSTinsert("A");
        t.BSTinsert("G");
        t.BSTinsert("E");

        t.print();

        TreeNode n = t.BSTsearch("R");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.BSTdelete("R");

        n = t.BSTsearch("R");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.print();

        n = t.BSTsearch("E");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.BSTdelete("E");

        n = t.BSTsearch("E");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.print();

        n = t.BSTsearch("J");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.BSTdelete("J");

        n = t.BSTsearch("J");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.print();

        n = t.BSTsearch("S");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.BSTdelete("S");

        n = t.BSTsearch("S");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.print();

        n = t.BSTsearch("B");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.BSTdelete("B");

        n = t.BSTsearch("B");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        t.print();

        n = t.BSTsearch("C");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }


        BinarySearchTree t1 = new BinarySearchTree();
        t1.BSTinsert("S");
        t1.BSTinsert("J");
        t1.BSTinsert("B");
        t1.BSTinsert("D");
        t1.BSTinsert("U");
        t1.BSTinsert("M");
        t1.BSTinsert("R");
        t1.BSTinsert("Q");
        t1.BSTinsert("A");
        t1.BSTinsert("G");
        t1.BSTinsert("E");

        t1.print();

        n = t1.BSTsearch("R");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        n = t1.BSTsearch("C");
        if (n==null) {
            System.out.println("Not Found");
        } else {
            System.out.println("Found");
        }

        System.out.println("분할 부분 ");

        BinarySearchTree Small = new BinarySearchTree();
        BinarySearchTree Large = new BinarySearchTree();

        t.print();
        t.BSTsplit("D", Small, Large);

        Small.print();
        Large.print();

        t1.print();
        t1.BSTsplit("C", Small, Large);

        Small.print();
        Large.print();

        t1 = new BinarySearchTree();
        t1.BSTinsert("T");
        t1.BSTinsert("U");
        t1.BSTinsert("S");
        t1.BSTinsert("R");
        t1.BSTinsert("O");
        t1.BSTinsert("M");
        t1.BSTinsert("W");
        t1.BSTinsert("X");
        t1.BSTinsert("C");
        t1.BSTinsert("B");
        t1.BSTinsert("A");

        t1.print();

        t1.BSTdelete("T");

        t1.print();

        t1 = new BinarySearchTree();
        t1.BSTinsert("T");
        t1.BSTinsert("U");
        t1.BSTinsert("R");
        t1.BSTinsert("S");
        t1.BSTinsert("O");
        t1.BSTinsert("M");
        t1.BSTinsert("X");
        t1.BSTinsert("W");
        t1.BSTinsert("C");
        t1.BSTinsert("B");
        t1.BSTinsert("Y");

        t1.print();

        t1.BSTdelete("X");

        t1.print();

        t1.BSTdelete("R");

        t1.print();
    }
}
