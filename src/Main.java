import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VisitorDAO dao = new VisitorDAO();

        while (true) {
            System.out.println("\n====== Visitor Log Management ======");
            System.out.println("1. Add Visitor");
            System.out.println("2. View All Visitors");
            System.out.println("3. Search Visitors");
            System.out.println("4. Update Visitor");
            System.out.println("5. Delete Visitor");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter Visitor Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Purpose: ");
                    String purpose = sc.nextLine();
                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();
                    dao.addVisitor(new Visitor(name, purpose, contact));
                    break;

                case 2:
                    List<Visitor> visitors = dao.getAllVisitors();
                    System.out.println("\nID   | Name            | Purpose             | Contact        | Check-in Time");
                    System.out.println("--------------------------------------------------------------------------------");
                    for (Visitor v : visitors) System.out.println(v);
                    break;

                case 3:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    List<Visitor> results = dao.searchVisitors(keyword);
                    System.out.println("\nSearch Results:");
                    for (Visitor v : results) System.out.println(v);
                    break;

                case 4:
                    System.out.print("Enter Visitor ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Purpose: ");
                    String newPurpose = sc.nextLine();
                    System.out.print("Enter New Contact: ");
                    String newContact = sc.nextLine();
                    dao.updateVisitor(id, newName, newPurpose, newContact);
                    break;

                case 5:
                    System.out.print("Enter Visitor ID to delete: ");
                    int delId = sc.nextInt();
                    dao.deleteVisitor(delId);
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}