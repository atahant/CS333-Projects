import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);

            // Read the number of items.
            System.out.print("Enter the number of items: ");
            int n = 0;
            try {
                n = sc.nextInt();
                if (n < 1 || n > 25) {
                    throw new Exception("Error: n must be a positive integer between 1 and 25 (inclusive).");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            // Read the weights of the items.
            System.out.println("Enter the weights of the items.");
            int[] w = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.printf("Enter the weight for item %d: ", i + 1);
                try {
                    w[i] = sc.nextInt();
                    if (w[i] < 0) {
                        throw new Exception("Error: all weights must be non-negative.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            // Read the profits of the items.
            System.out.println("Enter the profits of the items.");
            int[] p = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.printf("Enter the profit for item %d: ", i + 1);
                try {
                    p[i] = sc.nextInt();
                    if (p[i] < 0) {
                        throw new Exception("Error: all profits must be non-negative.");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }

            // Read the maximum weight that can be carried.
            System.out.print("Enter the maximum weight that can be carried: ");
            int b = 0;
            try {
                b = sc.nextInt();
                if (b < 0) {
                    throw new Exception("Error: b must be a non-negative integer.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            // Read the minimum profit required.
            System.out.print("Enter the minimum profit required: ");
            int m = 0;
            try {
                m = sc.nextInt();
                if (m < 0) {
                    throw new Exception("Error: m must be a non-negative integer.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }

            if (solve(0, 0, 0, w, p, b, m)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            // Prompt the user to continue or exit the program.
            System.out.print("Enter 'Continue' to perform another search or 'Exit' to end the program: ");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("Exit")) {
                break;
            }
        }
    }

    // Perform an exhaustive search to find a subset of items with total weight at most b and corresponding profit at least m.
    // Returns true if such a subset is found, false otherwise.
    public static boolean solve(int i, int weight, int profit, int[] w, int[] p, int b, int m) {
        // Base case: if all items have been considered, check if the total weight is at most b and the corresponding profit is at least m.
        if (i == w.length) {
            return weight <= b && profit >= m;
        }

        // Recursive case 1: do not include the current item.
        if (solve(i + 1, weight, profit, w, p, b, m)) {
            return true;
        }
        // Recursive case 2: include the current item.
        return solve(i + 1, weight + w[i], profit + p[i], w, p, b, m);
    }
}
