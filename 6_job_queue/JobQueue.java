import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    static class Worker {
        final int index;
        long freeAt = 0;

        Worker(int index) {
            this.index = index;
        }

        int getIndex() {
            return index;
        }

        long getFreeAt() {
            return freeAt;
        }
    }

    static class Job {
        final int duration;
        int assignedWorker;
        long startTime;

        Job(int duration) {
            this.duration = duration;
        }
    }

    private int numWorkers;
    private Job[] jobs;


    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new Job[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = new Job(in.nextInt());
        }
    }

    private void writeResponse() {
        for (Job job : jobs) {
            out.println(job.assignedWorker + " " + job.startTime);
        }
    }

    private void assignJobs(Job[] jobs, int numWorkers) {
        PriorityQueue<Worker> queue = new PriorityQueue<>(numWorkers, Comparator.comparing(Worker::getFreeAt).thenComparing(Worker::getIndex));
        for (int i = 0; i < numWorkers; i++) {
            queue.add(new Worker(i));
        }

        for (Job job : jobs) {
            Worker worker = queue.poll();
            job.startTime = worker != null ? worker.freeAt : 0;
            job.assignedWorker = worker != null ? worker.index : 0;
            if (worker != null) {
                worker.freeAt += job.duration;
            }
            queue.add(worker);
        }
    }

    private void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs(jobs, numWorkers);
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
