import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class tree_orders {
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

    public class TreeOrders {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<>();
            inOrder(result, 0);

            return result;
        }

        void inOrder(List<Integer> list, int index) {
            if (list == null)
                return;
            if (index != -1) {
                inOrder(list, left[index]);
                list.add(key[index]);
                inOrder(list, right[index]);
            }
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<>();
            preOrder(result, 0);
            return result;
        }

        void preOrder(List<Integer> list, int index) {
            if (list == null)
                return;
            if (index != -1) {
                list.add(key[index]);
                preOrder(list, left[index]);
                preOrder(list, right[index]);
            }
        }

        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<>();
			postOrder(result, 0);

            return result;
        }

        void postOrder(List<Integer> list, int index) {
            if (list == null)
                return;
            if (index != -1) {
                postOrder(list, left[index]);
                postOrder(list, right[index]);
                list.add(key[index]);
            }
        }

    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
