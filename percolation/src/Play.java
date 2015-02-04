public class Play {
    public static void main(String[] args) {
        // Q1
        QuickFindUF uf = new QuickFindUF(10);
        for (int[] pair : parse("1-5 0-5 0-6 8-7 0-7 3-9")) {
            uf.union(pair[0], pair[1]);
        }
        System.out.println(print(uf, 10));

        // Q2
        WeightedQuickUnionUF wuf = new WeightedQuickUnionUF(10);
        for (int[] pair : parse("7-0 3-8 9-2 6-0 2-3 7-4 7-1 7-8 3-5")) {
            wuf.union(pair[0], pair[1]);
        }
        System.out.println("Manual!");
    }

    private static String print(QuickFindUF uf, int N) {
        String result = "";
        for (int i = 0; i < N; i++) {
            result += uf.find(i) + " ";
        }
        return result;
    }

    private static int[][] parse(String string) {
        String[] lines = string.split("\\s+");
        int[][] pairs = new int[lines.length][2];
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split("-");
            pairs[i][0] = Integer.parseInt(line[0]);
            pairs[i][1] = Integer.parseInt(line[1]);
        }
        return pairs;
    }
}
