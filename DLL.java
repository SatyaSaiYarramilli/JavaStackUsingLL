/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dll;

import java.util.Scanner;

/**
 *
 * @author aditya
 */
public class DLL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DbLL dll = new DbLL();
        Scanner sc = new Scanner(System.in);
        int ch, item;
        do {
            dll.sop("Enter choice:\n1.Create\n2.Delete\n3.Display\n4.Insert\n5.Delete all\n6.Exit");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    dll.sop("Enter element: ");
                    item = sc.nextInt();
                    dll.create();
                    break;
                case 2:
                    dll.delete();
                    break;
                case 3:
                    dll.display();
                    break;
                case 4:
                    dll.insert();
                    break;
                case 5:
                    do {
                        dll.delete();
                    } while (dll.head != null);
                    dll.sop("All elements deleted");
                    break;
                case 6:
                    System.exit(0);
                default:
                    dll.sop("Invalid choice. Try again");
            }
        } while (ch != 6);
    }
}

class Node {

    int data;
    Node plink, nlink;

    public Node(int d) {
        data = d;
        plink = nlink = null;
    }
}

class DbLL {

    int item;
    Node head, last;
    Scanner s = new Scanner(System.in);

    public void create() {
        head = last = null;
        do {
            sop("Enter elements: ");
            item = s.nextInt();
            if (item == 0) {
                break;
            }
            Node n = new Node(item);
            if (head == null) {
                head = last = n;
            } else {
                last.nlink = n;
                n.plink = last;
                last = n;
            }
        } while (item != 0);
    }

    public void display() {
        if (head == null) {
            sop("List is empty");
        } else {
            Node temp;
            temp = head;
            do {
                sop(temp.data + "  ");
                temp = temp.nlink;
            } while (temp != null);
        }
    }

    int length() {
        int l;
        Node temp = head;
        for (l = 1; temp != null; l++) {
            temp = temp.nlink;
        }
        return l;
    }

    public void delete() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.println("Enter position: ");
        int pos = s.nextInt();
        if (pos > 0 && pos <= length()) {
            if (pos == 1) {
                head = head.nlink;
            } else {
                Node prv, curr;
                prv = curr = head;
                int i = 1;
                while (i <= pos) {
                    prv = curr;
                    prv.plink = curr.nlink;
                    i++;
                }
                prv.nlink = curr.nlink;
            }
        }
    }

    void insert() {
        System.out.println("Enter Position: ");
        int pos = s.nextInt();
        if (pos > 0 && pos <= length()) {
            System.out.println("Enter item: ");
            int item = s.nextInt();
            Node temp = new Node(item);
            if (pos == 1) {
                temp.nlink = head;
                head.plink = temp;
                head = temp;
            } else {
                Node prev, curr;
                prev = curr = head;
                int i = 1;
                while (i <= pos) {
                    prev = curr;
                    curr = curr.nlink;
                    i++;
                }
                prev.nlink = temp;
                temp.plink = prev;
                temp.nlink = curr;
                curr.plink = temp;
            }
        } else {
            System.out.println("No such place");
        }
    }

    public void sop(String a) {
        System.out.println(a);
    }
}
