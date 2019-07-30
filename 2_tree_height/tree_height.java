import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class tree_height {
    class Node {
        Queue<Node> childrens;

        Node() {
            childrens = new LinkedList<>();
        }

        void addChild(Node node) {
            childrens.add(node);
        }

        Node getChild() {
            return childrens.poll();
        }

        ArrayList<Node> getChilds() {
            ArrayList<Node> retNodes = new ArrayList<>();
            for (Node n : childrens) {
                retNodes.add(n);
            }
            return retNodes;
        }
    }

    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {
        int n;
        int parent[];
        Node rootNode;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
            createTree();
        }

        void createTree() {
            List<Node> nodeList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                nodeList.add(new Node());
            }
            for (int i = 0; i < n; i++) {
                if (parent[i] == -1) {
                    rootNode = nodeList.get(i);
                } else {
                    nodeList.get(parent[i]).addChild(nodeList.get(i));
                }
            }
        }

        int treeHeight(Node node) {
            if (node == null)
                return 0;
            Queue<Node> q = new LinkedList<>();
            q.add(node);
            int height = 0;
            while (true) {
                int nodeCount = q.size();
                if (nodeCount == 0)
                    return height;
                height++;
                while (nodeCount > 0) {
                    Node newNode = q.peek();
                    q.remove();
                    if (newNode != null)
                        if (newNode.childrens != null) {
                            q.addAll(newNode.getChilds());
                        }
                    nodeCount--;
                }
            }
        }

        int computeHeight() {
            return treeHeight(rootNode);
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
