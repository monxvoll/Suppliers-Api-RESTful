package co.edu.uptc.model;


import java.util.List;

public class SupplierSorter {

    public  void bubbleSortByNameDescending(List<Supplier> suppliers) {
        int n = suppliers.size();
        boolean aux;
        do {
            aux = false;
            for (int i = 1; i < n; i++) {

                if (suppliers.get(i - 1).getName().charAt(0) < suppliers.get(i).getName().charAt(0)) {

                    Supplier temp = suppliers.get(i - 1);
                    suppliers.set(i - 1, suppliers.get(i));
                    suppliers.set(i, temp);
                    aux = true;
                }
            }
            n--;
        } while (aux);
    }

    public void bubbleSortByNameAscending(List<Supplier> suppliers) {
        int n = suppliers.size();
        boolean aux;
        do {
            aux = false;
            for (int i = 1; i < n; i++) {

                if (suppliers.get(i - 1).getName().charAt(0) > suppliers.get(i).getName().charAt(0)) {

                    Supplier temp = suppliers.get(i - 1);
                    suppliers.set(i - 1, suppliers.get(i));
                    suppliers.set(i, temp);
                    aux = true;
                }
            }
            n--;
        } while (aux);
    }

    public void insertionSortByIdDescending(List<Supplier> suppliers) {
        int n = suppliers.size();
        for (int i = 1; i < n; ++i) {
            Supplier key = suppliers.get(i);
            int j = i - 1;


            while (j >= 0 && Integer.parseInt(suppliers.get(j).getId()) < Integer.parseInt(key.getId())) {
                suppliers.set(j + 1, suppliers.get(j));
                j = j - 1;
            }
            suppliers.set(j + 1, key);
        }
    }

    public void insertionSortByIdAscending(List<Supplier> suppliers) {
        int n = suppliers.size();
        for (int i = 1; i < n; ++i) {
            Supplier key = suppliers.get(i);
            int j = i - 1;


            while (j >= 0 && Integer.parseInt(suppliers.get(j).getId()) > Integer.parseInt(key.getId())) {
                suppliers.set(j + 1, suppliers.get(j));
                j = j - 1;
            }
            suppliers.set(j + 1, key);
        }
    }



    public void selectionSortByEmailFirstCharDescending(List<Supplier> suppliers) {
        int n = suppliers.size();

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (suppliers.get(j).getEmail().charAt(0) > suppliers.get(min).getEmail().charAt(0)) {
                    min = j;
                }
            }
            Supplier temp = suppliers.get(min);
            suppliers.set(min, suppliers.get(i));
            suppliers.set(i, temp);
        }
    }

    public void selectionSortByEmailFirstCharAscending(List<Supplier> suppliers) {
        int n = suppliers.size();

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (suppliers.get(j).getEmail().charAt(0) < suppliers.get(min).getEmail().charAt(0)) {
                    min = j;
                }
            }
            // Swap elements
            Supplier temp = suppliers.get(min);
            suppliers.set(min, suppliers.get(i));
            suppliers.set(i, temp);
        }
    }
}






