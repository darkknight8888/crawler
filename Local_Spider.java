package tests.java;

import java.util.*;
import java.io.*;

// ===================================================================
// acknowledgement: Help taken from internet and StackOverflow
// ===================================================================
//  file crawler implementation
// ===================================================================

public class Local_Spider {

	// adds folders and finds files
	public static void addTree(File file, Collection<File> root) {
		File[] new_file2 = file.listFiles();
		if (new_file2 != null) {
			for (File new_file3 : new_file2) {
				addTree(new_file3, root);
			}
		} else {
			root.add(file);
		}
	}
	
	// ===================================================================
	// This function basically searches all files that are present in map for
	// the particular string that we enter.
	// ===================================================================

	public static void searchIndex(HashMap data_set) throws IOException {
		// taking input from user

		Scanner in = new Scanner(System.in);

		while (true) {

			System.out.println("\nPlease enter any keyword to search or press e to exit : ");
			String s = in.nextLine();

			// checking exit
			if (s.equals("e")) {
				System.exit(0);

			}
      // ===============================================================
      // to traverse the map
	  // ===============================================================

			Set s1 = data_set.entrySet();
			Iterator i_1 = s1.iterator();

			while (i_1.hasNext()) {
				Map.Entry me = (Map.Entry) i_1.next();
				String new_string = (String) me.getValue();
				// for finding in paths (folder names)
				if (new_string.contains(s)) {
					System.out.println("The string exists in file: " + " " + new_string);
				}
				File file = new File(new_string);

				BufferedReader br = new BufferedReader(new FileReader(file));

				String line = null;
				// for finding in files (Any file name)
				while ((line = br.readLine()) != null) {
					if (line.contains(s)) {
						System.out.println("The string exists in file: " + " " + new_string);
					}
				}

			}
		}
	}

	// ============================================================================

	public static void main(String[] args) {

		Collection<File> new_file = new ArrayList<File>();

		// The crawling directory is given

		addTree(new File("C:\\Users\\Raveel\\Desktop"), new_file);
		try {

			// ========================================================================
			// Now implementing the method for creating HashMaps for doing
			// indexing
			// ========================================================================

			HashMap<String, String> data_set = new HashMap();

			Iterator i_2 = new_file.iterator();
			while (i_2.hasNext()) {
				File check = (File) i_2.next();

				// getting the filename
				String Fname = check.getName();
				String Fpath = check.getPath();

				// Storing files to hash map
				data_set.put(Fname, Fpath);
			}
			printIndex(data_set);
			searchIndex(data_set);
		} catch (IOException ex) {
			}

	}
	// ===================================================================
	// ===================================================================
	// prints all the files with indexes
	// ===================================================================

	public static void printIndex(HashMap data_set) {
		int x = 1;
		Set s2 = data_set.entrySet();
		Iterator s = s2.iterator();
		System.out.println("\n========== Welcome to Local Spider =========");
		// System.out.println("The Index mapping is ");
		System.out.println(" FILE NAMES   :     PATH");
		while (s.hasNext()) {
			Map.Entry me = (Map.Entry) s.next();
			System.out.print(x + ". " + me.getKey() + " : ");
			System.out.println(me.getValue());
			x = x + 1;
		}
	}

}
// ===================================================================
